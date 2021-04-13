package com.bielecki.springsecuriy;

import org.springframework.stereotype.Controller;

@Controller

public class SecuredController {


    public String securedPage(){
        return "secured";
    }
}
