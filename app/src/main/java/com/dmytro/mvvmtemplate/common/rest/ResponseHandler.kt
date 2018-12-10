package com.dmytro.mvvmtemplate.common.rest

import com.dmytro.mvvmtemplate.common.rest.exceptions.NetworkException
import io.reactivex.functions.Function
import com.dmytro.mvvmtemplate.common.rest.response.*

class ResponseHandler<R : BaseResponse>(private val url: String) : Function<R, R> {

    override fun apply(response: R): R = when {
//        response.responseCode != SUCCESS -> {
        response.page == 0 -> {
            throw NetworkException.serverError(url,
                    response.responseCode ?: "null", response.errorMessage)
        }
        !response.isResponseValid() -> {
            throw NetworkException.invalidResponseError(url,
                    "${response.javaClass.simpleName} is not valid")
        }
        else -> response
    }

}