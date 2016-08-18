package com.feifanuniv.collegeebook.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feifanuniv.collegeebook.entity.College;
import com.feifanuniv.collegeebook.entity.CollegeCourseEbook;
import com.feifanuniv.collegeebook.entity.Ebook;
import com.feifanuniv.collegeebook.entity.MajorTeachingplanCourse;
import com.feifanuniv.collegeebook.entity.ResponsetJsonData;
import com.feifanuniv.collegeebook.mapper.CollegeCourseEbookMapper;
import com.feifanuniv.collegeebook.mapper.CollegeMapper;
import com.feifanuniv.collegeebook.mapper.EbookMapper;
import com.feifanuniv.collegeebook.utils.CapitalizedPropertyNamingStrategy;
import com.feifanuniv.collegeebook.utils.TeachingplanCache;

public class CollegeAndCourseEbookService {
	
	@Autowired
	private CollegeMapper collegeMapper;
	@Autowired
	private CollegeCourseEbookMapper collegeCourseEbookMapper;
	@Autowired
	private EbookMapper ebookMapper;
	@Autowired
	private TeachingplanCache cache;
	
	
	public List<College> selectAllCollege(){
		return collegeMapper.selectAllCollege();
	}
	
	public List<CollegeCourseEbook> selectCollegeCourse(int collegeId){
		return collegeCourseEbookMapper.selectCollegeCourse(collegeId);
	}
	
	public List<String> selectCollegeCourseEbookSSId(CollegeCourseEbook collegeCourseEbook){
		return collegeCourseEbookMapper.selectCollegeCourseEbookSSId(collegeCourseEbook);
	}
	public List<Ebook> selectEbook(List<String> SSId){
		return ebookMapper.selectEbookBySSId(SSId);
	}
	public List<Ebook> selectEbookByAllFields(Ebook ebook){
		return ebookMapper.selectEbookByAllFields(ebook);
	}
	;
	public int insertCollegeCourseEbook(List<CollegeCourseEbook> collegeCourseEbook){
		return collegeCourseEbookMapper.insertCollegeCourseEbook(collegeCourseEbook);
	}
	
	public int deleteCollegeCourseEbook(CollegeCourseEbook collegeCourseBook){
		return collegeCourseEbookMapper.deleteCollegeCourseEbook(collegeCourseBook);
	}
	
	
	
	public ResponsetJsonData getAllCurrentTeachingplanCourse(String schoolUrl,String code){
		if(cache.get(schoolUrl)==null){
			String requestJson1=String.format("{'CollegeUrl':'%s','Batch':'161','Level':'高起专'}", schoolUrl);
			String requestJson2=String.format("{'CollegeUrl':'%s','Batch':'161','Level':'高起本'}", schoolUrl);
			String requestJson3=String.format("{'CollegeUrl':'%s','Batch':'161','Level':'专升本'}", schoolUrl);
			
			long start = System.currentTimeMillis();    
			
			ResponsetJsonData result=queryUfs(requestJson1);
			if(!"404".equals(result.getHR())){
			result.getData().addAll(queryUfs(requestJson2).getData());
			result.getData().addAll(queryUfs(requestJson3).getData());	
			}
			long end = System.currentTimeMillis();    
			System.out.println(schoolUrl+"所有教学计划获取时间："+(end-start)/1000+"秒");   
		
			//setCoursewareAvailability(schoolUrl.substring(schoolUrl.indexOf("edu.www.")+8),result);
			setCoursewareAvailability(code,result);
			cache.put(schoolUrl, result);
			return result;
			
		}else{
			return cache.get(schoolUrl);
		}
	}
	
	public ResponsetJsonData getTeachingplanByBatch(String requestJson,String code){
		
		if(cache.get(requestJson)==null){
			ResponsetJsonData result=queryUfs(requestJson);			
			setCoursewareAvailability(code,result);
			cache.put(requestJson, result);
			return result;
		}else { 
			return cache.get(requestJson);
		}
	};
	
	
	
	public ResponsetJsonData queryUfs(String requestJson){ 
		
	        CloseableHttpClient httpclient = HttpClients.createDefault();  
	        HttpPost httppost = new HttpPost("http://ufs.service.qingshupad.com/v3/CDS/GetAllMajorTeachPlanCourses"); 
	        	        
	        CloseableHttpResponse response = null;
			try {			
			long start = System.currentTimeMillis();
			
			if(cache.get(requestJson)==null){	
				StringEntity entity= new StringEntity(requestJson);
				httppost.setEntity(entity);
				httppost.addHeader("Content-Type","application/json;charset=utf-8");
				response = httpclient.execute(httppost);
				
				if (response.getStatusLine().getStatusCode()==200){
				ObjectMapper mapper = new ObjectMapper();
				mapper.setPropertyNamingStrategy(new CapitalizedPropertyNamingStrategy());
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				HttpEntity responseEntity = response.getEntity();	
				
				long end = System.currentTimeMillis();  
				System.out.println("该批次教学计划获取时间："+(end-start)/1000+"秒");
				
				ResponsetJsonData result= mapper.readValue(EntityUtils.toString(responseEntity, "UTF-8"), ResponsetJsonData.class);	
				cache.put(requestJson, result);
				return result;
				}else{
					return new ResponsetJsonData("404", "没有请求到教学计划", null, null);
				}
			}else { 
				return cache.get(requestJson);
			}
				} catch (IOException e) {
						e.printStackTrace();
						}finally {  
							try {
								if(response!=null)response.close();
								if(httpclient!=null)httpclient.close();				            
							} catch (IOException e) {
								e.printStackTrace();
							}  
				            }  
				        					
		return null;  
    }   
	
	
	public void setCoursewareAvailability(String code,ResponsetJsonData data){
		HashMap<String, Boolean> courseMap=new HashMap<>();
		
	
		if(data.getData()==null){
		}else{
	
			for (MajorTeachingplanCourse majorTeachingplanCourse : data.getData()) {
				
				String courseId= majorTeachingplanCourse.getCourseId();
				
				if(courseMap.get(courseId)==null){
					String courseUrl = "http://7u2k2m.com2.z0.glb.qiniucdn.com/cdn/"+code+"/course/" + majorTeachingplanCourse.getCourseId() + "/course.xml";	
					URL url;
					
					try {
						
						url = new URL(courseUrl);
						HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
						httpURLConnection.setRequestMethod("HEAD");
						httpURLConnection.setDoOutput(true);
						
						if(httpURLConnection.getResponseCode()==200){ 
							courseMap.put(courseId, true);
							majorTeachingplanCourse.setCourseWareStatus(true);
						}else{
							courseMap.put(courseId, false); 
							majorTeachingplanCourse.setCourseWareStatus(false);
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}	
			    }else{
			    	majorTeachingplanCourse.setCourseWareStatus(courseMap.get(courseId));
			    };	
				
			}
			
		}
		
	}
	
}