package com.olavarria.domain.usecases

import com.olavarria.core.common.ApiState
import com.olavarria.core.common.map
import com.olavarria.data.repository.LocalRepository
import com.olavarria.domain.bean.Item
import com.olavarria.domain.bean.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetListEventsLocalUseCase @Inject constructor(
    private val localRepository: LocalRepository
) : UseCase<Unit, MutableList<Item>>() {

    override fun execute(params: Unit): Flow<ApiState<MutableList<Item>>> {
        return localRepository.getObjects().map {
            it.map { list ->
                list.map { itemEntity ->
                    itemEntity.toDomain()
                }
            }
        } as Flow<ApiState<MutableList<Item>>>
    }
}