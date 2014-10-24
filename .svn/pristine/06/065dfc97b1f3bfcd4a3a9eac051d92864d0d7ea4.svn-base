package com.fix.obd.web.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ThtApplicationContext implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		if (ThtApplicationContext.applicationContext == null) {
			ThtApplicationContext.applicationContext = applicationContext;
			System.out.println();
			System.out.println();
			System.out
			.println("---------------------------------------------------------------------");
			System.out
			.println("========ApplicationContext配置成功,在普通类可以通过调用ToolSpring.getAppContext()获取applicationContext对象,applicationContext="
					+ applicationContext + "========");
			System.out
			.println("---------------------------------------------------------------------");
			System.out.println();
			System.out.println();
		}
	}
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}
}