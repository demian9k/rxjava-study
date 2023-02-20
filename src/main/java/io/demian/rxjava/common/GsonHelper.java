package io.demian.rxjava.common;

import com.google.gson.JsonParser;

public class GsonHelper {
	public static String parseValue(String json, String key) { 
		return JsonParser.parseString(json)
				.getAsJsonObject()
				.get(key)
				.getAsString();
	}
}
