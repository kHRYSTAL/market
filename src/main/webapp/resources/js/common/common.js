/**
 * Created by kHRYSTAL on 18/7/4.
 */
function changeVerifyCode(img) {
    img.src = "/market/Kaptcha?" + Math.floor(Math.random() * 100);
}