package br.leosilvadev.gchat.utils

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.ObjectMapper

class ExtensionUtils {

	static void extendObjects(){
		ObjectMapper jsonMapper = new ObjectMapper()
		jsonMapper.setSerializationInclusion(Include.NON_NULL)
		Object.metaClass.toJson {
			jsonMapper.writeValueAsString(delegate)
		}
	}
	
}
