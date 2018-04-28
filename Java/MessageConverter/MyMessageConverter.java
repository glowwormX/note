package com.self.common;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.self.controller.TestController;
import com.self.dao.mes.UserMapper;
import com.self.entity.mes.User;

public class MyMessageConverter extends AbstractHttpMessageConverter<Object> {


	public final static Charset UTF8 = Charset.forName("UTF-8");

	public MyMessageConverter() {
		// 设置我们媒体类型
		super(new MediaType("application", "json", UTF8));
	}

	// 标明本HttpMessageConverter处理所有类，过滤在这些
	@Override
	protected boolean supports(Class<?> aClass) {
		return true;
	}

	//发起请求，反序列化时调用
	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		System.out.println("readInternal");

		return null;
	}

	//放回数据，序列化时调用
	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		System.out.println("writeInternal");
		Class<? extends Object> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			//自定义注解UserNoToName
			if (field.isAnnotationPresent(UserNoToName.class)) {
				try {
					field.setAccessible(true);
					String val = field.get(obj)==null?"":(String)field.get(obj);
//					field.set(obj, "df1");
//					field.set(obj, userMapper.findByLoginName(val).getDisplayName());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		outputMessage.getBody().write(JSON.toJSONBytes(obj, 
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteMapNullValue, 
				SerializerFeature.WriteNullStringAsEmpty));
	}

}
