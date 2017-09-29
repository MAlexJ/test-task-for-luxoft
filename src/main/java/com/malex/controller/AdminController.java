package com.malex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(method = RequestMethod.GET)
    public String admin_GET(){
        return "admin";
    }

    @RequestMapping(path = "/adminHome",method = RequestMethod.GET)
    public String admin_home_GET(){
        return "admin/adminHome";
    }

    @RequestMapping(path = "/adminCreate",method = RequestMethod.GET)
    public String admin_create_GET(){
        return "admin/adminCreate";
    }

    @RequestMapping(path = "/adminUpdate",method = RequestMethod.GET)
    public String admin_update_GET(){
        return "admin/adminUpdate";
    }

    @RequestMapping(path = "/adminDelete",method = RequestMethod.GET)
    public String admin_delete_GET(){
        return "admin/adminDelete";
    }

    @RequestMapping(path = "/adminView",method = RequestMethod.GET)
    public String admin_view_GET(){
        return "admin/adminView";
    }


}
