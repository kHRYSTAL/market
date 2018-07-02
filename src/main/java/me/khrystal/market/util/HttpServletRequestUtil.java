package me.khrystal.market.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kHRYSTAL on 18/7/2.
 */
public class HttpServletRequestUtil {
    public static int getInt(HttpServletRequest request, String key) {
        try {
            // 转换为整型
            return Integer.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request, String key) {
        try {
            return Long.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return -1L;
        }
    }

    public static double getDouble(HttpServletRequest request, String key) {
        try {
            return Double.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return -1d;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key) {
        try {
            return Boolean.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            if (result != null) {
                result = result.trim();
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
