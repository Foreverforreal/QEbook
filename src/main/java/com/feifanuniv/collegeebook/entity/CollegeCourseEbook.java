package com.feifanuniv.collegeebook.entity;

public class CollegeCourseEbook extends College {
	private int id;
	private int collegeId;
	private String courseId;
	private String courseName;
	private String bookName;
	private String SSId;
	private int ebookCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getSSId() {
		return SSId;
	}
	public void setSSId(String sSId) {
		SSId = sSId;
	}
	public int getEbookCount() {
		return ebookCount;
	}
	public void setEbookCount(int ebookCount) {
		this.ebookCount = ebookCount;
	}
	@Override
	public String toString() {
		return "CollegeCourseEbook [id=" + id + ", collegeId=" + collegeId + ", courseId=" + courseId + ", courseName="
				+ courseName + ", bookName=" + bookName + ", SSId=" + SSId + ", ebookCount=" + ebookCount + "]";
	}	
		
}
