package com.example;

import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Checkout {
	
	private final Map<String, StockItem> stock;
	private final List<StockItem> discounts;
	
	private final Predicate<String> notForSale;
	private final Function<String, Long> itemToPrice;
	
	public Checkout(List<StockItem> stockItems) {
		stock = stockItems.stream().collect(toMap((item) -> item.name, (item) -> item));
		discounts = stockItems.stream().filter((stockItem) -> stockItem.discountMultiplier != 0).collect(toList()); 
		notForSale = (item) -> !stock.containsKey(item);
		itemToPrice = (item) -> stock.get(item).price;
	}

	public long scanItems(String... items) {
		Set<String> itemsNotForSale = asList(items).stream().filter(notForSale).collect(Collectors.toSet());
		long savings = calculateSavings(items);
		if (itemsNotForSale.isEmpty()) {
			long subtotal = asList(items).stream().map(itemToPrice).reduce(Long::sum).orElseGet(() -> 0L); 
			return subtotal - savings;
		} else {
			throw new ItemsNotForSaleException(itemsNotForSale.toArray(new String[itemsNotForSale.size()]));
		}
	}

	private long calculateSavings(String... items) {
		Map<String, Long> itemCount = asList(items).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return discounts.stream().map((discount) -> itemCount.getOrDefault(discount.name, 0L) / discount.discountMultiplier * discount.price).reduce(Long::sum).orElseGet(() -> 0L);
	}
	
	static class ItemsNotForSaleException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public ItemsNotForSaleException(String... items) {
			super(format("Items '%s' are not for sale", join(",", items)));
		}
	}
	
	static class StockItem {
		final String name;
		final long price;
		final int discountMultiplier;
		public StockItem(String name, long price, int discountMultiplier) {
			this.name = name;
			this.price = price;
			this.discountMultiplier = discountMultiplier;
		}
	}
 }
