package com.jassboys.mech.orders.model;

import com.jassboys.mech.orders.util.BooleanToStringConverter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    @Convert(converter=BooleanToStringConverter.class)
    private Boolean active;

    @Column(name = "created_by_user")
    private String createdByUser;

}
