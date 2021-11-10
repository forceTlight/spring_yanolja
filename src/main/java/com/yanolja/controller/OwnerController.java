package com.yanolja.controller;

import com.yanolja.domain.OwnerDTO;
import com.yanolja.service.OwnerServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/owner")
public class OwnerController {
    
    @Autowired
    OwnerServiceImpl ownerService;

    @PostMapping(value="/register")
    @ApiOperation(value = "오너등록", notes = "오너를 새로 등록함.")
    public ResponseEntity<OwnerDTO> ownerCreate(@RequestBody OwnerDTO owner){
        try {
            log.debug("owner = {}", owner.toString());
            return new ResponseEntity<>(ownerService.insert(owner), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/find/{ownerId}")
    @ApiOperation(value = "오너상세조회", notes = "ownerId로 오너를 조회함.")
    public ResponseEntity<OwnerDTO> ownerFindById(@PathVariable Integer ownerId){
        try{
            log.debug("owner = {}", ownerId);
            OwnerDTO owner = ownerService.findById(ownerId);
            return new ResponseEntity<>(ownerService.findById(ownerId), HttpStatus.OK);
        }catch(Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/update")
    @ApiOperation(value = "오너수정", notes = "오너 레이블을 수정한다.")
    public ResponseEntity<String> ownerUpdate(@RequestBody OwnerDTO owner){
        try {
            log.debug("owner = {}", owner.toString());
            Integer updatedCnt = ownerService.updateById(owner);
            return new ResponseEntity<>(String.format("%d updated", updatedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value="/delete/{ownerId}")
    @ApiOperation(value = "오너삭제", notes = "ownerId를 받아서 오너를 삭제한다.")
    public ResponseEntity<String> ownerDelete(@PathVariable Integer ownerId){
        try {
            log.debug("owner id = {}", ownerId);
            Integer deletedCnt = ownerService.deleteById(ownerId);
            return new ResponseEntity<>(String.format("%d deleted.", deletedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
