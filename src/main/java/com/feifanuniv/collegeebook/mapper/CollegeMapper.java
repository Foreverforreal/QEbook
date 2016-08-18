package com.feifanuniv.collegeebook.mapper;

import java.util.List;

import com.feifanuniv.collegeebook.entity.College;

 public interface CollegeMapper {
	 List<College> selectAllCollege();
	 List<College> selectCollegeByName(String CollegeName);
	 List<College> selectCollegeByCode(String Code);
	 List<College> selectCollegeById(int Id);
	 int deleteCollege(int Id);
	 int insertCollege(College college);
	 int updateCollege(College college);
}
