package com.jassboys.mech.orders.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class ApiResponse implements Serializable {

	private static final long serialVersionUID = -7793768106494854750L;
	
	private int responseCode;
	private String responseDescription;
	private Orders order;
	private String authToken;
	private List<Orders> ordersList;
	private Map<String, List<String>> lookupData;
	
}
