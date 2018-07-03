/**
 * Created by kHRYSTAL on 18/7/3.
 */
// 立即执行函数 引入该js文件的html具有该文件中的函数
$(function () {
    var initUrl = '/market/shopadmin/getshopinitinfo';
    var registerShopUrl = '/market/shopadmin/registershop';
    //调试信息
    alert(initUrl);
    // 初始化表单选择框
    getShopInitInfo();
    function getShopInitInfo() {
        // 请求拉取json 也可以用$.ajax
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                console.log('====>', data);
                var tempHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item, index) {
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">'
                        + item.shopCategoryName + '</option>';
                });
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });
    }

    $('#submit').click(function () {
        var shop = {};
        shop.shopName = $('#shop-name').val();
        shop.shopAddr = $('#shop-addr').val();
        shop.phone = $('#shop-phone').val();
        shop.shopDesc = $('#shop-desc').val();
        shop.shopCategory = {
            shopCategoryId: $('#shop-category').find('option').not(function () {
                return !this.selected;
            }).data('id')
        }
        shop.area = {
            areaId: $('#area').find('option').not(function () {
                return !this.selected;
            }).data('id')
        }
        var shopImg = $('#shop-img')[0].files[0];
        var formData = new FormData();
        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));
        console.log('发起请求');
        // 发起请求
        $.ajax({
            url: registerShopUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    $.toast("提交成功!");
                } else {
                    $.toast("提交失败!" + data.errMsg);
                }
            }
        });
    });
});