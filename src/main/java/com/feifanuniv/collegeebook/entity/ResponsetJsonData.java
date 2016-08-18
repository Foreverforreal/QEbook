package com.feifanuniv.collegeebook.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponsetJsonData {
	@JsonProperty("HR")
	private String HR;
	private String errorMessage;
	private String remark;
	private List<MajorTeachingplanCourse> data;
	
	

	public ResponsetJsonData() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ResponsetJsonData(String hR, String errorMessage, String remark, List<MajorTeachingplanCourse> data) {
		super();
		HR = hR;
		this.errorMessage = errorMessage;
		this.remark = remark;
		this.data = data;
	}
	
	
	public String getHR() {
		return HR;
	}
	public void setHR(String hR) {
		HR = hR;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<MajorTeachingplanCourse> getData() {
		return data;
	}
	public void setData(List<MajorTeachingplanCourse> data) {
		this.data = data;
	}
	

}
