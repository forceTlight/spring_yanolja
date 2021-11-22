package com.yanolja.controller;

import com.yanolja.configuration.DefaultResponse;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.Status;
import com.yanolja.domain.Room;
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
    public DefaultResponse<String> roomCreate(@RequestBody Room.RegisterReq room){
        try {
            log.debug("room = {}", room.toString());
            roomService.insert(room);
            return new DefaultResponse<String>(Status.CREATED, ResponseMessage.ROOM_REGISTER_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<String>(Status.DB_ERROR, ResponseMessage.ROOM_REGISTER_ERROR);
        }
    }
    @GetMapping(value="/find")
    @ApiOperation(value = "숙소이름 검색", notes = "숙소 이름으로 숙소를 조회함.")
    public DefaultResponse<List<Room.Info>> roomFindById(@RequestParam String name){
        try{
            log.debug("roomName = {}", name);
            List<Room.Info> roomList = roomService.findByName(name);
            return new DefaultResponse<List<Room.Info>>(Status.OK, ResponseMessage.ROOM_FIND_OK, roomService.findByName(name));
        }catch(Exception e){
            log.error(e.toString());
            return new DefaultResponse<>(Status.DB_ERROR, ResponseMessage.ROOM_FIND_ERROR);
        }
    }
    @PatchMapping(value="/update")
    @ApiOperation(value = "숙소 수정", notes = "숙소 레이블을 수정한다.")
    public DefaultResponse<String> roomUpdate(@RequestBody Room.PatchRoomReq room){
        try {
            log.debug("room = {}", room.toString());
            Integer updatedCnt = roomService.updateById(room);
            return new DefaultResponse<String>(Status.OK, ResponseMessage.ROOM_UPDATE_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(Status.DB_ERROR, ResponseMessage.ROOM_UPDATE_ERROR);
        }
    }
    @PatchMapping(value="/delete/{roomId}")
    @ApiOperation(value = "숙소 삭제", notes = "roomId를 받아서 숙소를 삭제한다.")
    public DefaultResponse<String> roomDelete(@PathVariable Integer roomId){
        try {
            log.debug("room id = {}", roomId);
            Integer deletedCnt = roomService.deleteById(roomId);
            return new DefaultResponse<String>(Status.OK, ResponseMessage.ROOM_DELETE_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(Status.DB_ERROR, ResponseMessage.ROOM_DELETE_ERROR);
        }
    }/*
    // 숙소 가져오는거 페이징 처리
    @GetMapping(value="/paging")
    @ApiOperation(value = "숙소 페이징으로 가져오기", notes = "page를 받아서 아이디 순서 기준으로 숙소 페이징을 리턴한다.");
    public DefaultResponse<RoomContent.Info> roomPaging(@RequestParam int pageNum){
        try{

        }
    }*/

}
