package com.codecool.shop.util;

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


    public static void handleError(HttpServletResponse resp, int statusCode, final String message) throws IOException {
        resp.setStatus(statusCode);
        resp.getWriter().println("{\"error\": \"" + message + "\"}");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
    }
}
