package de.mediqon.generic.data_dashboard_common.adapters;

import java.util.Collection;
import java.util.List;


public interface IModelDtoAdapter<M, E> {

  M fromDto(E edo);

  E toDto(M model);

  List<E> toDtoList(final Collection<M> modelList);

  List<M> fromDtoList(final Collection<E> dtoList);

}
