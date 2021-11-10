package com.yanolja.repository.room;

import com.yanolja.domain.RoomDTO;

public interface RoomRepository {
    public RoomDTO insert(RoomDTO room);
    public Integer updateById(RoomDTO room);
    public Integer deleteById(Integer id);
    public RoomDTO findById(Integer id);
}
