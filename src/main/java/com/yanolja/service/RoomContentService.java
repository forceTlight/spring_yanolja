package com.yanolja.service;

import com.yanolja.domain.RoomContentDTO;

public interface RoomContentService {
    public RoomContentDTO insert(RoomContentDTO roomContent);
    public Integer updateById(RoomContentDTO roomContent);
    public Integer deleteById(Integer id);
}
