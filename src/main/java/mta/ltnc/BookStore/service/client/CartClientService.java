package mta.ltnc.BookStore.service.client;

import mta.ltnc.BookStore.entity.CartItem;
import mta.ltnc.BookStore.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartClientService {
    @Autowired
    private CartItemRepository cartItemRepository;
    // Lay theo itemId va userId
    public  CartItem findByItemIdAndUserId(Long itemId,Long userId){
        return cartItemRepository.findByItemIdAndUserId(itemId,userId);
    }
    public  void removeByItemIdAndUserId(Long itemId,Long userId){
        cartItemRepository.removeByItemIdAndUserId(itemId,userId);
    }
    public void save(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }
    public void remove(CartItem cartItem){
        cartItemRepository.delete(cartItem);
    }
    public void updateQuantity(Integer quantity, Long itemId, Long userId){
        cartItemRepository.updateQuantity(quantity, itemId, userId);
    }
}
