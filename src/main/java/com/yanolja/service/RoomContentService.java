package com.yanolja.service;

import com.yanolja.domain.RoomContent;
import com.yanolja.repository.roomContent.RoomContentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoomContentService {
    @Autowired
    private RoomContentRepository roomContentRepository;
    public RoomContent.RegisterReq insert(RoomContent.RegisterReq roomContent) {
        return roomContentRepository.insert(roomContent);
    }
    public Integer updateById(RoomContent.PatchReq roomContent) {
        log.debug("roomContent Id = {}", roomContent.getRoomContentId());
        return roomContentRepository.updateById(roomContent);
    }
    public Integer deleteById(Integer id) {
        log.debug("roomContent id = {}", id);
        return roomContentRepository.deleteById(id);
    }
    public RoomContent.Info findById(Integer id){
        log.debug("roomContent Id = {}", id);
        return roomContentRepository.findById(id);
    }
    public List<RoomContent.Info> findByRoomId(Integer id){
        return roomContentRepository.findByRoomId(id);
    }
}
