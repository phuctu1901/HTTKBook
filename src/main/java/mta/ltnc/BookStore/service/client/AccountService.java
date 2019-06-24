package mta.ltnc.BookStore.service.client;

import mta.ltnc.BookStore.dto.client.CartItemDto;
import mta.ltnc.BookStore.entity.User;
import mta.ltnc.BookStore.repositories.CartItemRepository;
import mta.ltnc.BookStore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AccountService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    public Boolean checkLogin(String userNameOrEmail,String password){
        //password = Hash.getMd5(password);
        User u = userRepository.findByUserNameOrEmailAndPassword(userNameOrEmail,userNameOrEmail,password);
        if(u == null){
            return false;
        }
        return true;
    }
    public User getUser(String userNameOrEmail,String password){
        return userRepository.findByUserNameOrEmailAndPassword(userNameOrEmail,userNameOrEmail,password);
    }
    public HashMap<Long,CartItemDto> getCart(Long userId){
        HashMap<Long,CartItemDto> hashMap = new HashMap<>();
        cartItemRepository.getAllByUserId(userId).forEach(x ->{
            hashMap.put(x.getId(),x);
        });
        return hashMap;
    }
    public User findUser(Long userId){
        return userRepository.findById(userId).get();
    }
    public Short checkSameAcc(String userName,String email){
        User un = userRepository.findByUserName(userName);
        User ue = userRepository.findByEmail(email);
        if (un == null && ue == null) return 0;
        if (un == null && ue != null) return 1;
        if (un != null && ue == null) return 2;
        return 3;
    }
    public void saveUser(User user){
        userRepository.save(user);
}
}
