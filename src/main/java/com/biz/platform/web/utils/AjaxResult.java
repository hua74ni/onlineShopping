/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2016 Bosssoft Co, Ltd.
 * All rights reserved.
 * 
 * Created on 2016年9月22日
 *******************************************************************************/


package com.biz.platform.web.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * Ajax应答结果
 *
 * @author
 */
/*
 * 修改历史
 * $Log$
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class AjaxResult implements UnwrappResponseBody,java.io.Serializable{
	
	/*回调类型常量*/
	/**
	 * 回调类型，关闭当前页签
	 */
	public static final String CALLBACK_CLOSESELF = "closeSelf";
	
	/**
	 * 回调类型,刷新表格数据
	 */
	public static final String CALLBACK_REFRESHTABLE = "refreshTable";
	
	/**
	 * 回调类型,刷新当前页面
	 */
	public static final String CALLBACK_REFRESHPAGE = "refreshPage";
	
	/**
	 * 回调类型,跳转到指定页面
	 */
	public static final String CALLBACK_FORWARDURL = "forwardUrl";
	
	/**
	 * 回调类型,调用method属性指定的方法
	 */
	public static final String CALLBACK_CALLMETHOD = "callMethod";
	
	/*ajax结果常量*/
	
	/**
	 * 成功状态码
	 */
	public static final int STATUS_SUCCESS = 200;
	
	/**
	 * 错误状态码
	 */
	public static final int STATUS_ERROR = 300;
	
	/**
	 * 超时状态码
	 */
	public static final int STATUS_TIMEOUT = 301;
		
	/**
	 * 操作成功
	 */
	public static final AjaxResult SUCCESS = new AjaxResult(STATUS_SUCCESS,"操作成功");
	/**
	 * 操作失败
	 */
	public static final AjaxResult ERROR = new AjaxResult(STATUS_ERROR,"操作失败");
	/**
	 * 操作超时
	 */
	public static final AjaxResult TIMEOUT = new AjaxResult(STATUS_TIMEOUT,"操作超时");
	
    /**
     * 状态码
     */
    private int statusCode = STATUS_SUCCESS;
    
    
    /** 消息*/
    private String message = null;
   
    /**
     * 原因
     */
    private String causeBy=null;
    /**
     * 回调类型
     */
    private List<String> callBackTypes = null;
    
    /**
     * 回调方法
     */
    private String method = null;
    
    /**
     * 判断数据对象是否需要翻译
     */
    private boolean needTranslate = false;
    
    /**
     * 自定义AJAX标识
     */
    private String type="__custom";
    
    /**
     * 值
     */
    private Object data = null;
    
    /**
     * 翻译内容(K->字段名,V->对应数据字典Map)
     */
    private Map<String,Map<String,String>> translateBodys;

    /**
     * 构造方法
     */
    public AjaxResult(){
    	
    }
    
    /**
     * 构造器
     * @param statusCode 状态码
     * @param message 响应消息
     */
    public AjaxResult(int statusCode,String message){
    	this(statusCode,message,null);
    }
    
    /**
     * 构造器
     * @param statusCode 状态码
     * @param data 响应数据对象
     */
    public AjaxResult(int statusCode,Object data){
    	this(statusCode,"",data,false);
    }
    
    /**
     * 构造器
     * @param statusCode 状态码
     * @param message 响应消息
     * @param data 响应数据对象
     */
    public AjaxResult(int statusCode,String message,Object data){
    	this(statusCode,message,data,false);
    }
    
    /**
     * 构造器
     * @param statusCode 状态码
     * @param message 响应消息
     * @param data 响应数据对象
     * @param needTranslate 是否需要数据状态
     */
    public AjaxResult(int statusCode,String message,Object data,boolean needTranslate){
    	this.statusCode = statusCode;
    	this.message = message;
    	this.data = data;
    	this.needTranslate = needTranslate;
    }
    
    /**
     * 成功
     * @param data 响应数据对象
     * @return
     */
    public static AjaxResult success(Object data){
    	return new AjaxResult(STATUS_SUCCESS,data);
    }
    /**
     * 失败
     * @param data 响应数据对象
     * @return
     */
    public static AjaxResult error(Object data){
    	return new AjaxResult(STATUS_ERROR,data);
    }
    
    /**
     * 获取状态码
     * @return
     */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * 设置状态码
	 * @param statusCode
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * 获取响应消息
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置响应消息
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	
	/**
	 * 获取异常原因
	 * @return Returns the causeBy.
	 */
	public String getCauseBy() {
		return causeBy;
	}

	/**
	 * 设置异常原因
	 * @param causeBy The causeBy to set.
	 */
	public void setCauseBy(String causeBy) {
		this.causeBy = causeBy;
	}

	/**
	 * 设置回调类型列表
	 * @param callBackTypes 回调类型列表
	 */
	public void setCallBackTypes(List<String> callBackTypes) {
		this.callBackTypes = callBackTypes;
	}
	
	/**
	 * 添加回调类型
	 * @param callBackType 回调类型
	 */
	public void addCallBackType(String callBackType){
		if(callBackTypes == null)
			callBackTypes = new ArrayList<String>();
		callBackTypes.add(callBackType);
	}
	
	/**
	 * 清空回调类型
	 */
	public void clearCalllBackTypes(){
		if(callBackTypes != null)
			callBackTypes.clear();
	}
	
	public String getCallbackType() {
		if(callBackTypes == null || callBackTypes.size() == 0)
			return null;
		return StringUtils.join(callBackTypes,',');
	}


	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return Returns the data.
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data The data to set.
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return Returns the needTranslate.
	 */
	public boolean isNeedTranslate() {
		return needTranslate;
	}


	public void setNeedTranslate(boolean needTranslate) {
		this.needTranslate = needTranslate;
	}

	/**
	 * @return Returns the callBackTypes.
	 */
	public List<String> getCallBackTypes() {
		return callBackTypes;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return Returns the translateBodys.
	 */
	public Map<String, Map<String, String>> getTranslateBodys() {
		return translateBodys;
	}

	/**
	 * @param translateBodys The translateBodys to set.
	 */
	public void setTranslateBodys(Map<String, Map<String, String>> translateBodys) {
		this.translateBodys = translateBodys;
	}

	
    
}

/*
 * 修改历史
 * $Log$ 
 */