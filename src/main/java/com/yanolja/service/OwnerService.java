package com.yanolja.service;

import com.yanolja.domain.OwnerDTO;

public interface OwnerService {
    public OwnerDTO insert(OwnerDTO owner);
    public Integer updateById(OwnerDTO owner);
    public Integer deleteById(Integer id);
}
