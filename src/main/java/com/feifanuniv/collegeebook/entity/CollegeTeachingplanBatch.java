package com.feifanuniv.collegeebook.entity;

public class CollegeTeachingplanBatch {
	private String collegeUrl;
	private String batch;
	private String level;
	
	public String getCollegeUrl() {
		return collegeUrl;
	}
	public void setCollegeUrl(String collegeUrl) {
		this.collegeUrl = collegeUrl;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "TeachingplanCourseRequestJson [collegeUrl=" + collegeUrl + ", batch=" + batch + ", level=" + level
				+ "]";
	}
	
	
}
