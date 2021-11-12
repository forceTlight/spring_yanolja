package com.yanolja.controller;

import com.yanolja.configuration.DefaultRes;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.StatusCode;
import com.yanolja.domain.RoomDTO;
import com.yanolja.service.RoomService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping(value="/register")
    @ApiOperation(value = "숙소 등록", notes = "숙소를 새로 등록함.")
    public DefaultRes<String> roomCreate(@RequestBody RoomDTO.RegisterReq room){
        try {
            log.debug("room = {}", room.toString());
            roomService.insert(room);
            return new DefaultRes<String>(StatusCode.CREATED, ResponseMessage.ROOM_REGISTER_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultRes<String>(StatusCode.DB_ERROR, ResponseMessage.ROOM_REGISTER_ERROR);
        }
    }
    @GetMapping(value="/find")
    @ApiOperation(value = "숙소이름 검색", notes = "숙소 이름으로 숙소를 조회함.")
    public DefaultRes<List<RoomDTO.Info>> roomFindById(@RequestParam String name){
        try{
            log.debug("roomName = {}", name);
            List<RoomDTO.Info> roomList = roomService.findByName(name);
            return new DefaultRes<List<RoomDTO.Info>>(StatusCode.OK, ResponseMessage.ROOM_FIND_OK, roomService.findByName(name));
        }catch(Exception e){
            log.error(e.toString());
            return new DefaultRes<>(StatusCode.DB_ERROR, ResponseMessage.ROOM_FIND_ERROR);
        }
    }
    @PatchMapping(value="/update")
    @ApiOperation(value = "숙소 수정", notes = "숙소 레이블을 수정한다.")
    public DefaultRes<String> roomUpdate(@RequestBody RoomDTO.PatchReq room){
        try {
            log.debug("room = {}", room.toString());
            Integer updatedCnt = roomService.updateById(room);
            return new DefaultRes<String>(StatusCode.OK, ResponseMessage.ROOM_UPDATE_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultRes<>(StatusCode.DB_ERROR, ResponseMessage.ROOM_UPDATE_ERROR);
        }
    }
    @GetMapping(value="/delete/{roomId}")
    @ApiOperation(value = "숙소 삭제", notes = "roomId를 받아서 숙소를 삭제한다.")
    public DefaultRes<String> roomDelete(@PathVariable Integer roomId){
        try {
            log.debug("room id = {}", roomId);
            Integer deletedCnt = roomService.deleteById(roomId);
            return new DefaultRes<String>(StatusCode.OK, ResponseMessage.ROOM_DELETE_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultRes<>(StatusCode.DB_ERROR, ResponseMessage.ROOM_DELETE_ERROR);
        }
    }
}
