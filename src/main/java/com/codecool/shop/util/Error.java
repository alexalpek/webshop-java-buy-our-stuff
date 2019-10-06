package com.codecool.shop.util;

public interface Error {

    String DATABASE_IS_UNREACHABLE = "Database is unreachable";

    String NO_PRODUCT_ID = "Product object received no id";
    String NO_CATEGORY_ID = "ProductCategory object received no id";
    String NO_SUPPLIER_ID = "Supplier object received no id";
    String NO_CART_ID = "Cart object received no id";
    String NO_LINE_ITEM_ID = "LineItem object received no id";

    String NO_SUCH_PRODUCT = "No such product";
    String NO_SUCH_CATEGORY = "No such product-category";
    String NO_SUCH_SUPPLIER = "No such supplier";
    String NO_SUCH_CART = "No such Cart";
    String NO_SUCH_LINE_ITEM = "No such line-item";
    String NO_SUCH_USER = "No such user";

    String MALFORMED_FILTER_ID = "Malformed category or supplier id";
    String MALFORMED_CART_ID = "Malformed cart id";
    String MALFORMED_STATUS_CODE = "Malformed status code";

    String INVALID_CART_OPERATION = "Invalid cart operation";

    String CLASS_NOT_FOUND = "Class not found";

    String COULD_NOT_READ_FIELD = "Couldn't read field";
}
