package com.tacocloud.yifan.tacos.data;

import org.springframework.data.repository.CrudRepository;

import com.tacocloud.yifan.tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{
	
//	Iterable<Ingredient> findAll();
//	
//	Optional<Ingredient> findById(String id);
//	
//	Ingredient save(Ingredient ingredient);
}
