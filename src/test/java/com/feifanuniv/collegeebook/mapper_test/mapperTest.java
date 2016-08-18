package com.feifanuniv.collegeebook.mapper_test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.feifanuniv.collegeebook.mapper.CollegeCourseEbookMapper;
import com.feifanuniv.collegeebook.mapper.CollegeMapper;
import com.feifanuniv.collegeebook.mapper.EbookMapper;

public class mapperTest {
	private SqlSessionFactory sqlSessionFactory=null;

	@Before
	public void setUp() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("file:E:\\workspace-sts-3.7.1.RELEASE\\QEbook\\src\\main\\resources\\spring\\application-config.xml");
		sqlSessionFactory=(SqlSessionFactory)applicationContext.getBean("sqlSessionFactory");
	}

	@Test
	public void test() {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		CollegeMapper collegeMapper=sqlSession.getMapper(CollegeMapper.class);
		CollegeCourseEbookMapper collegeCourseEbookMapper=sqlSession.getMapper(CollegeCourseEbookMapper.class);
		EbookMapper ebookMapper=sqlSession.getMapper(EbookMapper.class);
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("---------------------------CollegeMapper-test------------------------");
		System.out.println("---------------------------------------------------------------------");
		System.out.println(collegeMapper.selectAllCollege());
		System.out.println(collegeMapper.selectCollegeByName("交通"));
		System.out.println(collegeMapper.selectCollegeByCode("zsdx"));
		System.out.println(collegeMapper.selectCollegeById(2));
		System.out.println("---------------------------------------------------------------------");
		
		System.out.println("\n");
		System.out.println("---------------------------------------------------------------------");
		System.out.println("---------------------------CollegeCourseEbookMapper-test-------------");
		System.out.println("---------------------------------------------------------------------");
		//System.out.println(collegeCourseEbookMapper.selectCollegeCourseEbookSSId(1));
		System.out.println("---------------------------------------------------------------------");
		
		System.out.println("\n");
		System.out.println("---------------------------------------------------------------------");
		System.out.println("---------------------------EbookMapper-test--------------------------");
		System.out.println("---------------------------------------------------------------------");
		System.out.println(ebookMapper.selectAllEbook());
		System.out.println(ebookMapper.selectEbookByBookName("数学"));
//		System.out.println(ebookMapper.selectEbookBySSId();
		System.out.println(ebookMapper.selectEbookById(2));
		System.out.println("---------------------------------------------------------------------");
	}

}
