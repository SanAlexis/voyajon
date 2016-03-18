/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nyx.voyajon.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author tchipi
 */
public class ListModel  implements Serializable{
    
    private String libelle;
    private String categorie;
    
    private List<DatatableColumnModel>  columns=new ArrayList();

    public ListModel() {
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    

    public List<DatatableColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<DatatableColumnModel> columns) {
        this.columns = columns;
    }
    
    public void addColumns(DatatableColumnModel column) {
        this.columns.add(column);
    }
    
    
    
}
