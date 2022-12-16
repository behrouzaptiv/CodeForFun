package com.aptiv.fika.domain.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <T> the local model input type
 * @param <V> the model return type
 */
interface IEntityMapper<TEntity, TDomain> {

    fun mapFromEntity(entity: TEntity): TDomain
}
