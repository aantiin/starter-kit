package com.antin.kit.common.service;

import com.antin.kit.common.vo.BaseVo;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public interface BaseVoService<V extends BaseVo> {
    public V add(V vo);

    public V edit(V vo);

    public Boolean delete(String id, String jwtToken);

    public V findById(String id);
}
