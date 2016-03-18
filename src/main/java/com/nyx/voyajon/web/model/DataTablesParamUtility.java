package com.nyx.voyajon.web.model;

import javax.servlet.http.HttpServletRequest;

public class DataTablesParamUtility {

    public static JQueryDataTableParamModel getParam(HttpServletRequest request) {
        if (request.getParameter("draw") != null && !"".equals(request.getParameter("draw"))) {
            JQueryDataTableParamModel param = new JQueryDataTableParamModel();
            param.sEcho = request.getParameter("draw");
			//param.sSearch = request.getParameter("sSearch");
            //param.sColumns = request.getParameter("sColumns");
            param.iDisplayStart = Integer.parseInt(request.getParameter("start"));
            param.iDisplayLength = Integer.parseInt(request.getParameter("length"));
			//param.iColumns = Integer.parseInt( request.getParameter("iColumns") );
            //param.iSortingCols = Integer.parseInt( request.getParameter("iSortingCols") );
            param.iSortColumnIndex = Integer.parseInt(request.getParameter("order[0][column]"));
            param.sSortDirection = request.getParameter("order[0][dir]");
            return param;
        } else {
            return null;
        }
    }
}
