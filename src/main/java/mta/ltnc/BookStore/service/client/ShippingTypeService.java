package mta.ltnc.BookStore.service.client;

import mta.ltnc.BookStore.entity.ShippingType;
import mta.ltnc.BookStore.repositories.ShippingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingTypeService {
    @Autowired
    private ShippingTypeRepository shippingTypeRepository;

    public List<ShippingType> getAll(){
        return shippingTypeRepository.findAll();
    }
    public  ShippingType findById(Long shipId){
        return shippingTypeRepository.findById(shipId).get();
    }
}
