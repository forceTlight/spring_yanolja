package com.yanolja.controller;

import com.yanolja.domain.ReserveDTO;
import com.yanolja.service.ReserveServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/reserve")
public class ReserveController {
    @Autowired
    ReserveServiceImpl reserveService;

    @PostMapping(value="/register")
    @ApiOperation(value = "예약 등록", notes = "예약을 새로 등록함.")
    public ResponseEntity<ReserveDTO> reserveCreate(@RequestBody ReserveDTO reserve){
        try {
            log.debug("reserve = {}", reserve.toString());
            return new ResponseEntity<>(reserveService.insert(reserve), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/find/{reserveId}")
    @ApiOperation(value = "예약 상세조회", notes = "reserveId로 예약을 조회함.")
    public ResponseEntity<ReserveDTO> reserveFindById(@PathVariable Integer reserveId){
        try{
            log.debug("reserve = {}", reserveId);
            ReserveDTO reserve = reserveService.findById(reserveId);
            return new ResponseEntity<>(reserveService.findById(reserveId), HttpStatus.OK);
        }catch(Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/update")
    @ApiOperation(value = "예약 수정", notes = "예약 레이블을 수정한다.")
    public ResponseEntity<String> reserveUpdate(@RequestBody ReserveDTO reserve){
        try {
            log.debug("reserve = {}", reserve.toString());
            Integer updatedCnt = reserveService.updateById(reserve);
            return new ResponseEntity<>(String.format("%d updated", updatedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value="/delete/{reserveId}")
    @ApiOperation(value = "예약 삭제", notes = "reserveId을 받아서 예약을 삭제한다.")
    public ResponseEntity<String> reserveDelete(@PathVariable Integer reserveId){
        try {
            log.debug("reserve id = {}", reserveId);
            Integer deletedCnt = reserveService.deleteById(reserveId);
            return new ResponseEntity<>(String.format("%d deleted.", deletedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
