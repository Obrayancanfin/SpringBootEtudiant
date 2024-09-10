package org.example.springbootetudiant.service;

import org.example.springbootetudiant.model.Etudiant;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EtudiantService {

    private Map< UUID ,Etudiant> etudiants= new HashMap<>();

    public Etudiant createEtudiant(String nom, String prenom, String adresse, int age) {
        Etudiant et = Etudiant.builder()
                .id(UUID.randomUUID())
                .age(age)
                .nom(nom)
                .prenom(prenom)
                .adresse(adresse).build();
        etudiants.put(et.getId(), et);
        return et;
    }

    public Etudiant createEtudiant(){
        return new Etudiant();
    }

    public Etudiant getEtudiantById(UUID id){
        return etudiants.get(id);
    }

    public List<Etudiant> getAllStudents() {
        return new ArrayList<>(etudiants.values());
    }

    public List<Etudiant> searchStudents(String search) {
        return etudiants.values().stream().filter(etudiant -> etudiant.getNom().toLowerCase().contains(search.toLowerCase())).toList();
    }
}