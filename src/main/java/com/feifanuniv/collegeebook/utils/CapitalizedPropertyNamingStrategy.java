package com.feifanuniv.collegeebook.utils;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;

public class CapitalizedPropertyNamingStrategy extends PropertyNamingStrategyBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String translate(String propertyName) {
		 String name = propertyName.replaceAll("^\\w", propertyName.toUpperCase().substring(0,1));
	        return name;
	}

}
