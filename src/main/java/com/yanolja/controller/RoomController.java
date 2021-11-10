package com.yanolja.controller;

import com.yanolja.domain.RoomDTO;
import com.yanolja.service.RoomServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomServiceImpl roomService;

    @PostMapping(value="/register")
    @ApiOperation(value = "숙소 등록", notes = "숙소를 새로 등록함.")
    public ResponseEntity<RoomDTO> roomCreate(@RequestBody RoomDTO room){
        try {
            log.debug("room = {}", room.toString());
            return new ResponseEntity<>(roomService.insert(room), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/find/{roomId}")
    @ApiOperation(value = "숙소 상세조회", notes = "roomId로 숙소를 조회함.")
    public ResponseEntity<RoomDTO> roomFindById(@PathVariable Integer roomId){
        try{
            log.debug("room = {}", roomId);
            RoomDTO room = roomService.findById(roomId);
            return new ResponseEntity<>(roomService.findById(roomId), HttpStatus.OK);
        }catch(Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/update")
    @ApiOperation(value = "숙소 수정", notes = "숙소 레이블을 수정한다.")
    public ResponseEntity<String> roomUpdate(@RequestBody RoomDTO room){
        try {
            log.debug("room = {}", room.toString());
            Integer updatedCnt = roomService.updateById(room);
            return new ResponseEntity<>(String.format("%d updated", updatedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value="/delete/{roomId}")
    @ApiOperation(value = "숙소 삭제", notes = "roomId를 받아서 숙소를 삭제한다.")
    public ResponseEntity<String> roomDelete(@PathVariable Integer roomId){
        try {
            log.debug("room id = {}", roomId);
            Integer deletedCnt = roomService.deleteById(roomId);
            return new ResponseEntity<>(String.format("%d deleted.", deletedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
