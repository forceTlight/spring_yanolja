package com.yanolja.controller;

import com.yanolja.domain.RentDTO;
import com.yanolja.service.RentServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rent")
public class RentController {
    @Autowired
    RentServiceImpl rentService;

    @PostMapping(value="/register")
    @ApiOperation(value = "대실등록", notes = "대실을 새로 등록함.")
    public ResponseEntity<RentDTO> rentCreate(@RequestBody RentDTO rent){
        try {
            log.debug("rent = {}", rent.toString());
            return new ResponseEntity<>(rentService.insert(rent), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/find/{rentId}")
    @ApiOperation(value = "대실 상세조회", notes = "rentId로 대실을 조회함.")
    public ResponseEntity<RentDTO> rentFindById(@PathVariable Integer rentId){
        try{
            log.debug("rent = {}", rentId);
            RentDTO rent = rentService.findById(rentId);
            return new ResponseEntity<>(rentService.findById(rentId), HttpStatus.OK);
        }catch(Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/update")
    @ApiOperation(value = "대실 수정", notes = "대실 레이블을 수정한다.")
    public ResponseEntity<String> rentUpdate(@RequestBody RentDTO rent){
        try {
            log.debug("rent = {}", rent.toString());
            Integer updatedCnt = rentService.updateById(rent);
            return new ResponseEntity<>(String.format("%d updated", updatedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value="/delete/{rentId}")
    @ApiOperation(value = "대실 삭제", notes = "rentId를 받아서 대실을 삭제한다.")
    public ResponseEntity<String> rentDelete(@PathVariable Integer rentId){
        try {
            log.debug("rent id = {}", rentId);
            Integer deletedCnt = rentService.deleteById(rentId);
            return new ResponseEntity<>(String.format("%d deleted.", deletedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
