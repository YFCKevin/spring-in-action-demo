package com.tacocloud.yifan.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		// 使用registry的addViewController()，將"/"傳遞進去，viewController會根據該路徑執行GET請求。
		// 返回ViewControllerRegistration，然後使用setViewName()，指定請求“/"時要到"home" view圖。
		registry.addViewController("/").setViewName("home");
		
		// 沒有設定視圖名稱，這表示當用戶訪問"/login"路徑時，沒有指定特定的視圖。這通常意味著控制器會執行一些後台操作，例如驗證用戶身份等。
		registry.addViewController("/login");
	}
}
