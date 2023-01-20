package ua.com.vp.confapp.utils;


import jakarta.servlet.http.HttpServletRequest;

public class PaginatorUtil {

    public static void paginate(int totalRecords, HttpServletRequest request) {
        int records = getInt(request.getParameter("records"), 1, 5);
        int currentPage = getInt(request.getParameter("page"), 1, 1);
        int noOfPages = (int) Math.ceil((double) totalRecords / records);
        setAttributes(request, records, noOfPages, currentPage);
    }

    private static void setAttributes(HttpServletRequest request, int records, int noOfPages, int currentPage) {
        request.setAttribute("records", records);
        request.setAttribute("page", currentPage);
        request.setAttribute("noOfPages", noOfPages);
    }

    private static int getInt(String value, int min, int defaultValue) {
        try {
            int records = Integer.parseInt(value);
            if (records >= min) {
                return records;
            }
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
            return defaultValue;
        }
        return defaultValue;
    }

    private PaginatorUtil() {}
}