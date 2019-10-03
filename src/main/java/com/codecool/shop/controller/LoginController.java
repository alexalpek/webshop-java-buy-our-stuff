package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;

import com.codecool.shop.dao.DaoController;
import com.codecool.shop.dao.DataNotFoundException;
import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import com.codecool.shop.util.Util;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private boolean loginError;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("loginError", loginError);
        engine.process("product/login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = DaoController.getUserDao();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = userDao.find(username, password);
            loginError = false;
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/");
        } catch (DataNotFoundException e) {
            loginError = true;
            doGet(req, resp);
        } catch (DataSourceException e) {
            Util.handleError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
