package com.olavarria.domain.usecases

import com.olavarria.core.common.ApiState
import com.olavarria.data.api.response.*
import com.olavarria.data.repository.ApiRepository
import com.olavarria.data.repository.LocalRepository
import com.olavarria.domain.CoroutinesTestRule
import com.olavarria.domain.bean.Pagination
import com.olavarria.domain.bean.SportEvents
import com.olavarria.domain.bean.mapper.toLocal
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.*

class GetListEventsUseCaseTest {

    @RelaxedMockK
    private lateinit var apiRepository: ApiRepository

    @RelaxedMockK
    private lateinit var localRepository: LocalRepository

    private lateinit var getListEventsUseCase: GetListEventsUseCase

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getListEventsUseCase = GetListEventsUseCase(apiRepository, localRepository)

    }

    @Test
    fun `cuando la data es nula`() = coroutinesTestRule.scope.runTest {

        coEvery {
            apiRepository.getSportEvents(hashMapOf())
        } returns ApiState.ErrorCode("usuario no encontrado")

        getListEventsUseCase(GetListEventsUseCase.GetListEventsParams(hashMapOf()), this) {
            if (it is ApiState.ErrorCode)
                Assert.assertEquals("usuario no encontrado", it.message)
        }
    }

    @Test
    fun `cuando se obtiene el usuario exitosamente`() = runTest {

        val result = ApiResponse(SportEventsResponse(listOf(), PaginationResponse(1,2,3,4,5,6,7,8), listOf()))



        coEvery {
            apiRepository.getSportEvents(hashMapOf())
        } returns ApiState.Success(result)

        getListEventsUseCase(GetListEventsUseCase.GetListEventsParams(hashMapOf()), this){
            if (it is ApiState.Success)
                Assert.assertEquals(result.body.pagination.first, it.data.pagination.first)
        }
    }
}