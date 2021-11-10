package com.yanolja.service;

import com.yanolja.domain.AmenityDTO;

public interface AmenityService {
    public AmenityDTO insert(AmenityDTO amenity);
    public Integer updateById(AmenityDTO amenity);
    public Integer deleteById(Integer id);
    public AmenityDTO findById(Integer id);
}
