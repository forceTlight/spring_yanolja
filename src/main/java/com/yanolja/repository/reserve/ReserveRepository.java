package com.yanolja.repository.reserve;

import com.yanolja.domain.ReserveDTO;

public interface ReserveRepository {
    public ReserveDTO insert(ReserveDTO reserve);
    public Integer updateById(ReserveDTO reserve);
    public Integer deleteById(Integer id);
    public ReserveDTO findById(Integer id);
}
