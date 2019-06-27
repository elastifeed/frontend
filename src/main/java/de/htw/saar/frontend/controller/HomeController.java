package de.htw.saar.frontend.controller;

import de.htw.saar.frontend.master.MasterController;
import de.htw.saar.frontend.model.Artikel;
import de.htw.saar.frontend.service.ArtikelService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;

@Named
@RequestMapping("/")
public class HomeController extends MasterController
{
    private ArtikelService artikelService = new ArtikelService();

    private String aktuellerArtikel;
    private ArrayList<Artikel> allArtikelList;
    private ArrayList<Artikel> yearArtikelList;
    private ArrayList<Artikel> monthArtikelList;

    public String getAktuellerArtikel(){
        return this.aktuellerArtikel;
    }

    /**
     * Rufe die in der Datenbank enthaltenen Artikel ab
     * Ist der String inputQuery gesetzt soll danach gefiltert werden
     */
    public void findAllArtikel()
    {
        allArtikelList = new ArrayList<>();

        if(this.getInputQuery() != null && this.getInputQuery().length() > 0)
        {
            allArtikelList = artikelService.getAllArtikel();
        }
        else
        {
            allArtikelList = artikelService.getAllArtikel();
        }
    }

    public void findAllArtikelByYear(int year)
    {
        allArtikelList = new ArrayList<>();
        allArtikelList = artikelService.getAllArtikel();
        yearArtikelList = new ArrayList<>();
        yearArtikelList.clear();
        for (Artikel artikel : allArtikelList) {
            int createdYear = artikel.getCreatedYearAsInt();
            if(createdYear == year){
                yearArtikelList.add(artikel);
            }
        }
    }

    public void findAllArtikelByMonth(int month)
    {
        monthArtikelList = new ArrayList<>();
        monthArtikelList.clear();
        if(!yearArtikelList.isEmpty()){
            for (Artikel artikel : yearArtikelList){
                int createdMonth = artikel.getCreatedMonthAsInt();
                if(createdMonth == month){
                    monthArtikelList.add(artikel);
                }
            }
        }
    }

    public ArrayList<Artikel> getAllArtikel()
    {
        return allArtikelList;
    }

    public ArrayList<Artikel> getYearArtikel() { return yearArtikelList; }

    public ArrayList<Artikel> getMonthArtikel() { return monthArtikelList; }

    @RequestMapping()
    public String index()
    {
        this.setInputQuery("");
        return view("index",this);
    }

    @RequestMapping("/suche")
    public String suche(String q)
    {
        return view("index",this);
    }

    /**
     * initialisiert die suche und leitet an die entsprechende Seite weiter
     * @param query
     * @return
     */
    public String initSuche(String query)
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        if (query == null || query == "")
        {
            return view("index",this);
        }
        else
        {
            try{
                ec.redirect(getNavigation().navigateHome("suche") + "?q=" + query);
                return "";
            } catch(Exception ex) {
                return "";
            }

        }
    }

}
