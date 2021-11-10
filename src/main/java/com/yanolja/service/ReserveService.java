package com.yanolja.service;

import com.yanolja.domain.ReserveDTO;

public interface ReserveService {
    public ReserveDTO insert(ReserveDTO reserve);
    public Integer updateById(ReserveDTO reserve);
    public Integer deleteById(Integer id);
}
