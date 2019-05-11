package de.htw.saar.frontend.controller;

import de.htw.saar.frontend.master.MasterController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends MasterController {
    @RequestMapping("/")
    public String main(){
        return view("index",this);
    }

    @RequestMapping("/index")
    public String index() {
        return view("index",this);
    }
}
