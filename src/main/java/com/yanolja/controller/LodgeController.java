package com.yanolja.controller;

import com.yanolja.configuration.DefaultResponse;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.Status;
import com.yanolja.domain.Lodge;
import com.yanolja.service.LodgeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/lodge")
public class LodgeController {
    @Autowired
    LodgeService lodgeService;

    @PostMapping(value="/register")
    @ApiOperation(value = "숙박등록", notes = "숙박을 새로 등록함.")
    public DefaultResponse<String> lodgeCreate(@RequestBody Lodge lodge){
        try {
            log.debug("lodge = {}", lodge.toString());
            return new DefaultResponse<String>(Status.CREATED, ResponseMessage.LODGE_REGISTER_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(Status.DB_ERROR, ResponseMessage.LODGE_REGISTER_ERROR);
        }
    }
    @GetMapping(value="/find/{lodgeId}")
    @ApiOperation(value = "숙박상세조회", notes = "lodgeId로 숙박을 조회함.")
    public DefaultResponse<Lodge.Info> lodgeFindById(@PathVariable Integer lodgeId){
        try{
            log.debug("lodge = {}", lodgeId);
            Lodge.Info lodge = lodgeService.findById(lodgeId);
            return new DefaultResponse<Lodge.Info>(Status.OK, ResponseMessage.LODGE_FIND_OK, lodge);
        }catch(Exception e){
            log.error(e.toString());
            return new DefaultResponse<>(Status.DB_ERROR, ResponseMessage.LODGE_FIND_ERROR);
        }
    }
    @GetMapping(value="/load/{roomContentId}")
    @ApiOperation(value = "숙박정보 불러오기", notes = "roomContentId로 숙박을 조회함.")
    public DefaultResponse<Lodge.Info> lodgeFindByRoomContentId(@PathVariable Integer roomContentId){
        try{
            Lodge.Info lodge = lodgeService.findByRoomContentId(roomContentId);
            return new DefaultResponse<Lodge.Info>(Status.OK, ResponseMessage.LODGE_FIND_OK, lodge);
        }catch(Exception e){
            log.error(e.toString());
            return new DefaultResponse<>(Status.DB_ERROR, ResponseMessage.LODGE_FIND_ERROR);
        }
    }
    @PutMapping(value="/update")
    @ApiOperation(value = "숙박수정", notes = "숙박 레이블을 수정한다.")
    public DefaultResponse<String> lodgeUpdate(@RequestBody Lodge.PatchReq lodge){
        try {
            log.debug("lodge = {}", lodge.toString());
            Integer updatedCnt = lodgeService.updateById(lodge);
            return new DefaultResponse<String>(Status.OK, ResponseMessage.LODGE_UPDATE_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(Status.DB_ERROR, ResponseMessage.LODGE_UPDATE_ERROR);
        }
    }
    @DeleteMapping(value="/delete/{lodgeId}")
    @ApiOperation(value = "숙박삭제", notes = "lodgeId를 받아서 숙박을 삭제한다.")
    public DefaultResponse<String> lodgeDelete(@PathVariable Integer lodgeId){
        try {
            log.debug("lodge id = {}", lodgeId);
            Integer deletedCnt = lodgeService.deleteById(lodgeId);
            return new DefaultResponse<>(Status.OK, ResponseMessage.LODGE_DELETE_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(Status.DB_ERROR, ResponseMessage.LODGE_DELETE_ERROR);
        }
    }
}
