package com.antin.kit.common.converter;


import com.antin.kit.common.persistence.domain.Base;
import com.antin.kit.common.util.ExtendedSpringBeanUtil;
import com.antin.kit.common.vo.BaseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Component
public class BaseVoConverter implements IBaseVoConverter<BaseVo, Base> {

    /**
     * transfer value from vo object to domain object
     * for enum value, please do manually using Enum.values()[ordinal]
     *
     * @param vo
     * @param model
     * @return
     */
    @Override
    public Base transferVoToModel(BaseVo vo, Base model) {
        ExtendedSpringBeanUtil.copySpecificProperties(vo, model, new String[]{
                "createdBy", "createdAt", "updatedBy", "updatedAt"
        });
        if (vo.getIsActive() != null) {
            model.setActive(vo.getIsActive());
        }
        if (!StringUtils.isEmpty(vo.getId())) {
            model.setUuid(vo.getId());
        }

        if (vo.getCreatedAt() != null) {
            model.setCreatedAt(new Date(vo.getCreatedAt()));
        }
        if (vo.getUpdatedAt() != null) {
            model.setUpdatedAt(new Date(vo.getUpdatedAt()));
        }
        return model;
    }

    /**
     * transfer value from list of domain object to list of vo object
     *
     * @param models
     * @param vos
     * @return
     */
    @Override
    public Collection<BaseVo> transferListOfModelToListOfVO(Collection<Base> models, Collection<BaseVo> vos) {

        if (null == vos) vos = new ArrayList<BaseVo>();

        if (null == models) return vos;

        for (Base model : models) {
            BaseVo vo = new BaseVo();
            transferModelToVO(model, vo);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public Collection<Base> transferListOfVoToListOfModel(Collection<BaseVo> vos, Collection<Base> models) {
        return null;
    }

    /**
     * transfer value from domain object to vo object
     *
     * @param model
     * @param vo
     * @return
     */
    @Override
    public BaseVo transferModelToVO(Base model, BaseVo vo) {
        ExtendedSpringBeanUtil.copySpecificProperties(model, vo, new String[]{
                "createdBy", "updatedBy" });

        if (model.getCreatedAt() != null) {
            vo.setCreatedAt(model.getCreatedAt().getTime());
        }

        if (model.getUpdatedAt() != null) {
            vo.setUpdatedAt(model.getUpdatedAt().getTime());
        }

        if (model.getActive() != null) {
            vo.setIsActive(model.getActive());
        }
        vo.setId(model.getUuid());
        return vo;
    }
}