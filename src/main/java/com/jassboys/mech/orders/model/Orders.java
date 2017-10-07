package com.jassboys.mech.orders.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="US/Arizona")
    private Date orderDate;

    @Column(name = "equipment_number")
    private String equipmentNumber;

    @Column(name = "equipment_type")
    private String equipmentType;

    @Column(name = "labor_hours")
    private Float laborHours;

    @Column(name = "labor_hourly_rate")
    private Float laborHourlyRate;

    @Column(name = "mechanic_name")
    private String mechanicName;

    @Column(name = "tyre_type")
    private String tyreType;

    @Column(name = "num_of_tyres")
    private Integer numOfTyres;

    @Column(name = "tyre_price")
    private Float tyrePrice;

    @Column(name = "part_cost")
    private Float partCost;

    @Column(name = "part_invoice")
    private String partInvoice;

    @Column(name = "part_vendor")
    private String partVendor;

    @Column(name = "part_description")
    private String partDescription;

    @Column(name = "lubricant_type")
    private String lubricantType;

    @Column(name = "lubricant_quantity")
    private Float lubricantQuantity;

    @Column(name = "lubricant_price")
    private Float lubricantPrice;

    @Column(name = "total_charges")
    private Float totalCharges;

    @Column(name = "notes")
    private String notes;

    @Column(name = "mod_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modDateTime;

    @Column(name = "mod_by_user")
    private String modifiedByUser;

    @Column(name = "creation_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;

    @Column(name = "created_by_user")
    private String createdByUser;


}
