package com.yanolja.repository.roomContent;

import com.yanolja.domain.RoomContentDTO;

public interface RoomContentRepository {
    public RoomContentDTO insert(RoomContentDTO roomContent);
    public Integer updateById(RoomContentDTO roomContent);
    public Integer deleteById(Integer id);
    public RoomContentDTO findById(Integer id);
}
