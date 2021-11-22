package com.yanolja.controller;

import com.yanolja.domain.Discount;
import com.yanolja.service.DiscountService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/discount")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @PostMapping(value="/register")
    @ApiOperation(value = "할인등록", notes = "할인을 새로 등록함.")
    public ResponseEntity<Discount> discountCreate(@RequestBody Discount discount){
        try {
            log.debug("discount = {}", discount.toString());
            return new ResponseEntity<>(discountService.insert(discount), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/find/{discountId}")
    @ApiOperation(value = "할인상세조회", notes = "discountId로 할인을 조회함.")
    public ResponseEntity<Discount> discountFindById(@PathVariable Integer discountId){
        try{
            log.debug("discount = {}", discountId);
            Discount discount = discountService.findById(discountId);
            return new ResponseEntity<>(discountService.findById(discountId), HttpStatus.OK);
        }catch(Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/update")
    @ApiOperation(value = "할인수정", notes = "할인 레이블을 수정한다.")
    public ResponseEntity<String> discountUpdate(@RequestBody Discount discount){
        try {
            log.debug("discount = {}", discount.toString());
            Integer updatedCnt = discountService.updateById(discount);
            return new ResponseEntity<>(String.format("%d updated", updatedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value="/delete/{discountId}")
    @ApiOperation(value = "할인삭제", notes = "discountId을 받아서 할인을 삭제한다.")
    public ResponseEntity<String> discountDelete(@PathVariable Integer discountId){
        try {
            log.debug("discount id = {}", discountId);
            Integer deletedCnt = discountService.deleteById(discountId);
            return new ResponseEntity<>(String.format("%d deleted.", deletedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
