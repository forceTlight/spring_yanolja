package com.yanolja.service;

import com.yanolja.domain.RoomContentDTO;
import com.yanolja.repository.roomContent.RoomContentRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoomContentServiceImpl implements RoomContentService{
    @Autowired
    private RoomContentRepositoryImpl roomContentRepository;
    public RoomContentDTO insert(RoomContentDTO roomContent) {
        return roomContentRepository.insert(roomContent);
    }
    public Integer updateById(RoomContentDTO roomContent) {
        log.debug("roomContent Id = {}", roomContent.getRoomContentId());
        return roomContentRepository.updateById(roomContent);
    }
    public Integer deleteById(Integer id) {
        log.debug("roomContent id = {}", id);
        return roomContentRepository.deleteById(id);
    }
}
