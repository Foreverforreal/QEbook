package com.feifanuniv.collegeebook.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feifanuniv.collegeebook.entity.ResponsetJsonData;

public class Ufs {
	
	private HttpPost httppost;
	private StringEntity entity;
	
	public Ufs(String url,String requestJson) {
		super();
		this.httppost = new HttpPost(url);
		try {
			this.entity =new StringEntity(requestJson);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public ResponsetJsonData getData() throws JsonParseException, JsonMappingException, ParseException, IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		httppost.setEntity(entity);
		httppost.addHeader("Content-Type","application/json;charset=utf-8");
		CloseableHttpResponse response = httpclient.execute(httppost);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(new CapitalizedPropertyNamingStrategy());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		HttpEntity responseEntity = response.getEntity();
		
		response.close();
		httpclient.close();	
		
		return mapper.readValue(EntityUtils.toString(responseEntity, "UTF-8"), ResponsetJsonData.class);
	}
	
	
	

}
