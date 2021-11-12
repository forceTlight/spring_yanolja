package com.yanolja.service;

import com.yanolja.domain.RoomContentDTO;
import com.yanolja.repository.roomContent.RoomContentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoomContentService {
    @Autowired
    private RoomContentRepository roomContentRepository;
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
    public RoomContentDTO findById(Integer id){
        log.debug("roomContent Id = {}", id);
        return roomContentRepository.findById(id);
    }
}
