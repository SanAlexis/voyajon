/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.web.model;

/**
 *
 * @author eisti
 */
public enum DatatableColumnType {

    DATE("date"),
    NUM("num"),
    NUM_FMT("num-fmt"),
    DEFAULT("string");
    private String value;

    DatatableColumnType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
