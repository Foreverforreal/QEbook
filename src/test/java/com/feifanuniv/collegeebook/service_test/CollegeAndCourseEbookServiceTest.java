package com.feifanuniv.collegeebook.service_test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.feifanuniv.collegeebook.entity.ResponsetJsonData;
import com.feifanuniv.collegeebook.service.CollegeAndCourseEbookService;

public class CollegeAndCourseEbookServiceTest {
	CollegeAndCourseEbookService collegeAndCourseEbookService =new CollegeAndCourseEbookService();
	@Before
	public void setUp() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("file:E:\\workspace-sts-3.7.1.RELEASE\\QEbook\\src\\main\\resources\\spring\\application-config.xml");
		collegeAndCourseEbookService=(CollegeAndCourseEbookService) applicationContext.getBean("collegeAndCourseEbookService");
	}

	@Test
	public void testGetTeachingplanCourseByBatch() {
		String requestJson="{'CollegeUrl':'edu.www.sxsf','Batch':'161','Level':'高起专'}";
		//System.out.println(collegeAndCourseEbookService.getTeachingplanByBatch(requestJson).getData());
	}
	
	@Test
	public void testGetAllCurrentTeachingplanCourseh() {
		String requestJson="edu.www.sxsf";
		//System.out.println(collegeAndCourseEbookService.getAllCurrentTeachingplanCourse(requestJson).getData());
	}

}
