package com.codecool.shop.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Util {

    public static boolean isJUnitTest() {
        StackTraceElement[] list = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : list) {
            if (element.getClassName().startsWith("org.junit.")) {
                return true;
            }
        }
        return false;
    }

    public static void handleError(HttpServletRequest req, HttpServletResponse resp, int statusCode, final String message) throws IOException {
        req.getSession().setAttribute("errorMessage", message);
        req.getSession().setAttribute("statusCode", statusCode);
        resp.sendRedirect("/error");
    }
}
