package com.tacocloud.yifan.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tacocloud.yifan.tacos.Ingredient;
import com.tacocloud.yifan.tacos.Ingredient.Type;
import com.tacocloud.yifan.tacos.Taco;
import com.tacocloud.yifan.tacos.TacoOrder;
import com.tacocloud.yifan.tacos.data.IngredientRepository;
import com.tacocloud.yifan.tacos.data.TacoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
//@SessionAttributes(types=TacoOrder.class)
@Controller
public class DesignTacoController {
	@Autowired
	private IngredientRepository ingredientRepo;
	@Autowired
	private TacoRepository tacoRepo;
	
	@ModelAttribute(name="tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	@ModelAttribute(name="taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		List<Ingredient> ingredients = new ArrayList<>();
		// 透過interface取用findAll()方法，拿到資料庫中所有的ingredient，再跑forEach個別放入ArrayList中
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		
		Type[] types = Ingredient.Type.values();
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),filterByType((List<Ingredient>) ingredients, type));
		}
		
		System.out.println("@GetMapping結合class級別的@RequestMapping，當接收到/design的HTTP GET請求時，Spring MVC會調用showDesignForm()來處理請求。");
		return "design";
	}
	
	// @Valid 會告知Spring MVC要對使用者提交的Taco進行驗證，驗證時機是在綁定完表單資料之後，且調用processTaco()之前進行。
	// 如果存在驗證錯誤，錯誤細節會放入Errors裏面，並傳送給processTaco()
	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
		
		// processTaco()會先查閱Errors，調用hasErrors()判斷是否有錯誤，如果有錯，就不會處理Taco domain，直接返回design view
		if (errors.hasErrors()) {
			return "design";
		}
		
		// 利用tacoRepo interface取用save()方法，將前端送過來的taco名稱存進資料庫
		Taco saved = tacoRepo.save(taco);
		// 再將taco資料名稱存進tacoOrder
		tacoOrder.addTaco(saved);

		System.out.println("新的方法來處理針對/design的POST請求。");
		return "redirect:/orders/current";
	}
	
	private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
		return ingredients.stream()
						.filter(x -> x.getType().equals(type))
						.collect(Collectors.toList());
	}
		
	
}
