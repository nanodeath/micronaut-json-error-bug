package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;

import javax.validation.Valid;

@Controller
public class BookController {

    @Post
    public void save(@Valid @Body Book book) {
        throw new RuntimeException("boom");
    }

    @Error
    public HttpResponse<JsonError> errorHandler(HttpRequest request, Exception exception) {
        JsonError error = new JsonError("Error: " + exception.getMessage())
                .link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>status(HttpStatus.BAD_REQUEST, "Fix Your Stuff")
                .body(error);
    }
}
