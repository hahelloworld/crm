package com.crm.dao.impl;


import com.crm.dao.CustomerDao;
import com.crm.domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
	
	public CustomerDaoImpl() {
		super(Customer.class);
	}
	/**
	 * �����û�
	 *//*
	@Override
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}*/
	/**
	 * ��ѯ�ܼ�¼��,��������Ӵ������˵�����ϣ��Ա𣬣�����
	 */
	/*@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		//����select count(*) from ... where ...
		detachedCriteria.setProjection(Projections.rowCount());
		 List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		 if(list.size()>0)
			 return list.get(0).intValue();
		return null;
	}*/
	/**
	 * ��ҳ��ѯ�ͻ�
	 */
	/*@Override
	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		//��������,findCount�еģ�Ĭ�ϲ�����
		detachedCriteria.setProjection(null);
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
		return list;
	}*/
	/**
	 * ���ݿͻ�ID�����ҿͻ�
	 */
	/*@Override
	public Customer findById(Long cust_id) {
		Customer customer = this.getHibernateTemplate().get(Customer.class, cust_id);
		return customer;
	}*/
	/**
	 * ��ѯ֮��ɾ���ͻ�
	 *//*
	@Override
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
		
	}
	*//**
	 * �����û�
	 *//*
	@Override
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);;
	}*/
	/*@Override
	public List<Customer> findAll() {
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer");
		return list;
	}
*/


}
