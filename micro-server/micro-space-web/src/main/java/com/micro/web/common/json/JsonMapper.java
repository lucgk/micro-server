package com.micro.web.common.json;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonMapper {

	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	public static final JsonMapper INSTANCE = new JsonMapper();

	private ObjectMapper mapper;
	private static final String space = "    ";
	private static final String spaceOne = " ";
	private static final char line = '\n';

	public JsonMapper() {
		this(null);
	}

	public JsonMapper(Include include) {
		mapper = new ObjectMapper();
		// 设置输出时包含属性的风格
		if (include != null) {
			mapper.setSerializationInclusion(include);
		}
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// 解决json字符串中key没有加引号的问题
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}

	/**
	 * 创建只输出非Null的属性到Json字符串的Mapper.
	 */
	public static JsonMapper nonNullMapper() {
		return new JsonMapper(Include.NON_NULL);
	}

	/**
	 * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper.
	 * 
	 * 注意，要小心使用, 特别留意empty的情况.
	 */
	public static JsonMapper nonEmptyMapper() {
		return new JsonMapper(Include.NON_EMPTY);
	}

	public static JsonMapper nonDefaultMapper() {
		return new JsonMapper(Include.NON_DEFAULT);
	}

	/**
	 * 默认的全部输出的Mapper, 区别于INSTANCE，可以做进一步的配置
	 */
	public static JsonMapper defaultMapper() {
		return new JsonMapper();
	}

	/**
	 * Object可以是POJO，也可以是Collection或数组。 如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".
	 */
	public String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * 反序列化POJO或简单Collection如List<String>.
	 * 
	 * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
	 * 
	 * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String, JavaType)
	 * 
	 * @see #fromJson(String, JavaType)
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 反序列化复杂Collection如List<Bean>,
	 * contructCollectionType()或contructMapType()构造类型, 然后调用本函数.
	 * 
	 * @see #createCollectionType(Class, Class...)
	 */
	@SuppressWarnings("unchecked")
	public <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return (T) mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 构造Collection类型.
	 */
	public JavaType buildCollectionType(@SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass,
			Class<?> elementClass) {
		return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
	}

	/**
	 * 构造Map类型.
	 */
	public JavaType buildMapType(@SuppressWarnings("rawtypes") Class<? extends Map> mapClass, Class<?> keyClass,
			Class<?> valueClass) {
		return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
	}

	/**
	 * 当JSON里只含有Bean的部分属性時，更新一個已存在Bean，只覆盖該部分的属性.
	 */
	public void update(String jsonString, Object object) {
		try {
			mapper.readerForUpdating(object).readValue(jsonString);
		} catch (JsonProcessingException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		} catch (IOException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		}
	}

	/**
	 * 輸出JSONP格式數據.
	 */
	public String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}

	/**
	 * 設定是否使用Enum的toString函數來讀寫Enum, 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
	 * 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
	 */
	public void enableEnumUseToString() {
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
	}

	/**
	 * 取出Mapper做进一步的设置或使用其他序列化API.
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
	
	/**
	 * 返回格式化JSON字符串。
	 * 
	 * @param json
	 *            未格式化的JSON字符串。
	 * @return 格式化的JSON字符串。
	 */
	public String formatJson(String content) throws Exception {
		if (StringUtils.isBlank(content)) {
			return content;
		}
		JSONObject obj = JSONObject.parseObject(content);
		if (obj == null) {
			throw new Exception("非标准JSON格式");
		}
		String json = obj.toJSONString();
		StringBuffer result = new StringBuffer();
		int length = json.length();
		int number = 0;
		char key = 0;
		for (int i = 0; i < length; i++) {
			key = json.charAt(i);
			if ((key == '[') || (key == '{')) {
				result.append(key);
				result.append(line);
				number++;
				result.append(indent(number));
				continue;
			}
			if ((key == ']') || (key == '}')) {
				result.append(line);
				number--;
				result.append(indent(number));
				result.append(key);
				continue;
			}
			if ((key == ',') && (i + 1 < length) && (json.charAt(i + 1) == '"')) {
				if (json.charAt(i - 2) == ':') {
					result.append(key);
				} else {
					result.append(key);
					result.append(line);
					result.append(indent(number));
				}
				continue;
			}
			if (key == ':' && (i + 1 < length)
					&& ((json.charAt(i + 1) == '"' || json.charAt(i + 1) == '{' || json.charAt(i + 1) == '['))) {
				result.append(key);
				result.append(spaceOne);
				continue;
			}
			result.append(key);
		}
		return result.toString();
	}

	/**
	 * 返回指定次数的缩进字符串。每一次缩进三个空格，即SPACE。
	 * 
	 * @param number
	 *            缩进次数。
	 * @return 指定缩进次数的字符串。
	 */
	private String indent(int number) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < number; i++) {
			result.append(space);
		}
		return result.toString();
	}

	/**
	 * 把格式化的JSON紧凑
	 * 
	 * @param content
	 * @return
	 */
	public String compactJson(String content) {
		if (StringUtils.isBlank(content)) {
			return content;
		}
		String regEx = "\r|\n";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(content);
		return m.replaceAll("").trim();
	}
}
