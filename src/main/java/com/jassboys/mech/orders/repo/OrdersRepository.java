package com.jassboys.mech.orders.repo;


import com.jassboys.mech.orders.model.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Long> {

    List<Orders> findByEquipmentNumberAndOrderDateBetween(String equimentNumber, Date from, Date to);

    List<Orders> findByMechanicNameAndOrderDateBetween(String mechanicName, Date from, Date to);

    List<Orders> findByEquipmentNumberAndMechanicNameAndOrderDateBetween(String equimentNumber, String mechanicName, Date from, Date to);

    List<Orders> findByOrderDateBetween(Date from, Date to);

}
