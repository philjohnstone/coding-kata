package com.example;

import static com.example.CheckoutTest.Stock.APPLE;
import static com.example.CheckoutTest.Stock.ORANGE;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.example.Checkout.ItemsNotForSaleException;
import com.example.Checkout.StockItem;

public class CheckoutTest {

	enum Stock {
		APPLE("Apple", 60),
		ORANGE("Orange", 25);
		String name;
		long price;
		Stock(String name, long price) {
			this.name = name;
			this.price = price;
		}
	}
	
	private List<StockItem> stockItems = Arrays.asList(new StockItem[] {
		new StockItem(APPLE.name, APPLE.price, 2),
		new StockItem(ORANGE.name, ORANGE.price, 3)
	});

	@Test
	public void testAppleAndOrange() {
		Assert.assertEquals("Total", APPLE.price + ORANGE.price, new Checkout(stockItems).scanItems(APPLE.name, ORANGE.name));
	}
	
	@Test
	public void testEmptyCheckout() {
		Assert.assertEquals("Total", 0, new Checkout(stockItems).scanItems());
	}
	
	@Test
	public void testSampleShop() {
		Assert.assertEquals("Total", APPLE.price + 0 + ORANGE.price + APPLE.price, new Checkout(stockItems).scanItems(APPLE.name, APPLE.name, ORANGE.name, APPLE.name));
	}
	
	@Test
	public void testItemNotForSale() {
		try {
			new Checkout(stockItems).scanItems("Apple", "Banana", "Carrot");
			Assert.fail("Banana and Carrot should not be for sale");
		} catch (ItemsNotForSaleException e) {
			Assert.assertEquals("Items 'Carrot,Banana' are not for sale", e.getMessage());
		}
	}
	
	@Test
	public void testAppleSale() {
		Assert.assertEquals("Total", APPLE.price + 0 + APPLE.price, new Checkout(stockItems).scanItems(APPLE.name, APPLE.name, APPLE.name));
	}
	
	@Test
	public void testOrangeSale() {
		Assert.assertEquals("Total", ORANGE.price + ORANGE.price + 0 + ORANGE.price, new Checkout(stockItems).scanItems(ORANGE.name, ORANGE.name, ORANGE.name, ORANGE.name));	
	}
}
