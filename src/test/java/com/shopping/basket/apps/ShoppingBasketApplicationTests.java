package com.shopping.basket.apps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shopping.basket.apps.services.ShoppingCartService;

@SpringBootTest
class ShoppingBasketApplicationTests {

	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	//List<String> listOfPuchaseProduct = java.util.Arrays.asList("Apple", "Apple", "Orange", "Apple", "Banana", "Pineapple", "Banana", "Orange", "Banana");
	
	private static String ORANGE = "Orange";
	private static String APPLE = "Apple";
	private static String BANANA = "Banana";
	private static String PINEAPPLE = "Pineapple";
	
	Map<String,Integer> listOfProductWithPrice = new HashMap<>();
	
	Map<String,List<Integer>> listOfPromo = new HashMap<>();
	
	@Test
	void contextLoads() {
	}

	@BeforeEach
	void beforeEach() {
		
		//Master Datas
		
		//Apple, Pineapple, Orange, Banana
		listOfProductWithPrice.put(APPLE, 12);
		listOfProductWithPrice.put(PINEAPPLE, 32);
		listOfProductWithPrice.put(BANANA, 51);
		listOfProductWithPrice.put(ORANGE, 95);

		//Product with Price
		this.shoppingCartService.loadProductWithPrice(this.listOfProductWithPrice);
		
		// Apple buy one get one
		// Bananas are three for two
		this.listOfPromo.put(APPLE, Arrays.asList(1,1));
		this.listOfPromo.put(BANANA, Arrays.asList(3,2));
		this.listOfPromo.put(PINEAPPLE, Arrays.asList(0,0));
		this.listOfPromo.put(ORANGE, Arrays.asList(0,0));
		
		this.shoppingCartService.setPromDetails(this.listOfPromo);
	}
	
	@Test
	void loadTestCases() {
		//System.out.println(listOfPuchaseProduct);
		//System.out.println(listOfProductWithPrice);
	}
	
	@Test
	void buyActualProduct(){
		
		System.out.println("\n");
		
		shoppingCartService.clearListOfPuchaseProduct();
		
		shoppingCartService.buyProducts(APPLE);
		shoppingCartService.buyProducts(APPLE);
		shoppingCartService.buyProducts(ORANGE);
		shoppingCartService.buyProducts(APPLE);
		 
		shoppingCartService.buyProducts(PINEAPPLE);
		shoppingCartService.buyProducts(BANANA);
		shoppingCartService.buyProducts(ORANGE);
		shoppingCartService.buyProducts(BANANA);
		
		
		shoppingCartService.shoppingTotalCalCulation();
	}
	
	
	@Test
	void buyProduct(){
		
		shoppingCartService.clearListOfPuchaseProduct();
		
		shoppingCartService.buyProducts(APPLE);
		shoppingCartService.buyProducts(APPLE);
		shoppingCartService.buyProducts(ORANGE);
		shoppingCartService.buyProducts(APPLE);
		 
		shoppingCartService.buyProducts(BANANA);
		shoppingCartService.buyProducts(PINEAPPLE);
		shoppingCartService.buyProducts(BANANA);
		shoppingCartService.buyProducts(ORANGE);
		shoppingCartService.buyProducts(BANANA);
		
		
		shoppingCartService.shoppingTotalCalCulation();
	}
}
