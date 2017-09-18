package com.jassboys.mech.orders.controller;

import com.jassboys.mech.orders.model.ApiResponse;
import com.jassboys.mech.orders.model.Lookup;
import com.jassboys.mech.orders.model.Orders;
import com.jassboys.mech.orders.model.User;
import com.jassboys.mech.orders.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Controller
@RequestMapping("api/mech/orders")
public class OrdersController {
	
	@Autowired
	OrdersService ordersService;

	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseBody
	public ApiResponse addUser(@RequestParam(value = "at", required = true) String authtoken, @RequestBody User user){
		ApiResponse serviceResponse = new ApiResponse();
		try {
			ordersService.addUser(user, authtoken);
			serviceResponse.setResponseCode(200);
		} catch (Exception e) {
			serviceResponse.setResponseCode(500);
			serviceResponse.setResponseDescription(e.getMessage());
		}

		return serviceResponse;
	}

	@RequestMapping(value = "/user", method = RequestMethod.DELETE, produces = {"application/json"})
	@ResponseBody
	public ApiResponse deactivateUser(@RequestParam(value = "at", required = true) String authtoken, @RequestBody User user){
		ApiResponse serviceResponse = new ApiResponse();
		try {
			ordersService.deleteUser(user, authtoken);
			serviceResponse.setResponseCode(200);
		} catch (Exception e) {
			serviceResponse.setResponseCode(500);
			serviceResponse.setResponseDescription(e.getMessage());
		}

		return serviceResponse;
	}
	
	@RequestMapping(value="/login",
			method = RequestMethod.POST,
    		consumes = {"application/json"}, 
    		produces = {"application/json"})
    @ResponseBody
	public ApiResponse login(@RequestBody User user){
		ApiResponse serviceResponse = new ApiResponse();
		try {
			if(user.getUserName() == null || user.getUserName().trim().isEmpty()
					|| user.getPassword() == null || user.getPassword().trim().isEmpty()){
				throw new Exception("Invalid credentials");
			}
			serviceResponse = ordersService.login(user.getUserName().trim(), user.getPassword().trim());
			serviceResponse.setResponseCode(200);
		} catch (Exception e) {
			serviceResponse.setResponseCode(500);
			serviceResponse.setResponseDescription(e.getMessage());
		}
		return serviceResponse;
	}

	@RequestMapping(value="/lookup",
			method = RequestMethod.POST,
			consumes = {"application/json"},
			produces = {"application/json"})
	@ResponseBody
	public ApiResponse addLookup(@RequestBody Lookup lookup, @RequestParam(value = "at", required = true) String authtoken){
		ApiResponse serviceResponse = new ApiResponse();
		try {
			ordersService.addLookupData(lookup, authtoken);
			serviceResponse.setResponseCode(200);
		} catch (Exception e) {
			serviceResponse.setResponseCode(500);
			serviceResponse.setResponseDescription(e.getMessage());
		}
		return serviceResponse;
	}

	@RequestMapping(value="/lookup",
			method = RequestMethod.GET,
			produces = {"application/json"})
	@ResponseBody
	public ApiResponse getLookup(@RequestParam(value = "at", required = true) String authtoken){
		ApiResponse serviceResponse = new ApiResponse();
		try {
			serviceResponse.setLookupData(ordersService.getLookupData(authtoken));
			serviceResponse.setResponseCode(200);
		} catch (Exception e) {
			serviceResponse.setResponseCode(500);
			serviceResponse.setResponseDescription(e.getMessage());
		}
		return serviceResponse;
	}

	@RequestMapping(value="/lookup",
			method = RequestMethod.DELETE,
			produces = {"application/json"})
	@ResponseBody
	public ApiResponse deleteLookup(@RequestBody Lookup lookup, @RequestParam(value = "at", required = true) String authtoken){
		ApiResponse serviceResponse = new ApiResponse();
		try {
			ordersService.deleteLookup(lookup, authtoken);
			serviceResponse.setResponseCode(200);
		} catch (Exception e) {
			serviceResponse.setResponseCode(500);
			serviceResponse.setResponseDescription(e.getMessage());
		}
		return serviceResponse;
	}

	@RequestMapping(method = RequestMethod.POST,
			consumes = {"application/json"},
			produces = {"application/json"})
	@ResponseBody
	public ApiResponse upsertOrder(@RequestBody Orders order, @RequestParam(value = "at", required = true) String authtoken){
		ApiResponse serviceResponse = new ApiResponse();
		try {
			ordersService.upsertOrder(order, authtoken);
			serviceResponse.setResponseCode(200);
		} catch (Exception e) {
			serviceResponse.setResponseCode(500);
			serviceResponse.setResponseDescription(e.getMessage());
		}
		return serviceResponse;
	}

	@RequestMapping(method = RequestMethod.GET,
			produces = {"application/json"})
	@ResponseBody
	public ApiResponse getOrder(@RequestParam(value = "at") String authtoken, @RequestParam(value = "id") Long orderId){
		ApiResponse serviceResponse = new ApiResponse();
		try {
			serviceResponse.setOrder(ordersService.getOrder(orderId, authtoken));
			serviceResponse.setResponseCode(200);
		} catch (Exception e) {
			serviceResponse.setResponseCode(500);
			serviceResponse.setResponseDescription(e.getMessage());
		}
		return serviceResponse;
	}

	@RequestMapping(value="/search",
			method = RequestMethod.GET,
			produces = {"application/json"})
	@ResponseBody
	public ApiResponse searchOrders(@RequestParam(value = "at") String authtoken,
									@RequestParam(value = "equipment", required = false) String equipmentNumber,
									@RequestParam(value = "mechanic", required = false) String mechanicName,
									@RequestParam(value = "from", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
									@RequestParam(value = "to", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date to){
		ApiResponse serviceResponse = new ApiResponse();
		try {
			serviceResponse.setOrdersList(ordersService.searchOrders(authtoken, equipmentNumber, mechanicName, from, to));
			serviceResponse.setResponseCode(200);
		} catch (Exception e) {
			serviceResponse.setResponseCode(500);
			serviceResponse.setResponseDescription(e.getMessage());
		}
		return serviceResponse;
	}

	@RequestMapping(method = RequestMethod.DELETE,
			produces = {"application/json"})
	@ResponseBody
	public ApiResponse deleteOrder(@RequestParam(value = "id") Long orderId, @RequestParam(value = "at") String authtoken){
		ApiResponse serviceResponse = new ApiResponse();
		try {
			ordersService.deleteOrder(orderId, authtoken);
			serviceResponse.setResponseCode(200);
		} catch (Exception e) {
			serviceResponse.setResponseCode(500);
			serviceResponse.setResponseDescription(e.getMessage());
		}
		return serviceResponse;
	}
}


