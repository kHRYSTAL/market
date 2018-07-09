/**
 * Created by kHRYSTAL on 18/7/4.
 */
function changeVerifyCode(img) {
    img.src = "/market/Kaptcha?" + Math.floor(Math.random() * 100);
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return '';
}