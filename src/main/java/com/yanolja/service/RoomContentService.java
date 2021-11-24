package com.yanolja.service;

import com.yanolja.configuration.DefaultException;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.StatusCode;
import com.yanolja.domain.RoomContent;
import com.yanolja.repository.roomContent.RoomContentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoomContentService {
    @Autowired
    private RoomContentRepository roomContentRepository;
    public RoomContent.RegisterReq insert(RoomContent.RegisterReq roomContent) {
        return roomContentRepository.insert(roomContent);
    }
    public Integer updateById(RoomContent.PatchReq roomContent) {
        return roomContentRepository.updateById(roomContent);
    }
    public Integer deleteById(Integer id) {
        return roomContentRepository.deleteById(id);
    }
    public RoomContent.Info findById(Integer id) throws DefaultException {
        RoomContent.Info roomContent = roomContentRepository.findById(id);
        if(roomContent == null)
            throw new DefaultException(StatusCode.ROOMCONTENT_SEARCH_FAIL, ResponseMessage.ROOMCONTENT_FIND_ERROR);
        return roomContent;
    }
    public List<RoomContent.Info> findByRoomId(Integer id) throws DefaultException {
        List<RoomContent.Info> roomContentList = roomContentRepository.findByRoomId(id);
        if(roomContentList.size() == 0)
            throw new DefaultException(StatusCode.ROOMCONTENT_SEARCH_FAIL, ResponseMessage.ROOMCONTENT_FIND_ERROR);
        return roomContentList;
    }

    // roomContentId로 roomId 반환
    public int findRoomIdByRoomContentId(Integer id){
        return roomContentRepository.findRoomIdByRoomContentId(id);
    }
}
