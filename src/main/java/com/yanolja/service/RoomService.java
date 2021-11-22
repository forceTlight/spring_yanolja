package com.yanolja.service;

import com.yanolja.domain.Room;
import com.yanolja.repository.room.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    public Room.RegisterReq insert(Room.RegisterReq room) {
        return roomRepository.insert(room);
    }
    public Integer updateById(Room.PatchRoomReq room) {
        log.debug("room Id = {}", room.getRoomId());
        return roomRepository.updateById(room);
    }
    public Integer deleteById(Integer id) {
        log.debug("room id = {}", id);
        return roomRepository.deleteById(id);
    }
    public List<Room.Info> findByName(String name){
        log.debug("room Name = {}", name);
        return roomRepository.findByName(name);
    }
    public int countRoom(){

    }

    public List<Room.Info> paigingRoom()
}
