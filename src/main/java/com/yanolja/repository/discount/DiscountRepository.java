package com.yanolja.repository.discount;

import com.yanolja.domain.DiscountDTO;

public interface DiscountRepository {
    public DiscountDTO insert(DiscountDTO discount);
    public Integer updateById(DiscountDTO discount);
    public Integer deleteById(Integer id);
    public DiscountDTO findById(Integer id);
}
