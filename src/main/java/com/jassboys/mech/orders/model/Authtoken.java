package com.jassboys.mech.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authtoken {

    @Id
    @Column(name = "authtoken")
    private String authtoken;

    @Column(name = "username")
    private String userName;

    @Column(name = "token_life")
    private Integer tokenLifeMins;


    @Column(name = "creation_date_time", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;

    @Column(name = "mod_date_time", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modDateTime;

}
