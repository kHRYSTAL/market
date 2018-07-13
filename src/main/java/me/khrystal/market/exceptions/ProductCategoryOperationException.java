package me.khrystal.market.exceptions;

/**
 * Created by kHRYSTAL on 18/7/13.
 */
public class ProductCategoryOperationException extends RuntimeException {

    private static final long serialVersionUID = -8284898556865071595L;

    public ProductCategoryOperationException(String msg) {
        super(msg);
    }
}
