package com.jassboys.mech.orders.model;

import com.jassboys.mech.orders.util.BooleanToStringConverter;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Lookup {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;

}
