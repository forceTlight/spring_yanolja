package com.yanolja.controller;

import com.yanolja.configuration.DefaultException;
import com.yanolja.configuration.DefaultResponse;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.StatusCode;
import com.yanolja.domain.RoomContent;
import com.yanolja.repository.owner.OwnerRepository;
import com.yanolja.repository.room.RoomRepository;
import com.yanolja.service.RoomContentService;
import com.yanolja.utils.JwtAuthenticationProvider;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roomcontent")
public class RoomContentController {
    @Autowired
    RoomContentService roomContentService;
    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    RoomRepository roomRepository;

    @PostMapping(value="/register")
    @ApiOperation(value = "객실정보 등록", notes = "객실정보를 새로 등록함.")
    public DefaultResponse<String> roomContentCreate(@RequestBody RoomContent.RegisterReq registerReq,
                                                     HttpServletRequest request){
        try {
            int roomId = registerReq.getRoomId();
            int ownerId = roomRepository.getOwnerIdByRoomId(roomId);
            // ownerId로 email 가져오기
            String ownerEmail = ownerRepository.getEmailById(ownerId);
            // jwt에서 email 추출
            String jwtEmail = jwtAuthenticationProvider.getJwtEmail(request);
            // jwt validation
            if(!jwtEmail.equals(ownerEmail) || jwtEmail == null){
                throw new DefaultException(StatusCode.JWT_ERROR, ResponseMessage.INVALID_JWT);
            }
            roomContentService.insert(registerReq);
            return new DefaultResponse<String>(StatusCode.ROOMCONTENT_REGISTER_SUCCESS, ResponseMessage.ROOMCONTENT_REGISTER_OK);
        }catch (DefaultException e){
            return new DefaultResponse<>(e.getStatusCode(), e.getMessage());
        }catch(Exception e) {
            e.printStackTrace();
            return new DefaultResponse<String>(StatusCode.ROOMCONTENT_REGISTER_FAIL, ResponseMessage.ROOMCONTENT_REGISTER_ERROR);
        }
    }
    @GetMapping(value="/find/{roomContentId}")
    @ApiOperation(value = "객실정보 상세조회", notes = "roomContentId로 객실정보를 조회함.")
    public DefaultResponse<RoomContent.Info> roomContentFindById(@PathVariable Integer roomContentId){
        try{
            RoomContent.Info roomContent = roomContentService.findById(roomContentId);
            return new DefaultResponse<RoomContent.Info>(StatusCode.ROOMCONTENT_SEARCH_SUCCESS, ResponseMessage.ROOMCONTENT_FIND_OK, roomContent);
        }catch(DefaultException e){
            return new DefaultResponse<>(e.getStatusCode(), e.getMessage());
        }catch(Exception e){
            return new DefaultResponse<>(StatusCode.ROOMCONTENT_SEARCH_FAIL, ResponseMessage.ROOMCONTENT_FIND_ERROR);
        }
    }
    @GetMapping(value="/load/{roomId}")
    @ApiOperation(value = "객실정보 불러오기", notes = "roomId로 객실정보를 조회함.")
    public DefaultResponse<List<RoomContent.Info>> roomContentFindByRoomId(@PathVariable Integer roomId){
        try{
            List<RoomContent.Info> roomContentList = roomContentService.findByRoomId(roomId);
            return new DefaultResponse<List<RoomContent.Info>>(StatusCode.ROOMCONTENT_LOAD_SUCCESS, ResponseMessage.ROOMCONTENT_FINDBYROOMID_OK, roomContentList);
        }catch(Exception e){
            return new DefaultResponse<>(StatusCode.ROOMCONTENT_LOAD_FAIL, ResponseMessage.ROOMCONTENT_FINDBYROOMID_ERROR);
        }
    }
    @PatchMapping(value="/update")
    @ApiOperation(value = "객실 수정", notes = "객실 레이블을 수정한다.")
    public DefaultResponse<String> roomContentUpdate(@RequestBody RoomContent.PatchReq roomContent){
        try {
            Integer updatedCnt = roomContentService.updateById(roomContent);
            return new DefaultResponse<String>(StatusCode.ROOMCONTENT_UPDATE_SUCCESS, ResponseMessage.ROOMCONTENT_UPDATE_OK);
        }catch(Exception e) {
            return new DefaultResponse<>(StatusCode.ROOMCONTENT_UPDATE_FAIL, ResponseMessage.ROOMCONTENT_UPDATE_ERROR);
        }
    }
    @PatchMapping(value="/delete/{roomContentId}")
    @ApiOperation(value = "객실 삭제", notes = "roomContentId를 받아서 객실를 삭제한다.")
    public DefaultResponse<String> roomContentDelete(@PathVariable Integer roomContentId, HttpServletRequest request){
        try {
            int roomId = roomContentService.findRoomIdByRoomContentId(roomContentId);
            int ownerId = roomRepository.getOwnerIdByRoomId(roomId);
            // ownerId로 email 가져오기
            String ownerEmail = ownerRepository.getEmailById(ownerId);
            // jwt에서 email 추출
            String jwtEmail = jwtAuthenticationProvider.getJwtEmail(request);
            // jwt validation
            if(!jwtEmail.equals(ownerEmail) || jwtEmail == null){
                throw new DefaultException(StatusCode.JWT_ERROR, ResponseMessage.INVALID_JWT);
            }
            Integer deletedCnt = roomContentService.deleteById(roomContentId);
            return new DefaultResponse<String>(StatusCode.ROOMCONTENT_DELETE_SUCCESS, ResponseMessage.ROOMCONTENT_DELETE_OK);
        }catch (DefaultException e){
         return new DefaultResponse<>(e.getStatusCode(), e.getMessage());
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(StatusCode.ROOMCONTENT_DELETE_FAIL, ResponseMessage.ROOMCONTENT_DELETE_ERROR);
        }
    }
}
