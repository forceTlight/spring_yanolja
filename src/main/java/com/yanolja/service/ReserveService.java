package com.yanolja.service;

import com.yanolja.domain.Reserve;
import com.yanolja.repository.reserve.ReserveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;
    public Reserve insert(Reserve reserve) {
        return reserveRepository.insert(reserve);
    }
    public Integer updateById(Reserve reserve) {
        log.debug("reserve Id = {}", reserve.getReserveId());
        return reserveRepository.updateById(reserve);
    }
    public Integer deleteById(Integer id) {
        log.debug("reserve id = {}", id);
        return reserveRepository.deleteById(id);
    }
    public Reserve findById(Integer id){
        log.debug("reserve Id = {}", id);
        return reserveRepository.findById(id);
    }
}
