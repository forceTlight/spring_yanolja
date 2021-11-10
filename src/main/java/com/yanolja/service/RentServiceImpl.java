package com.yanolja.service;

import com.yanolja.domain.RentDTO;
import com.yanolja.repository.rent.RentRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RentServiceImpl {
    @Autowired
    private RentRepositoryImpl rentRepository;
    public RentDTO insert(RentDTO rent) {
        return rentRepository.insert(rent);
    }
    public Integer updateById(RentDTO rent) {
        log.debug("rent Id = {}", rent.getRentId());
        return rentRepository.updateById(rent);
    }
    public Integer deleteById(Integer id) {
        log.debug("rent id = {}", id);
        return rentRepository.deleteById(id);
    }
    public RentDTO findById(Integer id){
        log.debug("rent Id = {}", id);
        return rentRepository.findById(id);
    }
}
