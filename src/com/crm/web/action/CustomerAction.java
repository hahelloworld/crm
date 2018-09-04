package com.crm.web.action;



import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
import com.crm.utils.UploadUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	//使用set方法接收数据
	private Integer currPage=1;//当前页数
	private Integer pageSize=3;//每页多少记录数

	public void setCurrPage(Integer currPage) {
		if(currPage == null)
			currPage = 1;
		this.currPage = currPage;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize == null)
			pageSize = 3;
		this.pageSize = pageSize;
	}
	/**
	 * 为文件上传提供的三个属性
	 */
	private String uploadFileName;//文件名称
	private File upload;
	private String uploadContentType;//文件类型 
	

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	/**
	 * 新增客户，跳转到保存页面
	 * @return
	 */
	public String saveUI() {
		
		return "saveUI";
	}
	/**
	 * 保存客户
	 * @return
	 */
	public String save() {
		//上传文件
		if(upload!=null) {
			//指定一下上传的路径
			String path="C:/upload";
			//如果上传的文件，因为文件名相同而被覆盖住怎么办，，所以生成一个随机的文件名，后缀不变
			String uuidFileName = UploadUtil.getUuidFileName(uploadFileName);
			//目录分离
			String realPath = UploadUtil.getPath(uuidFileName);
			String url = path+realPath;
			//手动创建目录
			File file = new File(url);
			if(!file.exists()) {
				//不存在，就创建
				file.mkdirs();
			}
			try {
				//文件上传
				File dicFile = new File(url+"/"+uuidFileName);
				FileUtils.copyFile(upload, dicFile);
				//设置客户资质图片的值
				customer.setCust_image(url+"/"+uuidFileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		customerService.save(customer);
		return "save";
	}
	/**
	 * 
	 * 查询客户
	 * @return
	 */
	public String findAll() {
		/*//接收参数：分页参数
		//使用DetachedCriteria对象，它提供了分页参数
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		PageBean<Customer> pageBean = customerService.findAByPage(detachedCriteria,currPage,pageSize);
		//得到数据之后，我们需要将数据传到页面上去，因此需要使用值栈
		ActionContext.getContext().getValueStack().push(pageBean);*/
		
		//改写，这个时候提供了条件筛选
		//使用DetachedCriteria对象，它提供了分页参数
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		//设置条件,然后添加到DetachedCriteria中
		//名称筛选
		if(customer.getCust_name()!=null)
			detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		System.out.println("-----------------------------------------------"+customer.getCust_name());
		
		//customer.getBaseDictSource()!=null如果为null，代表没有选
		if(customer.getBaseDictSource()!=null)
			if(customer.getBaseDictSource().getDict_id()!=null && !"".equals(customer.getBaseDictSource().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}
		
		if(customer.getBaseDictLevel()!=null && customer.getBaseDictLevel().getDict_id()!=null && !"".equals(customer.getBaseDictLevel().getDict_id()))
			detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
		
		if(customer.getBaseDictIndustry()!=null && customer.getBaseDictIndustry().getDict_id()!=null && !"".equals(customer.getBaseDictIndustry().getDict_id()))
			detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
		
		if(customer.getCust_phone()!=null)
			detachedCriteria.add(Restrictions.like("cust_phone", "%"+customer.getCust_phone()+"%"));
		if(customer.getCust_mobile()!=null)
			detachedCriteria.add(Restrictions.like("cust_mobile", "%"+customer.getCust_mobile()+"%"));
		//这里我们也需要数据回显
		PageBean<Customer> pageBean = customerService.findAllByPage(detachedCriteria,currPage,pageSize);
		//得到数据之后，我们需要将数据传到页面上去，因此需要使用值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	/**
	 * 删除客户信息
	 * @return
	 */
	public String delete() {
		//先查询，再删除
		customer = customerService.findById(customer.getCust_id());
		//删除图片
		if(customer.getCust_image()!=null) {
			File file = new File(customer.getCust_image());
			if(file.exists()) {
				file.delete();
			}
		}
		//首先，得接收一个客户的ID
		customerService.delete(customer);
		return "deleteSuccess";
	}
	/**
	 * 修改客户
	 * @return
	 */
	public String edit() {
		//修改客户时，我们需要先将客户回显 
		//获取ID，跳转页面
		customer = customerService.findById(customer.getCust_id());
		ActionContext.getContext().getValueStack().push(customer);
		return "editSuccess";
	}
	/**
	 * 保存修改后的客户
	 * @return
	 */
	public String update() {
		//再选择上传文件时，首先判断一下，是否有上传的文件
		if(upload!=null) {//此时已经选择了上传文件的按钮
			//要删除
			String cust_image = customer.getCust_image();
			if(cust_image!=null || !"".equals(cust_image)) {
				File file = new File(cust_image);
				file.delete();
			}
			//否则的话，上传
			//指定一下上传的路径
			String path="C:/upload";
			//如果上传的文件，因为文件名相同而被覆盖住怎么办，，所以生成一个随机的文件名，后缀不变
			String uuidFileName = UploadUtil.getUuidFileName(uploadFileName);
			//目录分离
			String realPath = UploadUtil.getPath(uuidFileName);
			String url = path+realPath;
			//手动创建目录
			File file = new File(url);
			if(!file.exists()) {
				//不存在，就创建
				file.mkdirs();
			}
			try {
				//文件上传
				File dicFile = new File(url+"/"+uuidFileName);
				FileUtils.copyFile(upload, dicFile);
				//设置客户资质图片的值
				customer.setCust_image(url+"/"+uuidFileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		customerService.update(customer);
		return "save";
	}
	public String findAllCustomer() throws IOException {
		List<Customer> list = customerService.findAll();
		//list转json
		JsonConfig jsonConfig = new JsonConfig();
		//这些是不需要的
		jsonConfig.setExcludes(new String[] {"linkMans","baseDictSource","baseDictLevel","baseDictIndustry"});
		//转json
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		System.out.println(jsonArray.toString());
		return NONE;
	}
}
