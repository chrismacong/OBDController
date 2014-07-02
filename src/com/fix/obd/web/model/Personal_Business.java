package com.fix.obd.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="personal_business")
public class Personal_Business {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	
	@ManyToOne(targetEntity=YY_User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="personal_id", referencedColumnName="id")
    private YY_User personal_user;
    
    @ManyToOne(targetEntity=YY_User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="business_id", referencedColumnName="id")
    private YY_User business_user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public YY_User getPersonal_user() {
		return personal_user;
	}

	public void setPersonal_user(YY_User personal_user) {
		this.personal_user = personal_user;
	}

	public YY_User getBusiness_user() {
		return business_user;
	}

	public void setBusiness_user(YY_User business_user) {
		this.business_user = business_user;
	}
}
