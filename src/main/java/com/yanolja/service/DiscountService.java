package com.yanolja.service;

import com.yanolja.domain.Discount;
import com.yanolja.repository.discount.DiscountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    public Discount.RegisterReq insert(Discount.RegisterReq discount) {
        return discountRepository.insert(discount);
    }
    public Integer updateById(Discount.PatchReq discount) {
        return discountRepository.updateById(discount);
    }
    public Integer deleteById(Integer id) {
        return discountRepository.deleteById(id);
    }
    public Discount.Info findById(Integer id){
        return discountRepository.findById(id);
    }
}
