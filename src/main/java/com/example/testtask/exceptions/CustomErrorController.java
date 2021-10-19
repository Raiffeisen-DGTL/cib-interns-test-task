package com.example.testtask.exceptions;

import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Controller
public class CustomErrorController implements ErrorController {

  private static final String PATH = "/error";
  private ErrorAttributes errorAttributes;

  @RequestMapping(CustomErrorController.PATH)
  public ResponseEntity<ErrorDto> error(WebRequest webRequest) {
    Map<String, Object> attributes = errorAttributes.getErrorAttributes(
        webRequest,
        ErrorAttributeOptions.of(Include.EXCEPTION, Include.MESSAGE)
    );
    return ResponseEntity
        .status((Integer) attributes.get("status"))
        .body(ErrorDto
            .builder()
            .error((String) attributes.get("error"))
            .errorDescription((String) attributes.get("message"))
            .build()
        );
  }

}
