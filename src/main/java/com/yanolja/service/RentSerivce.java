package com.yanolja.service;

import com.yanolja.domain.RentDTO;

public interface RentSerivce {
    public RentDTO insert(RentDTO rent);
    public Integer updateById(RentDTO rent);
    public Integer deleteById(Integer id);
    public RentDTO findById(Integer id);

}
