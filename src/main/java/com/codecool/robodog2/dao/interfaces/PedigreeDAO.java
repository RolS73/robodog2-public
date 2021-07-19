package com.codecool.robodog2.dao.interfaces;

import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;

import java.util.List;

public interface PedigreeDAO {

    void addPedigree(Pedigree pedigree);

    List<Pedigree> listPedigrees();

    Pedigree getPedigree(long id);

    void updatePedigree(Pedigree pedigree, long id);

    void deletePedigree(long id);

    List<Dog> getFamilyOfDog(long dog_id);

    void setPedigree(Pedigree pedigree, long id);

    Dog getPuppyMom(long id);

    Dog getPuppyDad(long id);

    List<Dog> getDogSiblings(long id);

    Dog getRandomPuppyFromParents(long dadId, long momId);
}
