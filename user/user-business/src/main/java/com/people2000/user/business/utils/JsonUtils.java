package com.people2000.user.business.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 使用Gson把json字符串转成Map
 * 
 * @author
 * @date
 */
public class JsonUtils {

	/**
	 * 获取JsonObject
	 * 
	 * @param json
	 * @return
	 */
	public static JsonObject parseJson(String json) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObj = parser.parse(json).getAsJsonObject();
		return jsonObj;
	}

	/**
	 * 获取JsonObject
	 * 
	 * @param json
	 * @return
	 */
	public static JSONArray parseJsonArray(String json) {
		return JSONArray.parseArray(json);
	}

	/**
	 * 根据json字符串返回Map对象
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> toMap(String json) {
		return JsonUtils.toMap(JsonUtils.parseJson(json));
	}

	/**
	 * 将JSONObjec对象转换成Map-List集合
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> toMap(JsonObject json) {
		Map<String, Object> map = new HashMap<String, Object>();
		Set<Entry<String, JsonElement>> entrySet = json.entrySet();
		for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter
				.hasNext();) {
			Entry<String, JsonElement> entry = iter.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof JsonArray)
				map.put(key, toList((JsonArray) value));
			else if (value instanceof JsonObject)
				map.put(key, toMap((JsonObject) value));
			else
				map.put(key, value);
		}
		return map;
	}

	/**
	 * 将JSONArray对象转换成List集合
	 * 
	 * @param json
	 * @return
	 */
	public static List<Object> toList(JsonArray json) {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < json.size(); i++) {
			Object value = json.get(i);
			if (value instanceof JsonArray) {
				list.add(toList((JsonArray) value));
			} else if (value instanceof JsonObject) {
				list.add(toMap((JsonObject) value));
			} else {
				list.add(value);
			}
		}
		return list;
	}

	/**
	 * @param obj
	 *            对象
	 * @return obj的json字符串
	 */
	public static String objectToJsonString(Object obj) {
		return JSON.toJSONString(obj);
	}

	/**
	 * openAPI使用
	 */
	public static <T> T JsonStringToObject(String jsonStr, Class<T> cl) {
		return JSON.parseObject(jsonStr, cl);
	}

}