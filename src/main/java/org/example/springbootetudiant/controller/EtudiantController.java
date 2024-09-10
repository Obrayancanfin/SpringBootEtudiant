package org.example.springbootetudiant.controller;

import org.example.springbootetudiant.model.Etudiant;
import org.example.springbootetudiant.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.swing.UIManager.get;

@Controller

public class EtudiantController {
    private final EtudiantService etudiantService;

    @Autowired
    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @RequestMapping("/")
    public String home() {
        return "Accueil";
    }

    @RequestMapping("/listeEtudiant")
    public String listEtudiant(Model model) {
        model.addAttribute("etudiants",etudiantService.getEtudiants() );
        return "ListeEtudiant";
    }

    @RequestMapping("/inscription")
    public String inscription(Model model) {
        model.addAttribute("etudiant", etudiantService.createEtudiant());
        return "Inscription";
    }

    @PostMapping("/inscription")
    public String postInscription(@ModelAttribute Etudiant etudiant, Model model) {
        Etudiant et = Etudiant.builder().id(UUID.randomUUID()).nom(etudiant.getNom()).age(etudiant.getAge()).adresse(etudiant.getAdresse()).prenom(etudiant.getPrenom()).build(); ;
        etudiantService.createEtudiant(et.getNom(),et.getPrenom(),et.getAdresse(),et.getAge());
        model.addAttribute("etudiant", etudiant);
        return listEtudiant(model);
    }

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        model.addAttribute("etudiant", etudiant);
        return "DetailEtudiant";
    }

    @RequestMapping("/search")
    public String showStudents(@RequestParam(name = "search", required = false) String search, Model model){
        if (search == null) {
            model.addAttribute("students", etudiantService.getAllStudents());
        } else {
            model.addAttribute("students", etudiantService.searchStudents(search));
        }
        return listEtudiant(model);
    }

}
