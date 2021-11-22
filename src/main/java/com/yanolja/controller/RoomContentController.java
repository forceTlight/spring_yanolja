package com.yanolja.controller;

import com.yanolja.configuration.DefaultResponse;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.Status;
import com.yanolja.domain.RoomContent;
import com.yanolja.service.RoomContentService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roomcontent")
public class RoomContentController {
    
    @Autowired
    RoomContentService roomContentService;

    @PostMapping(value="/register")
    @ApiOperation(value = "객실정보 등록", notes = "객실정보를 새로 등록함.")
    public DefaultResponse<String> roomContentCreate(@RequestBody RoomContent.RegisterReq roomContent){
        try {
            log.debug("roomContent = {}", roomContent.toString());
            return new DefaultResponse<String>(Status.CREATED, ResponseMessage.ROOMCONTENT_REGISTER_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<String>(Status.DB_ERROR, ResponseMessage.ROOMCONTENT_REGISTER_ERROR);
        }
    }
    @GetMapping(value="/find/{roomContentId}")
    @ApiOperation(value = "객실정보 상세조회", notes = "roomContentId로 객실정보를 조회함.")
    public DefaultResponse<RoomContent.Info> roomContentFindById(@PathVariable Integer roomContentId){
        try{
            log.debug("roomContent = {}", roomContentId);
            RoomContent.Info roomContent = roomContentService.findById(roomContentId);
            return new DefaultResponse<RoomContent.Info>(Status.OK, ResponseMessage.ROOMCONTENT_FIND_OK);
        }catch(Exception e){
            log.error(e.toString());
            return new DefaultResponse<>(Status.DB_ERROR, ResponseMessage.ROOMCONTENT_FIND_ERROR);
        }
    }
    @GetMapping(value="/load/{roomId}")
    @ApiOperation(value = "객실정보 불러오기", notes = "roomId로 객실정보를 조회함.")
    public DefaultResponse<List<RoomContent.Info>> roomContentFindByRoomId(@PathVariable Integer roomId){
        try{
            List<RoomContent.Info> roomContentList = roomContentService.findByRoomId(roomId);
            return new DefaultResponse<List<RoomContent.Info>>(Status.OK, ResponseMessage.ROOMCONTENT_FINDBYROOMID_OK, roomContentList);
        }catch(Exception e){
            log.error(e.toString());
            return new DefaultResponse<>(Status.DB_ERROR, ResponseMessage.ROOMCONTENT_FINDBYROOMID_ERROR);
        }
    }
    @PatchMapping(value="/update")
    @ApiOperation(value = "객실 수정", notes = "객실 레이블을 수정한다.")
    public DefaultResponse<String> roomContentUpdate(@RequestBody RoomContent.PatchReq roomContent){
        try {
            log.debug("roomContent = {}", roomContent.toString());
            Integer updatedCnt = roomContentService.updateById(roomContent);
            return new DefaultResponse<String>(Status.OK, ResponseMessage.ROOMCONTENT_UPDATE_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(Status.DB_ERROR, ResponseMessage.ROOMCONTENT_UPDATE_ERROR);
        }
    }
    @PatchMapping(value="/delete/{roomContentId}")
    @ApiOperation(value = "객실 삭제", notes = "roomContentId를 받아서 객실를 삭제한다.")
    public DefaultResponse<String> roomContentDelete(@PathVariable Integer roomContentId){
        try {
            log.debug("roomContent id = {}", roomContentId);
            Integer deletedCnt = roomContentService.deleteById(roomContentId);
            return new DefaultResponse<String>(Status.OK, ResponseMessage.ROOMCONTENT_DELETE_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(Status.DB_ERROR, ResponseMessage.ROOMCONTENT_DELETE_ERROR);
        }
    }
}
