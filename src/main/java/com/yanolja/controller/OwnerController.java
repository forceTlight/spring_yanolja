package com.yanolja.controller;

import com.yanolja.configuration.DefaultException;
import com.yanolja.configuration.DefaultResponse;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.StatusCode;
import com.yanolja.domain.Owner;
import com.yanolja.repository.owner.OwnerRepository;
import com.yanolja.service.OwnerService;
import com.yanolja.utils.JwtAuthenticationProvider;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    OwnerService ownerService;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;
    /*
        @param Owner.RegisterReq
        회원가입
     */
    @PostMapping(value="/register")
    @ApiOperation(value = "점주등록", notes = "점주를 새로 등록함.")
    public DefaultResponse<Owner.RegisterRes> ownerCreate(@RequestBody Owner.RegisterReq ownerReq){
        try {
            Owner.RegisterRes ownerRes = ownerService.register(ownerReq);
            return new DefaultResponse<Owner.RegisterRes>(StatusCode.CREATED,ResponseMessage.CREATED_USER, ownerRes);
        }catch(DefaultException e) { // 암호화 에러
            log.error(e.toString());
            return new DefaultResponse<>(e.getStatusCode(),e.getMessage());
        }
    }
    /*
        @param Owner.LoginReq, HttpServletResponse
        로그인
     */
    @PostMapping(value="/login")
    @ApiOperation(value = "로그인", notes = "점주를 새로 등록함.")
    public DefaultResponse<Owner.LoginRes> ownerLogin(@RequestBody Owner.LoginReq ownerReq, HttpServletResponse response){
        try {
            Owner.LoginRes loginRes= ownerService.login(ownerReq);
            // JWT TOKEN 설정
            String token = jwtAuthenticationProvider.createToken(ownerReq.getEmail());
            response.setHeader("X-AUTH-TOKEN", token);
            return new DefaultResponse<Owner.LoginRes>(StatusCode.LOGIN_SUCCESS, ResponseMessage.LOGIN_SUCCESS, loginRes);
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
    /*
        @param Owner.NickNameReq, HttpServletRequest
        @required jwtToken
        점주 닉네임 수정
     */
    @PatchMapping(value="/{ownerId}")
    @ApiOperation(value = "점주 닉네임 수정", notes = "점주 닉네임을 수정한다.")
    public DefaultResponse<String> ownerUpdate(@PathVariable("ownerId") int ownerId, @RequestBody Owner.NickNameReq owner,
                                               HttpServletRequest request){
        Owner.PatchReq ownerReq = Owner.PatchReq.builder().ownerId(ownerId).nickName(owner.getNickName()).build();
        try {
            ownerService.updateNickName(ownerReq);
            // ownerId로 email 가져오기
            String ownerEmail = ownerRepository.getEmailById(ownerId);
            // jwt에서 email 추출
            String jwtEmail = jwtAuthenticationProvider.getJwtEmail(request);
                // jwt validation
            if(!jwtEmail.equals(ownerEmail) || jwtEmail == null){
                throw new DefaultException(StatusCode.JWT_ERROR, ResponseMessage.INVALID_JWT);
            }
            return new DefaultResponse<String>(StatusCode.OK, ResponseMessage.UPDATE_USER);
        }catch(DefaultException e) {
            log.error(e.toString());
            return new DefaultResponse<String>(e.getStatusCode(), e.getMessage());
        }
    }
    /*
        @param ownerId
        점주 삭제
     */
    @PatchMapping(value="/delete/{ownerId}")
    @ApiOperation(value = "점주삭제", notes = "ownerId를 받아서 점주를 삭제한다.")
    public DefaultResponse<String> ownerDelete(@PathVariable("ownerId") int ownerId){
        try {
            ownerService.deleteById(ownerId);
            return new DefaultResponse<String>(StatusCode.OK, ResponseMessage.DELETE_USER);
        }catch(DefaultException e) {
            log.error(e.toString());
            return new DefaultResponse<String>(e.getStatusCode(), e.getMessage());
        }
    }
}
