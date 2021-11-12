package com.yanolja.service;

import com.yanolja.domain.AmenityDTO;
import com.yanolja.repository.amenity.AmenityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AmenityService {
    @Autowired
    private AmenityRepository amenityRepository;
    public AmenityDTO insert(AmenityDTO amenity) {
        return amenityRepository.insert(amenity);
    }
    public Integer updateById(AmenityDTO amenity) {
        log.debug("amenity Id = {}", amenity.getAmenityId());
        return amenityRepository.updateById(amenity);
    }
    public Integer deleteById(Integer id) {
        log.debug("amenity id = {}", id);
        return amenityRepository.deleteById(id);
    }
    public AmenityDTO findById(Integer id){
        log.debug("amenity Id = {}", id);
        return amenityRepository.findById(id);
    }
}
