package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DaoController;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.User;
import com.codecool.shop.util.Error;
import com.codecool.shop.util.Util;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDao cartDataStore = DaoController.getCartDao();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/");
        } else {
            context.setVariable("cart", cartDataStore.find(user.getCartId()));
            engine.process("product/cart.html", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LineItemDao lineItemDao = DaoController.getLineItemDao();
        int lineItemId = Integer.parseInt(req.getParameter("line-item-id"));
        LineItem lineItem = lineItemDao.find(lineItemId);

        try {
            switch (req.getParameter("edit")) {
                case "increase":
                    lineItemDao.update(lineItem, lineItem.getQuantity() + 1);
                    break;
                case "decrease":
                    if (lineItem.getQuantity() > 1) {
                        lineItemDao.update(lineItem, lineItem.getQuantity() - 1);
                    } else {
                        lineItemDao.remove(lineItem);
                    }
                    break;
                case "clear":
                    lineItemDao.remove(lineItem);
                    break;
                default:
                    Util.handleError(resp, HttpServletResponse.SC_BAD_REQUEST, Error.INVALID_CART_OPERATION);
                    return;
            }
        } catch (DataSourceException e) {
            Util.handleError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        resp.sendRedirect("/cart");
    }
}