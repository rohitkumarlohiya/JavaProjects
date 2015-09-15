package com.estel.utility;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Manjusha
 * Date: 20 Dec, 2010
 * Time: 6:05:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Pagination {
    public static Integer getPAGESIZE() {
        return PAGESIZE;
    }

    private static Integer PAGESIZE = 10;

    public static List getSubList(List mainList, HttpServletRequest httpServletRequest) throws Exception {
        List sub_List = null;
        int count = 0;
        int pageSize = PAGESIZE;
        int pageDisplay = 1;
        int totalPages = 0;
        int startIndex = 0;


        count = mainList.size();

        if (count < pageSize) {
            pageSize = count;

        }

        if (httpServletRequest.getParameter("strstartIndex") != null && Integer.parseInt(httpServletRequest.getParameter("strstartIndex").toString()) >= 0)
            startIndex = Integer.parseInt(httpServletRequest.getParameter("strstartIndex").toString());


        if (httpServletRequest.getParameter("strPagesize") != null) {

            pageSize = Integer.parseInt(httpServletRequest.getParameter("strPagesize").toString());
            if (pageSize >= count) {

                pageSize = count;
            }

        }
        if (httpServletRequest.getParameter("paginationVal") != null && httpServletRequest.getParameter("paginationVal").equalsIgnoreCase("")) {
            if (httpServletRequest.getAttribute("listSize") != null) {

                pageSize = Integer.parseInt(httpServletRequest.getAttribute("listSize").toString());
                if (pageSize > count) {

                    pageSize = count;
                } else {
                    if (pageSize > 10) {

                        pageSize = 10;
                    }
                }
            }
        } else {

        }


        try {
            if (count % pageSize > 0)
                totalPages = (count / pageSize) + 1;
            else
                totalPages = count / pageSize;
        } catch (Exception e) {
            System.out.println("pagination.jsp:Inside NumberFormatException");
        }

        if (httpServletRequest.getParameter("strPageNum") != null) {
            pageDisplay = Integer.parseInt(httpServletRequest.getParameter("strPageNum").toString());
            if (pageDisplay == 1)
                startIndex = 0;
            else {
                if (pageDisplay > totalPages) {
                    startIndex = 0;
                    pageDisplay = 1;
                } else
                    startIndex = (pageDisplay - 1) * pageSize;
            }
        } else if (startIndex == 0)
            pageDisplay = 1;
        else {
            pageDisplay = (startIndex / pageSize) + 1;

        }

        if (startIndex == 0) {
            if (count >= pageSize)
                sub_List = mainList.subList(0, pageSize);
            else
                sub_List = mainList.subList(0, count);
        } else {
            if (startIndex != count) {
                if (startIndex + pageSize > count)
                    sub_List = mainList.subList(startIndex, count);
                else
                    sub_List = mainList.subList(startIndex, startIndex + pageSize);
            } else
                sub_List = mainList.subList(startIndex, startIndex + pageSize);
        }

        httpServletRequest.setAttribute("strstartIndex", startIndex);
        httpServletRequest.setAttribute("strPagesize", pageSize);
        httpServletRequest.setAttribute("strPageNum", pageDisplay);
        httpServletRequest.setAttribute("count", mainList.size());

        return sub_List;

    }
}
