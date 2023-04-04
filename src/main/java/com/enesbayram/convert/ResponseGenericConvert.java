package com.enesbayram.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseGenericConvert {
	
	//Servisten dönen response değeri tek bir tipte yakalayabilmek için geliştirdiğim generic yapı...

	public static <T> Object jsonToObject(String json, Class<?> baseClass, Class<?> genericType) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JavaType convertType = mapper.getTypeFactory().constructParametricType(baseClass, genericType);
		try {
			return mapper.readValue(json, convertType);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	//JFIlacGeneralResponse<ArrayList<JFIlacHistory>>
	public static <T> Object jsonToObject(String json, Class<?> baseClass, Class<?> list, Class<?> genericType) {
		ObjectMapper mapper = new ObjectMapper();
		JavaType listGenericType = mapper.getTypeFactory().constructParametricType(list, genericType);
		JavaType baseClassGenericType = mapper.getTypeFactory().constructParametricType(baseClass, listGenericType);

		try {
			return mapper.readValue(json, baseClassGenericType);
		} catch (JsonMappingException e) {
			System.out.println(e.getMessage());
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static void main(String[] args) {
		//********************ÖRNEK KULLANIM********************
//		Object objectResponse = (JFIlacSuccessResponse<List<JFIlacHistory>>) jsonToObject(json,
//                JFIlacSuccessResponse.class, ArrayList.class, JFIlacHistory.class);
		
		//--------------------------------------------------------------
		
//		 response = (JFIlacSuccessResponse<JFIlacHistory>)
//                 jsonToObject(jsonValueToken, JFIlacSuccessResponse.class, JFIlacHistory.class);
	}
}
