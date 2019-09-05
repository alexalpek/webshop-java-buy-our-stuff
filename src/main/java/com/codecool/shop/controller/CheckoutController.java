package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.order.Cart;
import com.codecool.shop.order.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("cart", req.getParameter("cart"));
        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> orderInfo = new ArrayList<>();

        Enumeration<String> params = req.getParameterNames();
        while(params.hasMoreElements()) {
            String paramName = params.nextElement();
            if (!paramName.equals("cart")) {
                orderInfo.add(req.getParameter(paramName));
            }
        }

        for (String info : orderInfo) {
            if (info.equals("")) {
                doGet(req, resp);
                return;
            }
        }

        CartDao cartDataStore = CartDaoMem.getInstance();
        int cartId = Integer.parseInt(req.getParameter("cart"));
        Cart cart = cartDataStore.find(cartId);

        Order order = new Order(cart, orderInfo);
    }
}
