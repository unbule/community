package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootApplication
public class CommunityApplication {

	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		SpringApplication.run(CommunityApplication.class, args);

//		String str = "hello world";
//		Method m = str.getClass().getMethod("substring", int.class, int.class);
//		String res = (String) m.invoke(str, 1,7);
//		System.out.println(res);
	}

}
