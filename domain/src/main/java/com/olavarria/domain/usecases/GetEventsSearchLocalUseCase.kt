package com.olavarria.domain.usecases

import com.olavarria.core.common.ApiState
import com.olavarria.core.common.map
import com.olavarria.data.repository.LocalRepository
import com.olavarria.domain.bean.Item
import com.olavarria.domain.bean.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetEventsSearchLocalUseCase @Inject constructor(
    private val localRepository: LocalRepository
) : UseCase<GetEventsSearchLocalUseCase.GetEventsSearchLocalParams, MutableList<Item>>() {

    data class GetEventsSearchLocalParams(val search: String)

    override fun execute(params: GetEventsSearchLocalParams): Flow<ApiState<MutableList<Item>>> {
        return localRepository.getEventsSearch(params.search).map {
            it.map { list ->
                list.map { itemEntity ->
                    itemEntity?.toDomain()
                }
            }
        } as Flow<ApiState<MutableList<Item>>>
    }

}