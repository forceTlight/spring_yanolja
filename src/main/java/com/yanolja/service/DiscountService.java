package com.yanolja.service;

import com.yanolja.domain.DiscountDTO;

public interface DiscountService {
    public DiscountDTO insert(DiscountDTO discount);
    public Integer updateById(DiscountDTO discount);
    public Integer deleteById(Integer id);
    public DiscountDTO findById(Integer id);
}
