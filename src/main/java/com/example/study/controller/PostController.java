package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // end point, -> localhost:8080/api
public class PostController {

    // json (default), xml, multipart-form / text-plain
    @RequestMapping(method = RequestMethod.POST, path = "/postMethod")
    public String postRequest(@RequestBody SearchParam searchParam) {


        return "OK";
    }
}
