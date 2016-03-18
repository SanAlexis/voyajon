/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.web.controller;

import com.nyx.voyajon.web.model.DataTablesParamUtility;
import com.nyx.voyajon.web.model.JQueryDataTableParamModel;
import java.lang.reflect.Field;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tchipi
 */
@RestController
@RequestMapping("/app")
public class DatatablesController {

  

    @RequestMapping(value = "/datatables/{categorie}", method = RequestMethod.GET)
    public JSONObject serverside(
            @PathVariable String categorie, HttpServletRequest request, HttpSession session) throws Exception {

        final List<Field> listfieldscolumns = null;

        JQueryDataTableParamModel param = DataTablesParamUtility.getParam(request);
        List listresult = null;

        int totalrecords = listresult.size();

        final int sortColumnIndex = param.iSortColumnIndex;
        final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;

        // code permettant le tri de la liste
//        Collections.sort(listresult, new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                try {
//                    Field ff = listfieldscolumns.get(sortColumnIndex != 0 ? sortColumnIndex - 1 : sortColumnIndex);
//                    Object co1 = objectUtils.getFieldValue(ff, o1);
//                    if (co1 == null) {
//                        return -1;
//                    }
//                    Object co2 = objectUtils.getFieldValue(ff, o2);
//                    if (co2 == null) {
//                        return 1;
//                    }
//
//                    int value = ((Comparable) co1).compareTo(co2);
//                    return value * sortDirection;
//
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    return 0;
//                }
//
//            }
//        });

        //limitation des resultats de la requete  pour affichage
        if (listresult.size() < param.iDisplayStart + param.iDisplayLength) {
            listresult = listresult.subList(param.iDisplayStart, listresult.size());
        } else {
            listresult = listresult.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
        }

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("draw", param.sEcho);
        jsonResponse.put("recordsTotal", totalrecords);
        jsonResponse.put("recordsFiltered", totalrecords);
        jsonResponse.put("data", getDatatableList(listresult, categorie, param.iDisplayStart, request));

        return jsonResponse;

    }

    @RequestMapping(value = "/datatables1/{categorie}", method = RequestMethod.GET)
    public JSONArray ajaxsource(@PathVariable String categorie,
            HttpServletRequest request, HttpSession session) throws Exception {

        List listresult = null;
        return getDatatableList(listresult, categorie, 0, request);
    }

    public JSONArray getDatatableList(List list, String categorie, int displaystart, HttpServletRequest req) throws Exception {
        JSONArray array = new JSONArray();
       
        return array;
    }

}
