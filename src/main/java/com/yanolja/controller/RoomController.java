package com.yanolja.controller;

import com.yanolja.configuration.DefaultException;
import com.yanolja.configuration.DefaultResponse;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.StatusCode;
import com.yanolja.domain.Room;
import com.yanolja.repository.owner.OwnerRepository;
import com.yanolja.repository.room.RoomRepository;
import com.yanolja.service.RoomService;
import com.yanolja.utils.JwtAuthenticationProvider;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;
    @PostMapping(value="/register")
    @ApiOperation(value = "숙소 등록", notes = "숙소를 새로 등록함.")
    public DefaultResponse<String> roomCreate(@RequestBody Room.RegisterReq room, HttpServletRequest request){
        try {
            roomService.insert(room);
            int ownerId = room.getOwnerId();
            // ownerId로 email 가져오기
            String ownerEmail = ownerRepository.getEmailById(ownerId);
            // jwt에서 email 추출
            String jwtEmail = jwtAuthenticationProvider.getJwtEmail(request);
            // jwt validation
            if(!jwtEmail.equals(ownerEmail) || jwtEmail == null){
                throw new DefaultException(StatusCode.JWT_ERROR,ResponseMessage.INVALID_JWT);
            }
            return new DefaultResponse<String>(StatusCode.ROOM_REGISTER_SUCCESS, ResponseMessage.ROOM_REGISTER_OK);
        }catch (DefaultException e){
            return new DefaultResponse<>(e.getStatusCode(), e.getMessage());
        } catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<String>(StatusCode.ROOM_REGISTER_FAIL, ResponseMessage.ROOM_REGISTER_ERROR);
        }
    }
    @GetMapping(value="/find")
    @ApiOperation(value = "숙소이름 검색", notes = "숙소 이름으로 숙소를 조회함.")
    public DefaultResponse<List<Room.Info>> roomFindById(@RequestBody Room.FindNameReq nameReq){
        String name = nameReq.getName();
        try{
            List<Room.Info> roomList = roomService.findByName(name);
            return new DefaultResponse<List<Room.Info>>(StatusCode.ROOM_SEARCH_SUCCESS, ResponseMessage.ROOM_FIND_OK, roomService.findByName(name));
        }catch(Exception e){
            log.error(e.toString());
            return new DefaultResponse<>(StatusCode.ROOM_SEARCH_FAIL, ResponseMessage.ROOM_FIND_ERROR);
        }
    }
    @PatchMapping(value="/update")
    @ApiOperation(value = "숙소 수정", notes = "숙소 레이블을 수정한다.")
    public DefaultResponse<String> roomUpdate(@RequestBody Room.PatchRoomReq room, HttpServletRequest request){
        try {
            int roomId = room.getRoomId();
            int ownerId = roomRepository.getOwnerIdByRoomId(roomId);
            // ownerId로 email 가져오기
            String ownerEmail = ownerRepository.getEmailById(ownerId);
            // jwt에서 email 추출
            String jwtEmail = jwtAuthenticationProvider.getJwtEmail(request);
            // jwt validation
            if(!jwtEmail.equals(ownerEmail) || jwtEmail == null){
                throw new DefaultException(StatusCode.JWT_ERROR, ResponseMessage.INVALID_JWT);
            }
            Integer updatedCnt = roomService.updateById(room);
            return new DefaultResponse<String>(StatusCode.ROOM_UPDATE_SUCCESS, ResponseMessage.ROOM_UPDATE_OK);
        }catch (DefaultException e){
            return new DefaultResponse<>(e.getStatusCode(), e.getMessage());
        } catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(StatusCode.ROOM_UPDATE_FAIL, ResponseMessage.ROOM_UPDATE_ERROR);
        }
    }
    @PatchMapping(value="/delete/{roomId}")
    @ApiOperation(value = "숙소 삭제", notes = "roomId를 받아서 숙소를 삭제한다.")
    public DefaultResponse<String> roomDelete(@PathVariable Integer roomId, HttpServletRequest request){
        try {
            Integer deletedCnt = roomService.deleteById(roomId);
            int ownerId = roomRepository.getOwnerIdByRoomId(roomId);
            // ownerId로 email 가져오기
            String ownerEmail = ownerRepository.getEmailById(ownerId);
            // jwt에서 email 추출
            String jwtEmail = jwtAuthenticationProvider.getJwtEmail(request);
            // jwt validation
            if(!jwtEmail.equals(ownerEmail) || jwtEmail == null){
                throw new DefaultException(StatusCode.JWT_ERROR, ResponseMessage.INVALID_JWT);
            }
            return new DefaultResponse<String>(StatusCode.ROOM_DELETE_SUCCESS, ResponseMessage.ROOM_DELETE_OK);
        }catch (DefaultException e){
            return new DefaultResponse<>(e.getStatusCode(), e.getMessage());
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(StatusCode.ROOM_DELETE_FAIL, ResponseMessage.ROOM_DELETE_ERROR);
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
