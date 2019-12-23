package com.antin.kit.common.service;


import com.antin.kit.common.exception.ServiceException;
import com.antin.kit.common.persistence.domain.Base;
import com.antin.kit.common.vo.BaseVo;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Optional;

public abstract class BaseVoServiceImpl<T extends Base, V extends BaseVo> extends AbstractBaseVoServiceImpl<T, V> {

    @Override
    public V add(V vo) {
        validateAdd(vo);
        T t = getVoConverter().transferVoToModel(vo, null);
        if (null == t) throw new ServiceException("failed to convert from vo to model when add.");
        t.setCreatedAt(new Date());
        return this.add(t);
    }

    protected V add(T t) {
        T updated = getJpaRepository().save(t);

        if (null == updated) {
            throw new ServiceException("Failed save progress domain on database : " + t);
        }
        V vo = getVoConverter().transferModelToVO(updated, null);
        return vo;
    }

    protected T addModel(T t) {
        T updated = getJpaRepository().save(t);

        if (null == updated) {
            throw new ServiceException("Failed save progress domain on database : " + t);
        }

        return updated;
    }

    @Override
    public V edit(V vo) {
        validateEdit(vo);

        Optional<T> existing = getJpaRepository().findById(vo.getId());
        if (!existing.isPresent()) {
            throw new ServiceException("Failed to update, data not found : " + vo.getId());
        }

        T model = getVoConverter().transferVoToModel(vo, existing.get());
        model.setUpdatedAt(new Date());
        return this.add(model);
    }

    protected V edit(T t, String username) {
        t.setUpdatedBy(username);
        t.setUpdatedAt(new Date());
        return this.add(t);
    }

    @Override
    public Boolean delete(String id, String username) {
        if (StringUtils.isEmpty(username)) {
            throw new ServiceException("you're not auth!");
        }

        safeDelete(id, username);
        return true;
    }

    public V safeDelete(String id, String username) {
        validateDelete(id);

        Optional<T> existing = getJpaRepository().findById(id);

        if (existing == null || (Object)existing == Optional.empty()) {
            throw new ServiceException("data not found");
        }

        T model = existing.get();

        model.setUpdatedBy(username);
        model.setUpdatedAt(new Date());
        model.setActive(false);

        return this.add(model);
    }

    public V safeDelete(V vo, String username) {

        vo.setUpdatedBy(username);
        vo.setUpdatedAt(System.currentTimeMillis());

        validateEdit(vo);

        Optional<T> existing = getJpaRepository().findById(vo.getId());

        if (!existing.isPresent()) {
            throw new ServiceException("Failed to delete, data not found : " + vo.getId());
        }

        T model = getVoConverter().transferVoToModel(vo, existing.get());
        model.setUpdatedAt(new Date());
        model.setActive(false);

        return this.add(model);
    }

    @Override
    public V findById(String id) {
        Optional<T> existing = getJpaRepository().findById(id);
        if (!existing.isPresent()) return null;

        T t = existing.get();

        if (null == t) {
            return null;
        }
        V vo = getVoConverter().transferModelToVO(t, null);
        return vo;
    }

    protected void validateAdd(V vo) {
        if (null == vo.getCreatedBy() || StringUtils.isEmpty(vo.getCreatedBy())) {
            throw new ServiceException("createdBy must not be null");
        }
    }

    protected void validateDelete(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("id must not be null");
        }
    }

    protected void validateEdit(V vo) {
        if (StringUtils.isEmpty(vo.getId())) {
            throw new ServiceException("id must not be null");
        }

        if (StringUtils.isEmpty(vo.getUpdatedBy())) {
            throw new ServiceException("UpdatedBy must not be null");
        }
    }
}
