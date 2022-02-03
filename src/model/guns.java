/**
 * @author ${Josiah Anderson} - janderson78@dmacc.edu
 * CIS175 - Fall 2021
 * ${2/2/2022}
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="guns")
public class guns {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="COMPANY")
	private String company;
	
	@Column(name="TYPE")
	private String type;
	
	
	
	public guns() {
		super();
	}
	public guns(String company, String type) {
		super();
		this.setCompany(company);
		this.setType(type);
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String gunDetails() {
		return "Company: " + this.company + ", Type: " + this.type;
	}
}
