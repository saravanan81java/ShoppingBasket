package com.shopping.basket.apps.services;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService {

	void buyProducts(String product);
	
	void shoppingTotalCalCulation();

	void loadProductWithPrice(Map<String, Integer> listOfProductWithPrice);

	void setPromDetails(Map<String, List<Integer>> listOfPromo);

	void clearListOfPuchaseProduct();
}
