package com.yanolja.controller;

import com.yanolja.configuration.DefaultResponse;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.StatusCode;
import com.yanolja.domain.Rent;
import com.yanolja.service.RentService;
import com.yanolja.utils.JwtAuthenticationProvider;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/rent")
public class RentController {
    @Autowired
    RentService rentService;
    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;

    @PostMapping(value="/register")
    @ApiOperation(value = "대실등록", notes = "대실을 새로 등록함.")
    public DefaultResponse<String> rentCreate(@RequestBody Rent.RegisterReq rent, HttpServletRequest request){
        try {
            int roomContentId = rent.getRoomContentId();
            jwtAuthenticationProvider.setOwnerJwtTokenCheck(roomContentId, request);
            rentService.insert(rent);
            return new DefaultResponse<String>(StatusCode.CREATED, ResponseMessage.RENT_REGISTER_OK);
        }catch(Exception e) {
            return new DefaultResponse<>(StatusCode.BAD_REQUEST, ResponseMessage.RENT_REGISTER_ERROR);
        }
    }
    @GetMapping(value="/find/{rentId}")
    @ApiOperation(value = "대실 상세조회", notes = "rentId로 대실을 조회함.")
    public DefaultResponse<Rent.Info> rentFindById(@PathVariable Integer rentId){
        try{
            Rent.Info rent = rentService.findById(rentId);
            return new DefaultResponse<Rent.Info>(StatusCode.OK, ResponseMessage.RENT_FIND_OK, rent);
        }catch(Exception e){
            log.error(e.toString());
            return new DefaultResponse<>(StatusCode.BAD_REQUEST, ResponseMessage.RENT_FIND_ERROR);
        }
    }
    @GetMapping(value="/load/{roomContentId}")
    @ApiOperation(value = "대실 불러오기", notes = "roomContentId로 대실을 조회함.")
    public DefaultResponse<Rent.Info> rentFindByRoomContentId(@PathVariable Integer roomContentId){
        try{
            Rent.Info rent = rentService.findByRoomContentId(roomContentId);
            return new DefaultResponse<Rent.Info>(StatusCode.OK, ResponseMessage.RENT_FIND_OK, rent);
        }catch(Exception e){
            log.error(e.toString());
            return new DefaultResponse<>(StatusCode.BAD_REQUEST, ResponseMessage.RENT_FIND_ERROR);
        }
    }
    @PutMapping(value="/update")
    @ApiOperation(value = "대실 수정", notes = "대실 레이블을 수정한다.")
    public DefaultResponse<String> rentUpdate(@RequestBody Rent.PatchReq rent, HttpServletRequest request){
        try {
            int roomContentId = rentService.findRoomContentIdByRentId(rent.getRentId());
            jwtAuthenticationProvider.setOwnerJwtTokenCheck(roomContentId, request);
            Integer updatedCnt = rentService.updateById(rent);
            return new DefaultResponse<String>(StatusCode.OK, ResponseMessage.RENT_FIND_ERROR);
        }catch(Exception e) {
            return new DefaultResponse<>(StatusCode.BAD_REQUEST, ResponseMessage.RENT_FIND_ERROR);
        }
    }
    @DeleteMapping(value="/delete/{rentId}")
    @ApiOperation(value = "대실 삭제", notes = "rentId를 받아서 대실을 삭제한다.")
    public DefaultResponse<String> rentDelete(@PathVariable Integer rentId, HttpServletRequest request){
        try {
            Integer deletedCnt = rentService.deleteById(rentId);
            int roomContentId = rentService.findRoomContentIdByRentId(rentId);
            jwtAuthenticationProvider.setOwnerJwtTokenCheck(roomContentId, request);
            return new DefaultResponse<String>(StatusCode.OK, ResponseMessage.RENT_DELETE_OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new DefaultResponse<>(StatusCode.BAD_REQUEST, ResponseMessage.RENT_DELETE_ERROR);
        }
    }
}
