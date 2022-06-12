package com.shopping.basket.apps.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	
	List<String> listOfPuchaseProduct = null;
	Map<String,Integer > listOfProductWithPrice = null;
	Map<String,List<Integer>> listOfPromo = new HashMap<>();
	
	public ShoppingCartServiceImpl() {
		// TODO Auto-generated constructor stub
		if(listOfPuchaseProduct == null) {
			listOfPuchaseProduct = new ArrayList<>();
		}
		
		if(listOfProductWithPrice == null) {
			listOfProductWithPrice = new HashMap<>();
		}
	}
	
	@Override
	public void buyProducts(String product) {
		this.listOfPuchaseProduct.add(product);
	}

	@Override
	public void clearListOfPuchaseProduct() {
		this.listOfPuchaseProduct = new ArrayList<String>();
	}

	 

	@Override
	public void shoppingTotalCalCulation() {
		// TODO Auto-generated method stub
		
		System.out.println("*********************SHOPPING CART START****************************");
		
		System.out.println("Shopping Cart product details : " + listOfPuchaseProduct + "\n");
		
		Map<String, Long> result = listOfPuchaseProduct.stream().collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()
        ));
 
		result = new TreeMap<String, Long>(result);
		 	
		System.out.println("Item with Quantity : "+result + "\n");
		double  totalAmount = Double.valueOf("0.0");
		int totalQty = 0;
		
		for (Map.Entry<String, Long> mapEntery : result.entrySet()) {
			String productName = mapEntery.getKey();
			Long productQty = mapEntery.getValue();
			
			//Get Product Price
			Integer price = this.listOfProductWithPrice.get(productName);
			List<Integer> totOfferWithQuty = this.listOfPromo.get(productName);
			
			double subTotal = new Double("0.0");
			int offerQty = 0;
			
			if( totOfferWithQuty != null && !totOfferWithQuty.isEmpty())
			{
				// 1 by 1 or 3 buy 2
				Integer buyItemQty =  totOfferWithQuty.get(0);
				Integer getItemQty = totOfferWithQuty.get(1);
			
				if(buyItemQty > 0) {
					int quotient = (int) (productQty / buyItemQty);
			        if(quotient > 0)
			        {
			        	offerQty = (int) (quotient * getItemQty);
			        }	
				}
				
			}
	        
	        subTotal = subTotal + (productQty * price);
			totalAmount = totalAmount + subTotal;
			totalQty = (int) (totalQty + offerQty);
			
			System.out.println(String.format("Product Name [%s] , actual items count [%s], offer items count [%s] and Total amount :[%sp]",
					productName, productQty, offerQty, subTotal));
		} 
		
		System.out.println("\n" + String.format( "Shopping details actual Items counts [%s], with offer Items counts [%s], and overall Total of Items in the basket[%s],"
				+ " Total Shopping Amount :  %sp", this.listOfPuchaseProduct.size(), totalQty, (this.listOfPuchaseProduct.size() + totalQty), 
				totalAmount));
		
		System.out.println("*********************SHOPPING CART END******************************");
		
		/*
		
		for (String string : listOfPuchaseProduct) {
			
			Integer price= this.listOfProductWithPrice.get(string);
			if(!string.isEmpty() && "Apple".equals(string)) {
				System.out.println("Item 1    :[" +string +"]  Price :" + price +"p");
				System.out.println("     Free :[" +string +"]  Price :" + price +"p");
				totalAmount = totalAmount + (price  * 2);
			}
			
			if(!string.isEmpty() && "Banana".equals(string)) {
				System.out.println("Item 1    :[" +string +"]  Price :" + price +"p");
				totalAmount = totalAmount + (price  * 1);
			}
			
		}
		System.out.println(String.format( "Shopping Total Items [%s], Amount [%sp] ", this.listOfPuchaseProduct.size() , totalAmount));
		*/
	}

	@Override
	public void loadProductWithPrice(Map<String, Integer> listOfProductWithPrice) {
		// TODO Auto-generated method stub
		this.listOfProductWithPrice = listOfProductWithPrice;
	}

	@Override
	public void setPromDetails(Map<String, List<Integer>> listOfPromo) {
		// TODO Auto-generated method stub
		this.listOfPromo = listOfPromo;
	}

}
