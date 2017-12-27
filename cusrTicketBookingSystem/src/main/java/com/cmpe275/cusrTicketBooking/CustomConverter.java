package com.cmpe275.cusrTicketBooking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

	public class CustomConverter implements AttributeConverter<List<String>,String>{

	    private final static ObjectMapper objectMapper = new ObjectMapper();

		public String convertToDatabaseColumn(List<String> attribute) {
			String result = "";
			try {
				result = objectMapper.writeValueAsString(attribute);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return result;
		}

		public List<String> convertToEntityAttribute(String data) {
			List<String> result = new ArrayList<String>();
			if(data == null || data.equals("null")) {
				return null;
			}
			try {
				
				result = Arrays.asList(objectMapper.readValue(data,
				        String[].class));
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}

		

	}

