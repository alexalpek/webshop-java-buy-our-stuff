package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.mem.CartDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ShippingInfo;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDao cartDataStore = CartDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        //TODO change hardcoded ID
        context.setVariable("cart", cartDataStore.find(1));
        if (cartDataStore.find(1).size() > 0) {
            engine.process("product/checkout.html", context, resp.getWriter());
        } else {
            engine.process("product/cart.html", context, resp.getWriter());
        }
    }
}
