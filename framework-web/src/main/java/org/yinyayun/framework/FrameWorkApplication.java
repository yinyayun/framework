/**
 * 
 */
package org.yinyayun.framework;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yinyayun
 *
 */
@EnableAutoConfiguration
@ComponentScan
// 有了上面两个注解，Spring会默认查找FrameWorkApplication类对应包下的所有实体（包括子包）
public class FrameWorkApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication springApplication = new SpringApplication(FrameWorkApplication.class);
		try (InputStream in = new FileInputStream("conf/application.properties")) {
			Properties properties = new Properties();
			properties.load(in);
			springApplication.setDefaultProperties(properties);
		}
		springApplication.run(args);
	}
}
