package com.ge.predix.solsvc.boot.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * An example of creating a Rest api using Spring Annotations @RestController.
 * 
 * 
 * 
 * @author predix
 */
@RestController
public class TestRestController
{

    /**
     * 
     */
    public TestRestController()
    {
        super();
    }

    /**
     * Sample Endpoint which returns a Welcome Message
     * 
     * @param echo
     *            - the string to echo back
     * @return -
     */
    @SuppressWarnings("nls")
    @RequestMapping(value = "test/echo", method = RequestMethod.GET)
    public String index(@RequestParam(value = "echo", defaultValue = "echo this text") String echo)
    {
        return "Greetings from Predix Spring Boot! echo=" + echo + " " + (new Date());
    }

    /**
     * @return -
     */
    @SuppressWarnings("nls")
    @RequestMapping(value = "test/health", method = RequestMethod.GET)
    public String health()
    {
        return String.format("{\"status\":\"up\", \"date\": \" " + new Date() + "\"}");
    }

}
