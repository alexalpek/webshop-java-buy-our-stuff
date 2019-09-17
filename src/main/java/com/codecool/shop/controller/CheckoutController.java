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
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("cart", req.getParameter("cart"));
        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phone-number");
        String billingAddress = req.getParameter("billing-address");
        String shippingAddress = req.getParameter("shipping-address");

        ShippingInfo shippingInfo = new ShippingInfo(name, email, phoneNumber, billingAddress, shippingAddress);
        CartDao cartDataStore = CartDaoMem.getInstance();
        int cartId = Integer.parseInt(req.getParameter("cart"));
        Cart cart = cartDataStore.find(cartId);

        Order order = new Order(cart, shippingInfo);
    }
}
