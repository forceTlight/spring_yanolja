package com.yanolja.controller;

import com.yanolja.domain.AmenityDTO;
import com.yanolja.service.AmenityServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/amenity")
public class AmenityController {
    @Autowired
    AmenityServiceImpl amenityService;

    @PostMapping(value="/register")
    @ApiOperation(value = "편의시설 등록", notes = "편의시설을 새로 등록함.")
    public ResponseEntity<AmenityDTO> amenityCreate(@RequestBody AmenityDTO amenity){
        try {
            log.debug("amenity = {}", amenity.toString());
            return new ResponseEntity<>(amenityService.insert(amenity), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/find/{amenityId}")
    @ApiOperation(value = "편의시설 상세조회", notes = "amenityId로 편의시설을 조회함.")
    public ResponseEntity<AmenityDTO> amenityFindById(@PathVariable Integer amenityId){
        try{
            log.debug("amenity = {}", amenityId);
            AmenityDTO amenity = amenityService.findById(amenityId);
            return new ResponseEntity<>(amenityService.findById(amenityId), HttpStatus.OK);
        }catch(Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/update")
    @ApiOperation(value = "편의시설 수정", notes = "편의시설 레이블을 수정한다.")
    public ResponseEntity<String> amenityUpdate(@RequestBody AmenityDTO amenity){
        try {
            log.debug("amenity = {}", amenity.toString());
            Integer updatedCnt = amenityService.updateById(amenity);
            return new ResponseEntity<>(String.format("%d updated", updatedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value="/delete/{amenityId}")
    @ApiOperation(value = "편의시설 삭제", notes = "amenityId을 받아서 편의시설을 삭제한다.")
    public ResponseEntity<String> amenityDelete(@PathVariable Integer amenityId){
        try {
            log.debug("amenity id = {}", amenityId);
            Integer deletedCnt = amenityService.deleteById(amenityId);
            return new ResponseEntity<>(String.format("%d deleted.", deletedCnt), HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
