/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.web.model;

/**
 *
 * @author tchipi
 */
public class DatatableColumnModel {

    private boolean bVisible;
    private int aTargets;
    private boolean bSortable;
    private String sTitle;
    private String name;
    private String data;
    private String  type;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  

    public DatatableColumnModel() {
        this.bSortable=true;
        this.bVisible=true;
        this.type=DatatableColumnType.DEFAULT.getValue();
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public boolean isbVisible() {
        return bVisible;
    }

    public void setbVisible(boolean bVisible) {
        this.bVisible = bVisible;
    }

    public int getaTargets() {
        return aTargets;
    }

    public void setaTargets(int aTargets) {
        this.aTargets = aTargets;
    }

    public boolean isbSortable() {
        return bSortable;
    }

    public void setbSortable(boolean bSortable) {
        this.bSortable = bSortable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
}
