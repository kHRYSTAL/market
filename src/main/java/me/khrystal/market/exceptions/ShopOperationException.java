package me.khrystal.market.exceptions;

/**
 * Created by kHRYSTAL on 18/7/2.
 * 商城操作异常封装
 */
public class ShopOperationException extends RuntimeException {

    private static final long serialVersionUID = 998519666216235091L;

    public ShopOperationException(String message) {
        super(message);
    }
}
