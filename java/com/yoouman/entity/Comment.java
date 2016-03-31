package com.yoouman.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.yoouman.util.CustomDateSerializer;

@Entity
@Table(name = "comment")
public class Comment {
	// 评价id
	@Id
	@Column(name = "comment_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	// 评价内容
	@Column(name = "comment_Content",columnDefinition="longtext")
	private String cContent;
	// 评价时间
	@Column(name = "comment_Date")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date cDate;
	// 评价人 外键：用户表id
	@ManyToOne(cascade = { CascadeType.ALL })
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "user_Id")
	private User owner;
	@ManyToOne(cascade = { CascadeType.ALL })
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "product_Id")
	private Product product;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	
	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public User getUser() {
		return owner;
	}

	public void setUser(User user) {
		this.owner = user;
	}

	public Comment(String cContent, Date cDate, User user) {
		super();
		this.cContent = cContent;
		this.cDate = cDate;
		this.owner = user;
	}

	public Comment() {
		super();
	}

}
