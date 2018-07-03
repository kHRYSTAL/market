package me.khrystal.market.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kHRYSTAL on 18/7/3.
 */
@Controller

public class ShopAdminController {
    @RequestMapping(value = "/shopoperation", method = {RequestMethod.GET})
    public String shopOperation() {
        return "market/shopoperation";
    }
}
