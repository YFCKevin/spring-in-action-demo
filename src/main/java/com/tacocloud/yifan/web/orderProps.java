package com.tacocloud.yifan.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "tacos.orders")
@Data
@Validated
public class orderProps {
	
	@Min(value = 5, message = "必須在5 - 25之間")
	@Max(value = 25, message = "必須在5 - 25之間")
	
	private int pageSize = 20;
}
