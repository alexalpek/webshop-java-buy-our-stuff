package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Cart;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    private Integer categoryId = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = DaoController.getProductDao();
        ProductCategoryDao productCategoryDataStore = DaoController.getProductCategoryDao();
        CartDao cartDataStore = DaoController.getCartDao();
        SupplierDao supplierDataStore = DaoController.getSupplierDao();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = (User) req.getSession().getAttribute("user");

        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());

        ProductCategory category = productCategoryDataStore.find(categoryId);
        categoryId = 1;

        context.setVariable("category", category);
        context.setVariable("products", productDataStore.getBy(category));
        if (user != null) {
            int cartId = user.getCartId();
            context.setVariable("cartSize", cartDataStore.find(cartId).size());
        } else {
            context.setVariable("cartSize", 0);
        }
        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> params = Collections.list(req.getParameterNames());

        if (params.contains("categoryId")) {
            categoryId = Integer.parseInt(req.getParameter("categoryId"));
        } else {
            CartDao cartDataStore = DaoController.getCartDao();
            ProductDao productDataStore = DaoController.getProductDao();

            int productId = Integer.parseInt(req.getParameter("product"));
            User user = (User) req.getSession().getAttribute("user");
            int cartId = user.getCartId();

            Cart cart = cartDataStore.find(cartId);
            cart.add(productDataStore.find(productId));
        }
        doGet(req, resp);
    }
}
