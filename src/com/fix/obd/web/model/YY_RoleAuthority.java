package com.fix.obd.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="role_authority")
public class YY_RoleAuthority {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="rolename",nullable=false,columnDefinition="varchar(50)")
	private String rolename;
	@Column(name="authorityid",nullable=false,columnDefinition="varchar(50)")
	private String authorityid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getAuthorityid() {
		return authorityid;
	}
	public void setAuthorityid(String authorityid) {
		this.authorityid = authorityid;
	}

}
