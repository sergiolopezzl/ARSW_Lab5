/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence;

/**
 * @author hcadavid
 */
public class BlueprintPersistenceException extends Exception {

    public static final String EXISTING_BLUEPRINT = "The given blueprint already exists: ";

    public BlueprintPersistenceException(String message) {
        super(message);
    }

}
