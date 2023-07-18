package com.airalo.assignment

import com.airalo.assignment.data.remote.ApiResponse
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class ApiResponseTest {

    @Test
    fun `test data is is not null when got the Success response`() {
        val successResponse = ApiResponse.ApiSuccessResponse(Response.success("expectedResponse"))
        MatcherAssert.assertThat(successResponse.data, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(successResponse.data, CoreMatchers.`is`("hello_test"))
    }

    @Test
    fun `test message is not null or blank when having the Exception response`() {
        val exception = Exception("message")
        val apiResponse = ApiResponse.exception<String>(exception)
        MatcherAssert.assertThat(apiResponse.message, CoreMatchers.`is`("message"))
    }
}
