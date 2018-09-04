package com.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.LinkMan;
import com.crm.domain.PageBean;

public interface LinkManService {

	void save(LinkMan linkMan);

	PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
