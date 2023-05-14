package com.tacocloud.yifan.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableGlobalMethodSecurity
//public class SecurityConfig{
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		// 確保認證過的USER才能對"/design" and "/orders"發出請求，而其他URL請求不設定阻攔。
//		return http.authorizeRequests().antMatchers("/design", "/orders").access("hasRole('USER')")
//				.antMatchers("/", "/**").access("permitAll()")
//				
//				// 使用formLogin()方法定義客製化的登錄頁面，且當USER沒有經過驗證就會導向"/login"該頁面
//				// loginProcessingUrl("./authenticate")表示使用POST方式將登錄表單提交到"./authenticate"路徑進行處理
//				// usernameParameter("user")和passwordParameter("pwd")則定義了提交的表單中用於傳遞使用者名稱和密碼的參數名稱
//				// defaultSuccessUrl("/design", true)表示在驗證成功後將使用者重定向到"/design"頁面
//				.and()
//					.formLogin()
//						.loginPage("/login")
//						.loginProcessingUrl("./authenticate")
//						.usernameParameter("user")
//						.passwordParameter("pwd")
//						.defaultSuccessUrl("/design", true)
//				
//				// 登出後會退回首頁
//				.and()
//					.logout()
//						.logoutSuccessUrl("/")
//				
//				.and().build();
//	}
//}
