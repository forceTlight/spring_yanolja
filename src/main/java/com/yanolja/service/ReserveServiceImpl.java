package com.yanolja.service;

import com.yanolja.domain.ReserveDTO;
import com.yanolja.repository.reserve.ReserveRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReserveServiceImpl {
    @Autowired
    private ReserveRepositoryImpl reserveRepository;
    public ReserveDTO insert(ReserveDTO reserve) {
        return reserveRepository.insert(reserve);
    }
    public Integer updateById(ReserveDTO reserve) {
        log.debug("reserve Id = {}", reserve.getReserveId());
        return reserveRepository.updateById(reserve);
    }
    public Integer deleteById(Integer id) {
        log.debug("reserve id = {}", id);
        return reserveRepository.deleteById(id);
    }
    public ReserveDTO findById(Integer id){
        log.debug("reserve Id = {}", id);
        return reserveRepository.findById(id);
    }
}
