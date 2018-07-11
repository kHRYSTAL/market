package me.khrystal.market.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by kHRYSTAL on 18/7/3.
 */
@Controller
public class ShopAdminController {
    @RequestMapping(value = "/shopoperation", method = {RequestMethod.GET})
    public String shopOperation() {
        return "market/shopoperation";
    }

    @RequestMapping(value = "/shoplist", method = {RequestMethod.GET})
    public String shoplist() {
        return "market/shoplist";
    }

    @RequestMapping(value = "/shopmanagement", method = {RequestMethod.GET})
    public String shopManagement() {
        return "market/shopmanagement";
    }

}
