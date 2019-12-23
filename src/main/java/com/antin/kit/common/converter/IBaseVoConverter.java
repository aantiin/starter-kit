package com.antin.kit.common.converter;

import java.util.Collection;

public interface IBaseVoConverter<V, T> {
    public T transferVoToModel(V vo, T model);
    public V transferModelToVO(T model, V vo);
    public Collection<V> transferListOfModelToListOfVO(Collection<T> models, Collection<V> vos);
    public Collection<T> transferListOfVoToListOfModel(Collection<V> vos, Collection<T> models);
}