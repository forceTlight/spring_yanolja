package com.yanolja.service;

import com.yanolja.domain.Rent;
import com.yanolja.repository.rent.RentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;
    public Rent.RegisterReq insert(Rent.RegisterReq rent) {
        return rentRepository.insert(rent);
    }
    public Integer updateById(Rent.PatchReq rent) {
        log.debug("rent Id = {}", rent.getRentId());
        return rentRepository.updateById(rent);
    }
    public Integer deleteById(Integer id) {
        log.debug("rent id = {}", id);
        return rentRepository.deleteById(id);
    }
    public Rent.Info findById(Integer id){
        log.debug("rent Id = {}", id);
        return rentRepository.findById(id);
    }
    public Rent.Info findByRoomContentId(Integer id){
        return rentRepository.findByRoomContentId(id);
    }
    public int findRoomContentIdByRentId(Integer id){
        return rentRepository.findRoomContentIdByRentId(id);
    }
}
