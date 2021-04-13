package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // end point, -> localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")
    public String getRequest() {
        return "Hi getMethod";
    }

    @GetMapping("/getParameter") // get and path!
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) {
        System.out.println(id);
        System.out.println(pwd);
        return id + pwd;
    }

    // 개신기함! 자동으로 json type으로 바꿔줘빔;; 스프링 부트에서 기본적으로 json 라이브러리 내장 허덜덜
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());
        return searchParam;
    }
}
