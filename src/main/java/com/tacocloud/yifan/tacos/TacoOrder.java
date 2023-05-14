package com.tacocloud.yifan.tacos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.tacocloud.yifan.security.User;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Table(name = "taco_order")
public class TacoOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Date placeAt;
	
	@ManyToOne
	private User user;
	
	@NotBlank(message = "deliveryName必填")
	private String deliveryName;
	
	@NotBlank(message = "deliveryStreet必填")
	private String deliveryStreet;
	
	@NotBlank(message = "deliveryCity必填")
	private String deliveryCity;
	
	@NotBlank(message = "deliveryState必填")
	private String deliveryState;
	
	@NotBlank(message = "deliveryZip必填")
	private String deliveryZip;
	
	@CreditCardNumber(message = "非合法卡號")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
	           message="Must be formatted MM/YY")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tacoOrder")
	private List<Taco> tacos = new ArrayList<>();
	
	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}
}
