package com.yanolja.repository.lodge;

import com.yanolja.domain.LodgeDTO;

public interface LodgeRepository {
    public LodgeDTO insert(LodgeDTO lodge);
    public Integer updateById(LodgeDTO lodge);
    public Integer deleteById(Integer id);
    public LodgeDTO findById(Integer id);
}
