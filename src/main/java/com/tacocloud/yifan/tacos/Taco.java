package com.tacocloud.yifan.tacos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Table(name = "taco")
public class Taco implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@CreationTimestamp
	private Date createAtDate;
	
	@NotNull
	@Size(min = 5, message = "名字必須超過5個字！")
	private String name;
	
	@ManyToOne
	private TacoOrder tacoOrder;
	
	@NotNull
	@Size(min = 1, message = "必須勾選至少1種！")
	@ManyToMany
	@JoinTable(
		    name = "taco_ingredient",
		    joinColumns = @JoinColumn(name = "taco_id", referencedColumnName = "id"),
		    inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
	private List<Ingredient> ingredients = new ArrayList<>();
	
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}
}
