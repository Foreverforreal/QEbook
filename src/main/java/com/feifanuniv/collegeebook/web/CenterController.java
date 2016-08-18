package com.feifanuniv.collegeebook.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feifanuniv.collegeebook.entity.College;
import com.feifanuniv.collegeebook.entity.CollegeCourseEbook;
import com.feifanuniv.collegeebook.entity.CollegeCourseEbookList;
import com.feifanuniv.collegeebook.entity.Ebook;
import com.feifanuniv.collegeebook.entity.ResponsetJsonData;
import com.feifanuniv.collegeebook.service.CollegeAndCourseEbookService;

@Controller
public class CenterController {
	
	@Autowired
	private CollegeAndCourseEbookService collegeAndCourseEbookService;
	
	@RequestMapping("/GetAllCollege")
	public @ResponseBody List<College> getSchoolList(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return collegeAndCourseEbookService.selectAllCollege();
	}
	@RequestMapping("/GetCollegeCourse")
	public @ResponseBody List<CollegeCourseEbook> selectCollegeCourse(int collegeId,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return collegeAndCourseEbookService.selectCollegeCourse(collegeId);
	}
	@RequestMapping("/GetCourseEbook")
	public @ResponseBody List<Ebook> selectCourseEbook(CollegeCourseEbook collegeCourseEbook,HttpServletResponse response){
		List<String> SSId=collegeAndCourseEbookService.selectCollegeCourseEbookSSId(collegeCourseEbook);
		for (int a=0;a<SSId.size();a++) {
			if(SSId.get(a)==null){
				SSId.remove(a);
			}
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		return (SSId.size()==0) ?  null : collegeAndCourseEbookService.selectEbook(SSId);
	}
	@RequestMapping("/GetEbook")
	public @ResponseBody List<Ebook> selectEbook(HttpServletResponse response,Ebook ebook){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return collegeAndCourseEbookService.selectEbookByAllFields(ebook);
	}
	
	@RequestMapping("/AddCollegeCourseEbook")
	public @ResponseBody int addCollegeCourseEbook(HttpServletResponse response, CollegeCourseEbookList collegeCourseEbookList){
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println(collegeCourseEbookList.getCcelist());
		return collegeAndCourseEbookService.insertCollegeCourseEbook(collegeCourseEbookList.getCcelist());
	}
	@RequestMapping("/DeleteCollegeCourseEbook")
	public @ResponseBody int deleteCollegeCourseEbook(HttpServletResponse response,CollegeCourseEbook collegeCourseBook){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return collegeAndCourseEbookService.deleteCollegeCourseEbook(collegeCourseBook);
	}
	
	@RequestMapping("/GetTeachingPlan")
	public @ResponseBody ResponsetJsonData getTeachingPlan(HttpServletResponse response,String para,int type,String code){
		response.setHeader("Access-Control-Allow-Origin", "*");
		if(type==1){
			return collegeAndCourseEbookService.getAllCurrentTeachingplanCourse(para,code);
		}
		if(type==2){
			return collegeAndCourseEbookService.getTeachingplanByBatch(para,code);
		}
		
		return null;
	}

}
