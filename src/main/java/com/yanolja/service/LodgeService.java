package com.yanolja.service;

import com.yanolja.domain.LodgeDTO;

public interface LodgeService {
    public LodgeDTO insert(LodgeDTO lodge);
    public Integer updateById(LodgeDTO lodge);
    public Integer deleteById(Integer id);
}
