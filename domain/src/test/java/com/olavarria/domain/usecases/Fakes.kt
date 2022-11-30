package com.olavarria.domain.usecases

import com.olavarria.data.api.response.ApiResponse
import com.olavarria.data.api.response.LoginResponse
import com.olavarria.data.api.response.UserResponse

val fakeLogin = ApiResponse(LoginResponse("1112312", "12-01-01","Bearer", UserResponse("123abc")))

class FakeRemoteDataSource {

}

class fakeLocalDataSource {

}