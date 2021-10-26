package cib.interns.test.task.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ExceptionAdvice {

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(ex: ConstraintViolationException) : List<Message> {

        val list = mutableListOf<Message>()

        ex.constraintViolations.iterator().forEach {
            list.add(Message("VALIDATION_EXCEPTION", it.propertyPath.toString(), it.message))
        }

        return list
    }

    data class Message(val type: String, val path: String, val message: String)
}