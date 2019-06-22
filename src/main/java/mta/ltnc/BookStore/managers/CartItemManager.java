package mta.ltnc.BookStore.managers;

import mta.ltnc.BookStore.entity.CartItem;
import mta.ltnc.BookStore.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* Generated by Spring Data Generator on 22/06/2019
*/
@Component
public class CartItemManager {

	private CartItemRepository cartItemRepository;

	@Autowired
	public CartItemManager(CartItemRepository cartItemRepository) {
		this.cartItemRepository = cartItemRepository;
	}

}
