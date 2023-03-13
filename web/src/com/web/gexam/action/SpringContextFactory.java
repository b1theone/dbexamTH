package com.web.gexam.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextFactory {
	private static String[] configFiles = { "classpath:com/server/gexam/conf/application-context.xml" };
	private static ApplicationContext ctx;

	public SpringContextFactory() {
	}

	public static ApplicationContext getCurrentContext() throws Exception {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext(configFiles);
		}
		return ctx;
	}

	public static void initialContext() throws Exception {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext(configFiles);
		}
	}

	public static void cleanContext() throws Exception {
		if (ctx != null) {
			((ClassPathXmlApplicationContext) ctx).close();
		}
	}
}
