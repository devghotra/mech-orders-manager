package com.jassboys.mech.orders.service;

import com.google.common.collect.Lists;
import com.jassboys.mech.orders.model.*;
import com.jassboys.mech.orders.repo.AuthtokenRepository;
import com.jassboys.mech.orders.repo.LookupRepository;
import com.jassboys.mech.orders.repo.OrdersRepository;
import com.jassboys.mech.orders.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrdersService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AuthtokenRepository authtokenRepo;

	@Autowired
	private LookupRepository lookupRepo;

	@Autowired
	private OrdersRepository ordersRepo;

	public void addUser(User user, String authtokenVal) {
		Authtoken authtoken = authtokenRepo.findOne(authtokenVal);
		user.setCreatedByUser(authtoken.getUserName());
		user.setActive(true);
		userRepo.save(user);
	}

	public void deleteUser(User user, String authtokenVal) {
		authtokenRepo.findOne(authtokenVal);
		User persistedUser = userRepo.findOne(user.getUserName());

		if(persistedUser == null)
			throw new RuntimeException("No user found with username "+user.getUserName());

		userRepo.delete(persistedUser);
	}
	
	public ApiResponse login(String username, String password) throws Exception{
		long start = System.currentTimeMillis();
		User user = userRepo.findOne(username.toUpperCase());

		if(user == null || !user.getPassword().equalsIgnoreCase(password)){
			throw new Exception("Invalid credentials");
		}

		String tokenVal = UUID.randomUUID().toString().replace("-", "");
		authtokenRepo.save(new Authtoken(tokenVal, username, 60, new Date(), new Date()));

		ApiResponse response =  new ApiResponse();
		response.setAuthToken(tokenVal);

		System.out.println("OrdersService - exit login "+(System.currentTimeMillis() - start));

		return response;
	}

	public void addLookupData(Lookup lookup, String authtokenVal){
		getAuthtokenDetails(authtokenVal);
		lookupRepo.save(lookup);
	}

	public Map<String, List<String>> getLookupData(String authtokenVal){
		long start = System.currentTimeMillis();
		getAuthtokenDetails(authtokenVal);
		Iterable<Lookup> lookupItr = lookupRepo.findAll();

		Map<String, List<String>> lookupDataMap = new HashMap<>();

		lookupItr.forEach(
				lookup -> {
					if (lookupDataMap.containsKey(lookup.getType())) {
						lookupDataMap.get(lookup.getType()).add(lookup.getValue());
					} else {
						List<String> list = Lists.newArrayList(lookup.getValue());
						lookupDataMap.put(lookup.getType(), list);
					}
				}
		);

		System.out.println("OrdersService - exit getLookupData "+(System.currentTimeMillis() - start));
		return lookupDataMap;
	}


	public void deleteLookup(Lookup lookup, String authtokenVal){
		getAuthtokenDetails(authtokenVal);
		lookup = lookupRepo.findByTypeAndValue(lookup.getType(), lookup.getValue());

		if(lookup == null)
			throw new RuntimeException("Invalid input");

		lookupRepo.delete(lookup);
	}

	public Orders getOrder(Long orderId, String authtokenVal) {
		long start = System.currentTimeMillis();
		getAuthtokenDetails(authtokenVal);
		Orders order = ordersRepo.findOne(orderId);

		if(order == null)
			throw new RuntimeException("Invalid Order Id "+orderId);

		System.out.println("OrdersService - exit getOrder "+(System.currentTimeMillis() - start));
		return order;
	}

	public void upsertOrder(Orders order, String authtokenVal) {
		long start = System.currentTimeMillis();
		String username = getAuthtokenDetails(authtokenVal);

		if(order.getId() == null){
			order.setCreatedByUser(username);
			order.setCreationDateTime(new Date());
		} else{
			Orders persistedOrder = ordersRepo.findOne(order.getId());
			order.setModifiedByUser(username);
			order.setModDateTime(new Date());
			order.setCreatedByUser(persistedOrder.getCreatedByUser());
			order.setCreationDateTime(persistedOrder.getCreationDateTime());
		}

		ordersRepo.save(order);
		System.out.println("OrdersService - exit upsertOrder "+(System.currentTimeMillis() - start));
	}

	public void deleteOrder(Long orderId, String authtokenVal){
		getAuthtokenDetails(authtokenVal);
		ordersRepo.delete(orderId);
	}

	public List<Orders> searchOrders(String authtokenVal, String equipmentNumber, String mechanicName, Date from, Date to) {
		long start = System.currentTimeMillis();
		authtokenRepo.findOne(authtokenVal);

		Calendar cal = Calendar.getInstance();
		if(to == null){
			to = cal.getTime();
		}

		if(from == null){
			cal.add(Calendar.MONTH, -1);
			from = cal.getTime();
		}

		List<Orders> orders;
		if(equipmentNumber != null && mechanicName != null){
			orders = ordersRepo.findByEquipmentNumberAndMechanicNameAndOrderDateBetweenOrderByIdDesc(equipmentNumber, mechanicName, from, to);
		} else if(equipmentNumber != null){
			orders = ordersRepo.findByEquipmentNumberAndOrderDateBetweenOrderByIdDesc(equipmentNumber, from, to);
		} else if(mechanicName != null){
			orders = ordersRepo.findByMechanicNameAndOrderDateBetweenOrderByIdDesc(mechanicName, from , to);
		} else{
			orders = ordersRepo.findByOrderDateBetweenOrderByIdDesc(from, to);
		}

		System.out.println("OrdersService - exit searchOrders "+(System.currentTimeMillis() - start));
		return orders;
	}

	private String getAuthtokenDetails(String authtokenVal){
		long start = System.currentTimeMillis();
		Authtoken authtoken = authtokenRepo.findOne(authtokenVal);

		if(authtoken == null)
			throw new RuntimeException("Unauthorized User");

		String username = authtoken.getUserName();
		long modDateTime = authtoken.getModDateTime().getTime();
		int tokenLife = authtoken.getTokenLifeMins();

		long currentTimestamp = Calendar.getInstance().getTimeInMillis();
		long expirationTime = modDateTime + tokenLife*60*1000;

		if(expirationTime > currentTimestamp){
			authtoken.setModDateTime(new Date());
			authtokenRepo.save(authtoken);
			System.out.println("OrdersService - exit getAuthtokenDetails "+(System.currentTimeMillis() - start));
			return username;
		}
		else {
			throw new RuntimeException("Session Expired");
		}
	}
}
