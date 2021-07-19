package com.codecool.robodog2.service.services;

import com.codecool.robodog2.dao.interfaces.PedigreeDAO;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedigreeService {

    PedigreeDAO pedigreeDAO;

    public PedigreeService(PedigreeDAO pedigreeDAO) {
        this.pedigreeDAO = pedigreeDAO;
    }

    public void addPedigree(Pedigree pedigree) {
        pedigreeDAO.addPedigree(pedigree);
    }

    public List<Pedigree> listPedigrees() {
        return pedigreeDAO.listPedigrees();
    }

    public Pedigree getPedigree(long id) {
        return pedigreeDAO.getPedigree(id);
    }

    public void updatePedigree(Pedigree pedigree, long id) {
        pedigreeDAO.updatePedigree(pedigree, id);
    }

    public void deletePedigree(long id) {
        pedigreeDAO.deletePedigree(id);
    }

    public List<Dog> getFamilyOfDog(long dog_id) {
        return pedigreeDAO.getFamilyOfDog(dog_id);
    }

    public void setDogPedigree(Pedigree pedigree, long id) {
        pedigreeDAO.setPedigree(pedigree, id);
    }

    public Dog getPuppyMom(long id) {
        return pedigreeDAO.getPuppyMom(id);
    }

    public Dog getPuppyDad(long id) {
        return pedigreeDAO.getPuppyDad(id);
    }

    public List<Dog> getDogSiblings(long id) {
        return pedigreeDAO.getDogSiblings(id);
    }

    public Dog getRandomPuppyFromParents(long dadId, long momId) {
        return pedigreeDAO.getRandomPuppyFromParents(dadId, momId);
    }
}
