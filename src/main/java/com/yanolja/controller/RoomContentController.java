package com.yanolja.controller;

import com.yanolja.domain.RoomContentDTO;
import com.yanolja.service.RoomContentServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/roomcontent")
public class RoomContentController {
    @Autowired
    RoomContentServiceImpl roomContentService;

    @PostMapping(value="/register")
    @ApiOperation(value = "숙소정보 등록", notes = "숙소정보를 새로 등록함.")
    public ResponseEntity<RoomContentDTO> roomContentCreate(@RequestBody RoomContentDTO roomContent){
        try {
            log.debug("roomContent = {}", roomContent.toString());
            return new ResponseEntity<>(roomContentService.insert(roomContent), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/find/{roomContentId}")
    @ApiOperation(value = "숙소정보 상세조회", notes = "roomContentId로 숙소정보를 조회함.")
    public ResponseEntity<RoomContentDTO> roomContentFindById(@PathVariable Integer roomContentId){
        try{
            log.debug("roomContent = {}", roomContentId);
            RoomContentDTO roomContent = roomContentService.findById(roomContentId);
            return new ResponseEntity<>(roomContentService.findById(roomContentId), HttpStatus.OK);
        }catch(Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/update")
    @ApiOperation(value = "숙소정보 수정", notes = "숙소정보 레이블을 수정한다.")
    public ResponseEntity<String> roomContentUpdate(@RequestBody RoomContentDTO roomContent){
        try {
            log.debug("roomContent = {}", roomContent.toString());
            Integer updatedCnt = roomContentService.updateById(roomContent);
            return new ResponseEntity<>(String.format("%d updated", updatedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value="/delete/{roomContentId}")
    @ApiOperation(value = "숙소정보 삭제", notes = "roomContentId를 받아서 숙소정보를 삭제한다.")
    public ResponseEntity<String> roomContentDelete(@PathVariable Integer roomContentId){
        try {
            log.debug("roomContent id = {}", roomContentId);
            Integer deletedCnt = roomContentService.deleteById(roomContentId);
            return new ResponseEntity<>(String.format("%d deleted.", deletedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
