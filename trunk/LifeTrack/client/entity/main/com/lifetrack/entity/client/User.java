package com.lifetrack.entity.client;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lifetrack.core.entity.BaseEntity;



@Entity
@Table(name = "user")
public class User extends BaseEntity {
	
	private String name;
	private int loginTimes;
	private Date lastLoginTime;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
