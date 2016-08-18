package com.feifanuniv.collegeebook.entity;

public class MajorTeachingplanCourse {
	private String majorId;
	private String majorName;
	private short semester;
	private String courseId;
	private String courseName;
	private String level;
	private boolean courseWareStatus;
	
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public short getSemester() {
		return semester;
	}
	public void setSemester(short semester) {
		this.semester = semester;
	}
	
	public boolean isCourseWareStatus() {
		return courseWareStatus;
	}
	public void setCourseWareStatus(boolean courseWareStatus) {
		this.courseWareStatus = courseWareStatus;
	}
	@Override
	public String toString() {
		return "MajorTeachingplanCourse [majorId=" + majorId + ", majorName=" + majorName + ", semester=" + semester
				+ ", courseId=" + courseId + ", courseName=" + courseName + ", level=" + level + ", courseWareStatus="
				+ courseWareStatus + "]";
	}
	
}
