package com.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.crm.domain.BaseDict;
import com.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * �ֵ���
 * @author �����
 *
 */
public class BaseDictAtion extends ActionSupport implements ModelDriven<BaseDict>{

	private BaseDict baseDict = new BaseDict();

	@Override
	public BaseDict getModel() {
		// TODO Auto-generated method stub
		return baseDict ;
	}
	private BaseDictService baseDictService;

	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	/**
	 * �����ֵ�����ѯ
	 * @return
	 */
	public String findByTypeCode() {
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		//��listתΪjson,,��Ϊ�첽
		/**
		 * JSONConfig:תJSON�����ö���
		 * JSONArray:�������list����תΪJSON
		 * JSONObject:�������Map����תΪJSON
		 */
		JsonConfig jsonConfig = new JsonConfig();
		//һ�����Բ���Ҫ��ѯ����
		jsonConfig.setExcludes(new String[] {"dict_sort","dict_enable","dict_memo"});
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		//��JSON���͵�ҳ��
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}

}
