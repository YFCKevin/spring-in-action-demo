package com.tacocloud.yifan.tacos.data;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

import com.tacocloud.yifan.tacos.Taco;
import com.tacocloud.yifan.tacos.TacoOrder;

public interface TacoRepository extends CrudRepository<Taco, Integer>{

	List<Taco> findAll(PageRequest page);

	TacoOrder save(TacoOrder tacoOrder);

}
