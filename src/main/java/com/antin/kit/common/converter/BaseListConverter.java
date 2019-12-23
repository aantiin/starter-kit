package com.antin.kit.common.converter;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseListConverter<VO, MODEL> implements IBaseVoConverter<VO , MODEL > {
    @Override
    public Collection<MODEL> transferListOfVoToListOfModel(Collection<VO> vos, Collection<MODEL> models) {
        if (models == null) {
            models = new ArrayList<>() ;
        }
        for (VO vo : vos ) {
            models.add(this.transferVoToModel(vo, null));
        }
        return models;
    }

    @Override
    public Collection<VO> transferListOfModelToListOfVO(Collection<MODEL> models, Collection<VO> vos) {
        if(vos == null ) {
            vos = new ArrayList<>() ;
        }
        for (MODEL model: models) {
            vos.add(this.transferModelToVO(model , null));
        }
        return vos;
    }
}
