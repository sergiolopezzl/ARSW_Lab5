/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import java.util.Set;

/**
 * @author hcadavid
 */
public interface BlueprintsPersistence {

    void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;

    Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException;

    Set<Blueprint> getBlueprintByAuthor(String author) throws BlueprintNotFoundException;

    Set<Blueprint> getAllBlueprints();

    void updateBlueprint(String author, String bpname, Blueprint bpUpdate) throws BlueprintNotFoundException;

}
