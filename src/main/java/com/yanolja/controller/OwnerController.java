package com.yanolja.controller;

import com.yanolja.configuration.DefaultException;
import com.yanolja.configuration.DefaultResponse;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.Status;
import com.yanolja.domain.Owner;
import com.yanolja.service.OwnerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    OwnerService ownerService;

    // 회원가입
    @PostMapping(value="/register")
    @ApiOperation(value = "점주등록", notes = "점주를 새로 등록함.")
    public DefaultResponse<Owner.RegisterRes> ownerCreate(@RequestBody Owner.RegisterReq ownerReq){
        try {
            log.debug("owner = {}", ownerReq.toString());
            Owner.RegisterRes ownerRes = ownerService.register(ownerReq);
            return new DefaultResponse<Owner.RegisterRes>(Status.CREATED,ResponseMessage.CREATED_USER, ownerRes);
        }catch(DefaultException e) { // 암호화 에러
            log.error(e.toString());
            return new DefaultResponse<>(e.getStatusCode(),e.getMessage());
        }
    }
    // 로그인
    @PostMapping(value="/login")
    @ApiOperation(value = "로그인", notes = "점주를 새로 등록함.")
    public DefaultResponse<Owner.LoginRes> ownerLogin(@RequestBody Owner.LoginReq ownerReq){
        try {
            log.debug("owner = {}", ownerReq.toString());
            return new DefaultResponse<Owner.LoginRes>(Status.LOGIN_SUCCESS, ResponseMessage.LOGIN_SUCCESS,ownerService.login(ownerReq));
        }catch(DefaultException e) { // 복호화 에러, 로그인 실패
            log.error(e.toString());
            return new DefaultResponse<>(e.getStatusCode(), e.getMessage());
        }
    }/*
	@GetMapping(value="")
	@ApiOperation(value = "점주 조회", notes = "닉네임으로 점주를 조회함, 닉네임을 안적으면 전체 점주 리스트를 반환한다.")
	public DefaultRes<OwnerDTO.Info> ownerFindById(@PathVariable(required = false) String name){
		try{
			log.debug("owner = {}", ownerId);
			OwnerDTO owner = ownerService.findById(ownerId);
			return new ResponseEntity<>(ownerService.findById(ownerId), HttpStatus.OK);
		}catch(Exception e){
			log.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
    @PatchMapping(value="/{ownerId}")
    @ApiOperation(value = "점주 닉네임 수정", notes = "점주 닉네임을 수정한다.")
    public DefaultResponse<String> ownerUpdate(@PathVariable("ownerId") int ownerId, @RequestBody Owner.NameReq owner){
        Owner.PatchReq ownerReq = Owner.PatchReq.builder().ownerId(ownerId).name(owner.getName()).build();
        try {
            ownerService.updateNickName(ownerReq);
            return new DefaultResponse<String>(Status.OK, ResponseMessage.UPDATE_USER);
        }catch(DefaultException e) {
            log.error(e.toString());
            return new DefaultResponse<String>(e.getStatusCode(), e.getMessage());
        }
    }
    @PatchMapping(value="/delete/{ownerId}")
    @ApiOperation(value = "점주삭제", notes = "ownerId를 받아서 점주를 삭제한다.")
    public DefaultResponse<String> ownerDelete(@PathVariable("ownerId") int ownerId){
        try {
            log.debug("owner id = {}", ownerId);
            ownerService.deleteById(ownerId);
            return new DefaultResponse<String>(Status.OK, ResponseMessage.DELETE_USER);
        }catch(DefaultException e) {
            log.error(e.toString());
            return new DefaultResponse<String>(e.getStatusCode(), e.getMessage());
        }
    }
}
