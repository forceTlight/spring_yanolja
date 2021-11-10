package com.yanolja.repository.owner;

import com.yanolja.domain.OwnerDTO;

public interface OwnerRepository {
    public OwnerDTO insert(OwnerDTO owner);
    public Integer updateById(OwnerDTO owner);
    public Integer deleteById(Integer id);
    public OwnerDTO findById(Integer id);
}
