package com.ejemplo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * JP Entity to carry out data from H2 PRICES table
 */
@Setter
@Getter
@Entity
@Table(name ="prices")
public class InditextPrice {

    @Id
    @GeneratedValue
    private Long colID;
    
	private Integer brandID;
	private Date startDate;
	private Date endDate;
	private String priceList;
	private String productId;
	private Short priority;
	private Float price;
	private String curr;

}
