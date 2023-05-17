package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import spring.common.web.LogonCheckInterceptor;

//===================== 추가된 Class  ======================//
// Interceptor 등록하는 WebMvcCongigurer 구현 Bean
//=======================================================//
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	///Field
	@Autowired
	private LogonCheckInterceptor logonCheckInterceptor;

	///Constructor
	public WebConfig() {
		System.out.println("==> WebConfig default Constructor call.............");
	}

	//Method
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// URL Pattern 을 확인하고. interceptor 적용유무 등록함.
		registry.addInterceptor( logonCheckInterceptor ).addPathPatterns("/user/**");
		
	}

}
