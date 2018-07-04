package me.khrystal.market.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kHRYSTAL on 18/7/4.
 */
public class CodeUtil {
    // 判断验证码是否正确
    public static boolean checkVerifyCode(HttpServletRequest request) {
        // 从Session中获取期望得到的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 获取用户输入的验证码
        String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
        if (verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)) {
            return false;
        }
        return true;
    }
}
