package com.yoouman.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ptype")
public class PType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8573764830732511341L;
	// 商品类型编号
	@Id
	@Column(name = "type_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tId;
	// 类型名称
	@Column(name = "type_name")
	private String tName;
	// 类型简介
	@Column(name = "type_Desc")
	private String tDesc;
	// 类型计量单位（件、本、个、kg、g、张、）
	@Column(name = "type_Unit")
	private String tUnit;

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String gettDesc() {
		return tDesc;
	}

	public void settDesc(String tDesc) {
		this.tDesc = tDesc;
	}

	public String gettUnit() {
		return tUnit;
	}

	public void settUnit(String tUnit) {
		this.tUnit = tUnit;
	}

	public PType(String tName, String tDesc, String tUnit) {
		super();
		this.tName = tName;
		this.tDesc = tDesc;
		this.tUnit = tUnit;
	}

	public PType() {
		super();
	}

}
