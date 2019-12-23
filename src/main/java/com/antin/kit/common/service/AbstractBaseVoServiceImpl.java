package com.antin.kit.common.service;


import com.antin.kit.common.converter.IBaseVoConverter;
import com.antin.kit.common.persistence.domain.Base;
import com.antin.kit.common.persistence.repository.BaseRepository;
import com.antin.kit.common.util.Constants;
import com.antin.kit.common.vo.BaseVo;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public abstract class AbstractBaseVoServiceImpl<T extends Base, V extends BaseVo> implements BaseVoService<V> {

    protected abstract BaseRepository<T> getJpaRepository();

    protected abstract IBaseVoConverter<V, T> getVoConverter();

    @Override
    public V add(V vo){
        throw new NotImplementedException("NotImplemented");
    }

    @Override
    public V edit(V vo) {
        throw new NotImplementedException("NotImplemented");
    }

    @Override
    public V findById(String id) {
        throw new NotImplementedException("NotImplemented");
    }

    @Override
    public Boolean delete(String id, String jwtToken) {
        throw new NotImplementedException("NotImplemented");
    }

    protected Map<String, Object> constructMapReturn(Page<T> page) {
        Collection<V> vos = getVoConverter().transferListOfModelToListOfVO(page.getContent(), null);
        return constructMapReturn(vos, page.getTotalElements(), page.getTotalPages());
    }

    protected Map<String, Object> constructMapReturn(Collection voList, long totalElements, int totalPages) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put(Constants.PageParameter.LIST_DATA, voList);
        map.put(Constants.PageParameter.TOTAL_ELEMENTS, totalElements);
        map.put(Constants.PageParameter.TOTAL_PAGES, totalPages);

        return map;
    }

    protected Sort getSortBy(String sortBy, String direction) {
        if (StringUtils.equalsIgnoreCase(direction, "asc")) {
            return Sort.by(Sort.Direction.ASC, sortBy);
        } else {
            return Sort.by(Sort.Direction.DESC, sortBy);
        }
    }
}
