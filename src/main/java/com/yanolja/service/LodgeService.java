package com.yanolja.service;

import com.yanolja.domain.LodgeDTO;
import com.yanolja.repository.lodge.LodgeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LodgeService {
    @Autowired
    private LodgeRepository lodgeRepository;
    public LodgeDTO insert(LodgeDTO lodge) {
        return lodgeRepository.insert(lodge);
    }
    public Integer updateById(LodgeDTO lodge) {
        log.debug("lodge Id = {}", lodge.getLodgeId());
        return lodgeRepository.updateById(lodge);
    }
    public Integer deleteById(Integer id) {
        log.debug("lodge id = {}", id);
        return lodgeRepository.deleteById(id);
    }
    public LodgeDTO findById(Integer id){
        log.debug("lodge Id = {}", id);
        return lodgeRepository.findById(id);
    }
}
