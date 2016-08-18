package com.feifanuniv.collegeebook.mapper;

import java.util.List;
import java.util.Map;

import com.feifanuniv.collegeebook.entity.CollegeCourseEbook;

 public interface CollegeCourseEbookMapper {
	 List<CollegeCourseEbook> selectCollegeCourse(int collegeId);
	 List<String>  selectCollegeCourseEbookSSId(CollegeCourseEbook collegeCourseEbook);
	 int updateCollegeCourseEbook(CollegeCourseEbook collegeCourseEbook);
	 int insertCollegeCourseEbook(List<CollegeCourseEbook> collegeCourseEbook);
	 int deleteCollegeCourseEbook(CollegeCourseEbook collegeCourseBook);
}
