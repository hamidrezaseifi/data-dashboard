package de.mediqon.generic.data_dashboard_common.adapters;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ModelDtoAdapterBase<M, E>{

    public abstract  M fromDto(E dto);

    public abstract E toDto(M model);

    public List<E> toDtoList(Collection<M> modelList) {
        return modelList.stream().map(m -> toDto(m)).collect(Collectors.toList());
    }


    public List<M> fromDtoList(Collection<E> dtoList) {
        return dtoList.stream().map(e -> fromDto(e)).collect(Collectors.toList());
    }
}
