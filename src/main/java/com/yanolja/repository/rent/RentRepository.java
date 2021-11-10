package com.yanolja.repository.rent;

import com.yanolja.domain.RentDTO;

public interface RentRepository {
    public RentDTO insert(RentDTO rent);
    public Integer updateById(RentDTO rent);
    public Integer deleteById(Integer id);
    public RentDTO findById(Integer id);
}
