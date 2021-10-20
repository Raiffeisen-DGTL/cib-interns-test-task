package com.projects.raifservice.exception

import org.springframework.http.HttpStatus

class RequestParamException : BaseException(
    HttpStatus.BAD_REQUEST,
    "параметры запроса отсутствуют или имеют некорректный формат",
    ""
)