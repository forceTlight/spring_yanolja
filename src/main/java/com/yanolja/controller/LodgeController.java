package com.yanolja.controller;

import com.yanolja.domain.LodgeDTO;
import com.yanolja.service.LodgeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/lodge")
public class LodgeController {
    @Autowired
    LodgeService lodgeService;

    @PostMapping(value="/register")
    @ApiOperation(value = "숙박등록", notes = "숙박을 새로 등록함.")
    public ResponseEntity<LodgeDTO> lodgeCreate(@RequestBody LodgeDTO lodge){
        try {
            log.debug("lodge = {}", lodge.toString());
            return new ResponseEntity<>(lodgeService.insert(lodge), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/find/{lodgeId}")
    @ApiOperation(value = "숙박상세조회", notes = "lodgeId로 숙박을 조회함.")
    public ResponseEntity<LodgeDTO> lodgeFindById(@PathVariable Integer lodgeId){
        try{
            log.debug("lodge = {}", lodgeId);
            LodgeDTO lodge = lodgeService.findById(lodgeId);
            return new ResponseEntity<>(lodgeService.findById(lodgeId), HttpStatus.OK);
        }catch(Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/update")
    @ApiOperation(value = "숙박수정", notes = "숙박 레이블을 수정한다.")
    public ResponseEntity<String> lodgeUpdate(@RequestBody LodgeDTO lodge){
        try {
            log.debug("lodge = {}", lodge.toString());
            Integer updatedCnt = lodgeService.updateById(lodge);
            return new ResponseEntity<>(String.format("%d updated", updatedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value="/delete/{lodgeId}")
    @ApiOperation(value = "숙박삭제", notes = "lodgeId를 받아서 숙박을 삭제한다.")
    public ResponseEntity<String> lodgeDelete(@PathVariable Integer lodgeId){
        try {
            log.debug("lodge id = {}", lodgeId);
            Integer deletedCnt = lodgeService.deleteById(lodgeId);
            return new ResponseEntity<>(String.format("%d deleted.", deletedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
