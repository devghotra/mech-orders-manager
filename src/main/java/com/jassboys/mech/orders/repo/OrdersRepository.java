package com.jassboys.mech.orders.repo;


import com.jassboys.mech.orders.model.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Long> {

    List<Orders> findByEquipmentNumberAndOrderDateBetweenOrderByIdDesc(String equimentNumber, Date from, Date to);

    List<Orders> findByMechanicNameAndOrderDateBetweenOrderByIdDesc(String mechanicName, Date from, Date to);

    List<Orders> findByEquipmentNumberAndMechanicNameAndOrderDateBetweenOrderByIdDesc(String equimentNumber, String mechanicName, Date from, Date to);

    List<Orders> findByOrderDateBetweenOrderByIdDesc(Date from, Date to);

}
