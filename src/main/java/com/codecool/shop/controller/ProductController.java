package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Cart;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
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
import java.util.Optional;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    private Integer id = 1;
    private boolean filterByCategory = true;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = DaoController.getProductDao();
        ProductCategoryDao productCategoryDataStore = DaoController.getProductCategoryDao();
        CartDao cartDataStore = DaoController.getCartDao();
        SupplierDao supplierDataStore = DaoController.getSupplierDao();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = (User) req.getSession().getAttribute("user");
        Integer cartSize =  Optional.ofNullable(user)
                .map(User::getCartId)
                .map(id -> cartDataStore.find(id).size())
                .orElse(0);
        context.setVariable("cartSize", cartSize);
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());

        try {
            if (filterByCategory) {
                ProductCategory category = productCategoryDataStore.find(id);
                context.setVariable("filter", category);
                context.setVariable("products", productDataStore.getBy(category));
            } else {
                Supplier supplier = supplierDataStore.find(id);
                context.setVariable("filter", supplier);
                context.setVariable("products", productDataStore.getBy(supplier));
            }
            engine.process("product/index.html", context, resp.getWriter());
        } catch (DataNotFoundException e) {
            handleError(resp, HttpServletResponse.SC_BAD_REQUEST, "Wrong id");
        } catch (DataSourceException e) {
            handleError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> params = Collections.list(req.getParameterNames());

        try {
            if (params.contains("categoryId")) {
                id = Integer.parseInt(req.getParameter("categoryId"));
                filterByCategory = true;
            } else if (params.contains("supplierId")) {
                id = Integer.parseInt(req.getParameter("supplierId"));
                filterByCategory = false;
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
        } catch (NumberFormatException e) {
            handleError(resp, HttpServletResponse.SC_BAD_REQUEST, "Malformed category or supplier id.");
        }
    }

    private void handleError(HttpServletResponse resp, int statusCode, final String message) throws IOException {
        resp.setStatus(statusCode);
        resp.getWriter().println("{\"error\": \"" + message + "\"}");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
    }
}
