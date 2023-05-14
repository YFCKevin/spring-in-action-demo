package com.tacocloud.yifan.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.tacocloud.yifan.tacos.Ingredient;
import com.tacocloud.yifan.tacos.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient>{
	
//	private Map<String, Ingredient> ingredientMap = new HashMap<>();
	
	@Autowired
	private IngredientRepository ingredientRepo;

	@Override
	public Ingredient convert(String id) {
//		return ingredientMap.get(id);
		return ingredientRepo.findById(id).orElse(null);
	}
	
}
