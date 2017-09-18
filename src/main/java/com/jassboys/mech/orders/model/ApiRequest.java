package com.jassboys.mech.orders.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiRequest implements Serializable {

	private static final long serialVersionUID = 5919871743941682945L;
	
	private String username;
	private String password;
	private String truckHaulerNumber;
	private String driverName;
	private String primeCarrier;
	private String customer;

}
