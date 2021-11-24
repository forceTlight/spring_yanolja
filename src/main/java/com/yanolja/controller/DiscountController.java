package com.yanolja.controller;

import com.yanolja.configuration.DefaultResponse;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.StatusCode;
import com.yanolja.domain.Discount;
import com.yanolja.service.DiscountService;
import com.yanolja.service.LodgeService;
import com.yanolja.service.RentService;
import com.yanolja.utils.JwtAuthenticationProvider;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/discount")
public class DiscountController {
    @Autowired
    DiscountService discountService;
    @Autowired
    RentService rentService;
    @Autowired
    LodgeService lodgeService;
    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;

    @PostMapping(value="/register")
    @ApiOperation(value = "할인등록", notes = "할인을 새로 등록함.")
    public DefaultResponse<Discount.Info> discountCreate(@RequestBody Discount.RegisterReq discount, HttpServletRequest request){
        try {
            Integer lodgeId = discount.getLodgeId();
            int roomContentId;
            if(lodgeId == null){
                roomContentId = rentService.findRoomContentIdByRentId(discount.getRentId());
            }else{
                roomContentId = lodgeService.findRoomContentIdByLodgeId(discount.getLodgeId());
            }
            jwtAuthenticationProvider.setOwnerJwtTokenCheck(roomContentId, request);
            discountService.insert(discount);
            return new DefaultResponse<>(StatusCode.DISCOUNT_REGISTER_SUCCESS, ResponseMessage.DISCOUNT_REGISTER_OK);
        }catch(Exception e) {
            return new DefaultResponse<>(StatusCode.DISCOUNT_REGISTER_FAIL, ResponseMessage.DISCOUNT_REGISTER_ERROR);
        }
    }
    @GetMapping(value="/find/{discountId}")
    @ApiOperation(value = "할인상세조회", notes = "discountId로 할인을 조회함.")
    public DefaultResponse<Discount.Info> discountFindById(@PathVariable Integer discountId){
        try{
            Discount.Info discount = discountService.findById(discountId);
            return new DefaultResponse<>(StatusCode.DISCOUNT_SEARCH_SUCCESS, ResponseMessage.DISCOUNT_FIND_OK, discount);
        }catch(Exception e){
            return new DefaultResponse<>(StatusCode.DISCOUNT_SEARCH_FAIL, ResponseMessage.DISCOUNT_FIND_ERROR);
        }
    }
    @PutMapping(value="/update")
    @ApiOperation(value = "할인수정", notes = "할인 레이블을 수정한다.")
    public DefaultResponse<String> discountUpdate(@RequestBody Discount.PatchReq discount, HttpServletRequest request){
        try {
            Integer lodgeId = discount.getLodgeId();
            int roomContentId;
            if(lodgeId == null){
                roomContentId = rentService.findRoomContentIdByRentId(discount.getRentId());
            }else{
                roomContentId = lodgeService.findRoomContentIdByLodgeId(discount.getLodgeId());
            }
            jwtAuthenticationProvider.setOwnerJwtTokenCheck(roomContentId, request);
            Integer updatedCnt = discountService.updateById(discount);
            return new DefaultResponse<>(StatusCode.DISCOUNT_UPDATE_SUCCESS, ResponseMessage.DISCOUNT_UPDATE_OK);
        }catch(Exception e) {
            return new DefaultResponse<>(StatusCode.DISCOUNT_UPDATE_FAIL, ResponseMessage.DISCOUNT_UPDATE_ERROR);
        }
    }
    @DeleteMapping(value="/delete/{discountId}")
    @ApiOperation(value = "할인삭제", notes = "discountId을 받아서 할인을 삭제한다.")
    public DefaultResponse<String> discountDelete(@PathVariable Integer discountId){
        try {
            Integer deletedCnt = discountService.deleteById(discountId);
            return new DefaultResponse<>(StatusCode.DISCOUNT_DELETE_SUCCESS, ResponseMessage.DISCOUNT_DELETE_OK);
        }catch(Exception e) {
            return new DefaultResponse<>(StatusCode.DISCOUNT_DELETE_FAIL, ResponseMessage.DISCOUNT_DELETE_ERROR);
        }
    }
}
