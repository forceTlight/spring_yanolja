package com.yanolja.controller;

import com.yanolja.configuration.DefaultException;
import com.yanolja.configuration.DefaultResponse;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.StatusCode;
import com.yanolja.domain.Lodge;
import com.yanolja.repository.owner.OwnerRepository;
import com.yanolja.repository.room.RoomRepository;
import com.yanolja.service.LodgeService;
import com.yanolja.service.RoomContentService;
import com.yanolja.utils.JwtAuthenticationProvider;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/lodge")
public class LodgeController {
    @Autowired
    LodgeService lodgeService;
    @Autowired
    RoomContentService roomContentService;
    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    OwnerRepository ownerRepository;

    @PostMapping(value="/register")
    @ApiOperation(value = "숙박등록", notes = "숙박을 새로 등록함.")
    public DefaultResponse<String> lodgeCreate(@RequestBody Lodge.RegisterReq lodge, HttpServletRequest request){
        try {
            int roomContentId = lodge.getRoomContentId();
            jwtAuthenticationProvider.setOwnerJwtTokenCheck(roomContentId, request);
            lodgeService.insert(lodge);
            return new DefaultResponse<String>(StatusCode.LODGE_REGISTER_SUCCESS, ResponseMessage.LODGE_REGISTER_OK);
        }catch(DefaultException e){
            return new DefaultResponse<>(e.getStatusCode(), e.getMessage());
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(StatusCode.LODGE_REGISTER_FAIL, ResponseMessage.LODGE_REGISTER_ERROR);
        }
    }
    @GetMapping(value="/find/{lodgeId}")
    @ApiOperation(value = "숙박상세조회", notes = "lodgeId로 숙박을 조회함.")
    public DefaultResponse<Lodge.Info> lodgeFindById(@PathVariable Integer lodgeId){
        try{
            Lodge.Info lodge = lodgeService.findById(lodgeId);
            return new DefaultResponse<Lodge.Info>(StatusCode.LODGE_SEARCH_SUCCESS, ResponseMessage.LODGE_FIND_OK, lodge);
        }catch(Exception e){
            return new DefaultResponse<>(StatusCode.LODGE_SEARCH_FAIL, ResponseMessage.LODGE_FIND_ERROR);
        }
    }
    @GetMapping(value="/load/{roomContentId}")
    @ApiOperation(value = "숙박정보 불러오기", notes = "roomContentId로 숙박을 조회함.")
    public DefaultResponse<Lodge.Info> lodgeFindByRoomContentId(@PathVariable Integer roomContentId){
        try{
            Lodge.Info lodge = lodgeService.findByRoomContentId(roomContentId);
            return new DefaultResponse<Lodge.Info>(StatusCode.LODGE_SEARCH_SUCCESS, ResponseMessage.LODGE_FIND_OK, lodge);
        }catch(Exception e){
            return new DefaultResponse<>(StatusCode.LODGE_SEARCH_FAIL, ResponseMessage.LODGE_FIND_ERROR);
        }
    }
    @PatchMapping(value="/update")
    @ApiOperation(value = "숙박수정", notes = "숙박 레이블을 수정한다.")
    public DefaultResponse<String> lodgeUpdate(@RequestBody Lodge.PatchReq lodge, HttpServletRequest request){
        try {
            int roomContentId = lodgeService.findRoomContentIdByLodgeId(lodge.getLodgeId());
            jwtAuthenticationProvider.setOwnerJwtTokenCheck(roomContentId, request);
            Integer updatedCnt = lodgeService.updateById(lodge);
            return new DefaultResponse<String>(StatusCode.LODGE_UPDATE_SUCCESS, ResponseMessage.LODGE_UPDATE_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(StatusCode.LODGE_UPDATE_FAIL, ResponseMessage.LODGE_UPDATE_ERROR);
        }
    }
    @DeleteMapping(value="/delete/{lodgeId}")
    @ApiOperation(value = "숙박삭제", notes = "lodgeId를 받아서 숙박을 삭제한다.")
    public DefaultResponse<String> lodgeDelete(@PathVariable Integer lodgeId, HttpServletRequest request){
        try {
            int roomContentId = lodgeService.findRoomContentIdByLodgeId(lodgeId);
            jwtAuthenticationProvider.setOwnerJwtTokenCheck(roomContentId, request);
            Integer deletedCnt = lodgeService.deleteById(lodgeId);
            return new DefaultResponse<>(StatusCode.LODGE_DELETE_SUCCESS, ResponseMessage.LODGE_DELETE_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(StatusCode.LODGE_DELETE_FAIL, ResponseMessage.LODGE_DELETE_ERROR);
        }
    }
}
