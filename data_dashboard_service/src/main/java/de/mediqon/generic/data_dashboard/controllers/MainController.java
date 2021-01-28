package de.mediqon.generic.data_dashboard.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class MainController {

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Produces(MediaType.TEXT_PLAIN)
    @Get("/")
    public String home() {
        return "home .................";
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Produces(MediaType.TEXT_PLAIN)
    @Get("/test")
    public String test() {
        return "test string .................";
    }

}
