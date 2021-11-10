package com.yanolja.service;

import com.yanolja.domain.RoomDTO;

public interface RoomService {
    public RoomDTO insert(RoomDTO room);
    public Integer updateById(RoomDTO room);
    public Integer deleteById(Integer id);
    public RoomDTO findById(Integer id);
}
