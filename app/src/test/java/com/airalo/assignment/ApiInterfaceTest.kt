package com.airalo.assignment

import com.airalo.assignment.data.model.Image
import com.airalo.assignment.data.model.ModelPackagesResponse
import com.airalo.assignment.data.model.PackagesItem
import com.airalo.assignment.data.remote.ApiInterface
import com.airalo.assignment.data.remote.ApiResponse
import com.airalo.assignment.data.remote.message
import com.airalo.assignment.data.remote.onErrorSuspend
import com.airalo.assignment.data.remote.onExceptionSuspend
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.Response

class ApiInterfaceTest {

    private val apiInterface: ApiInterface = mockk()

    @Test
    fun fetchAvailablePackages_success() = runBlocking {
        // Mock the success response
        val expectedResponse = ModelPackagesResponse(
            id = 1,
            title = "Package Title",
            image = Image(url = "image_url"),
            packages = listOf(mockk<PackagesItem>()),
            slug = "package-slug",
        )
        val successResponse = ApiResponse.ApiSuccessResponse(Response.success(expectedResponse))
        coEvery { apiInterface.fetchAvailablePackages(any()) } returns successResponse

        // Call the method to be tested
        val response = apiInterface.fetchAvailablePackages("philippines")

        // Assert the response
        assertNotNull(response)
        assertTrue(response is ApiResponse.ApiSuccessResponse)
        assertEquals(expectedResponse, (response as ApiResponse.ApiSuccessResponse).data)
    }

    @Test
    fun fetchAvailablePackages_error(): Unit = runBlocking {
        // Mock the error response

        val errorResponseBody = ResponseBody.create(
            "application/json".toMediaTypeOrNull(),
            "Bad Request",
        )
        val errorResponse = ApiResponse.ApiFailureResponse.Error<ModelPackagesResponse>(
            Response.error(400, errorResponseBody),
        )
        coEvery { apiInterface.fetchAvailablePackages(any()) } returns errorResponse

        // Call the method to be tested
        val response = apiInterface.fetchAvailablePackages("us")

        // Assert the response
        assertNotNull(response)
        assertTrue(response is ApiResponse.ApiFailureResponse.Error)
        response.onErrorSuspend {
            assertEquals("Bad Request", message())
        }
    }

    @Test
    fun fetchAvailablePackages_exception(): Unit = runBlocking {
        // Mock an exception
        val exceptionResponse = ApiResponse.ApiFailureResponse.Exception<ModelPackagesResponse>(
            RuntimeException("API Call Failed"),
        )
        coEvery { apiInterface.fetchAvailablePackages(any()) } returns exceptionResponse

        // Call the method to be tested
        val response = apiInterface.fetchAvailablePackages("us")

        // Assert the response
        assertNotNull(response)
        assertTrue(response is ApiResponse.ApiFailureResponse.Exception)
        response.onExceptionSuspend {
            assertEquals("API Call Failed", exception.message)
        }
    }
}
