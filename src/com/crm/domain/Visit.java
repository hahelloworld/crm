package com.crm.domain;

import java.util.Date;

/**
 * �ͻ��ݷü�¼���û��Ϳͻ����м��,�Ƕ��һ��
 * @author �����
 *
 */
public class Visit {
	private String visit_id;//�ݷ���ID
	private Integer visit_cust_id;//�ͻ�ID
	private Integer visit_user_id;//�û�ID
	private Date visit_time;//�ݷ�ʱ��
	private String visit_addr;//�ݷõص�
	private String visit_detail;//�ݷ�����
	private Date visit_nexttime;//�´ΰݷ�ʱ��
	
	private Customer customer;
	private User user;
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getVisit_id() {
		return visit_id;
	}
	public void setVisit_id(String visit_id) {
		this.visit_id = visit_id;
	}
	public Integer getVisit_cust_id() {
		return visit_cust_id;
	}
	public void setVisit_cust_id(Integer visit_cust_id) {
		this.visit_cust_id = visit_cust_id;
	}
	public Integer getVisit_user_id() {
		return visit_user_id;
	}
	public void setVisit_user_id(Integer visit_user_id) {
		this.visit_user_id = visit_user_id;
	}
	public Date getVisit_time() {
		return visit_time;
	}
	public void setVisit_time(Date visit_time) {
		this.visit_time = visit_time;
	}
	public String getVisit_addr() {
		return visit_addr;
	}
	public void setVisit_addr(String visit_addr) {
		this.visit_addr = visit_addr;
	}
	public String getVisit_detail() {
		return visit_detail;
	}
	public void setVisit_detail(String visit_detail) {
		this.visit_detail = visit_detail;
	}
	public Date getVisit_nexttime() {
		return visit_nexttime;
	}
	public void setVisit_nexttime(Date visit_nexttime) {
		this.visit_nexttime = visit_nexttime;
	}
	
}
