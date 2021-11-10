package com.yanolja.service;

import com.yanolja.domain.OwnerDTO;
import com.yanolja.repository.owner.OwnerRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OwnerServiceImpl implements OwnerService{
    @Autowired
    private OwnerRepositoryImpl ownerRepository;
    public OwnerDTO insert(OwnerDTO owner) {
        return ownerRepository.insert(owner);
    }
    public Integer updateById(OwnerDTO owner) {
        log.debug("owner Id = {}", owner.getOwnerId());
        return ownerRepository.updateById(owner);
    }
    public Integer deleteById(Integer id) {
        log.debug("owner id = {}", id);
        return ownerRepository.deleteById(id);
    }
}
