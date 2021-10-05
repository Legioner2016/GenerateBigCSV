package com.bigcsv.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table(name = "test")
@Data
public class MyTestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Column(name = "owner_id")
	private Integer ownerId;
	
	@Column(name = "string1")
	private String string1;

	@Column(name = "string2")
	private String string2;

	@Column(name = "string3")
	private String string3;

	@Column(name = "string4")
	private String string4;

	@Column(name = "string5")
	private String string5;

	@Column(name = "int1")
	private Integer int1;

	@Column(name = "int2")
	private Integer int2;
	
	@Column(name = "int3")
	private Integer int3;

	@Column(name = "int4")
	private Integer int4;
	
	@Column(name = "int5")
	private Integer int5;
	
	@Column(name = "numeric1", scale = 2, precision = 5)
	private BigDecimal numeric1;

	@Column(name = "numeric2", scale = 2, precision = 5)
	private BigDecimal numeric2;

	@Column(name = "numeric3", scale = 2, precision = 5)
	private BigDecimal numeric3;

	@Column(name = "numeric4", scale = 2, precision = 5)
	private BigDecimal numeric4;

	@Column(name = "numeric5", scale = 2, precision = 5)
	private BigDecimal numeric5;

	
}
