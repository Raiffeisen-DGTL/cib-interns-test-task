package com.projects.raifservice.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(ex: BaseException): ResponseEntity<ApiError> {
        val apiError = ApiError(
            errorCode = ex.errorCode,
            description = ex.description
        )
        return ResponseEntity(apiError, ex.httpStatus)
    }
}