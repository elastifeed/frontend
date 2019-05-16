package de.htw.saar.frontend.controller;

import de.htw.saar.frontend.master.MasterController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends MasterController
{
    private String aktuellerArtikel;

    public String getAktuellerArtikel(){
        return this.aktuellerArtikel;
    }


    @RequestMapping("/")
    public String index() {
        return view("index",this);
    }

    @RequestMapping("/artikel")
    public String artikel(String id)
    {
        if (id == null)
        {
            return index();
        }
        else
        {
            this.aktuellerArtikel = id;
            return view("artikel",this);
        }

    }
}
