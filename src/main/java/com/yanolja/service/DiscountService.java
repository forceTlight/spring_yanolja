package com.yanolja.service;

import com.yanolja.domain.DiscountDTO;
import com.yanolja.repository.discount.DiscountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    public DiscountDTO insert(DiscountDTO discount) {
        return discountRepository.insert(discount);
    }
    public Integer updateById(DiscountDTO discount) {
        log.debug("discount Id = {}", discount.getDiscountId());
        return discountRepository.updateById(discount);
    }
    public Integer deleteById(Integer id) {
        log.debug("discount id = {}", id);
        return discountRepository.deleteById(id);
    }
    public DiscountDTO findById(Integer id){
        log.debug("discount Id = {}", id);
        return discountRepository.findById(id);
    }
}
