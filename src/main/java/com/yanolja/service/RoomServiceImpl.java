package com.yanolja.service;

import com.yanolja.domain.RoomDTO;
import com.yanolja.repository.room.RoomRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoomServiceImpl implements RoomService{
    @Autowired
    private RoomRepositoryImpl roomRepository;
    public RoomDTO insert(RoomDTO room) {
        return roomRepository.insert(room);
    }
    public Integer updateById(RoomDTO room) {
        log.debug("room Id = {}", room.getRoomId());
        return roomRepository.updateById(room);
    }
    public Integer deleteById(Integer id) {
        log.debug("room id = {}", id);
        return roomRepository.deleteById(id);
    }
    public RoomDTO findById(Integer id){
        log.debug("room Id = {}", id);
        return roomRepository.findById(id);
    }
}
