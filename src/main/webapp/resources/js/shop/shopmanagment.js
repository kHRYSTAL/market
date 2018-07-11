/**
 * Created by kHRYSTAL on 18/7/11.
 */
$(function () {
    var shopId = getQueryString('shopId');
    var shopInfoUrl = '/market/shopadmin/getshopmanagementinfo?shopId=' + shopId;
    $.getJSON(shopInfoUrl, function (data) {
        if (data.redirect) {
            window.location.href = data.url;
        } else {
            if (data.shopId != undefined && data.shopId != null) {
                shopId = data.shopId;
            }
            $('#shopInfo')
                .attr('href', '/market/shopoperation?shopId=' + shopId);
        }

    })
})
