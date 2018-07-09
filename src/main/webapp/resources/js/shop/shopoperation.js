/**
 * Created by kHRYSTAL on 18/7/3.
 */
// 立即执行函数 引入该js文件的html具有该文件中的函数
$(function () {
    var shopId = getQueryString('shopId'); // 从url中根据key 获取value
    console.log('shopId:', shopId);
    var isEdit = shopId ? true : false; // 如果存在shopId 说明是编辑页面, 需要传入商铺信息且部分字段不可编辑
    var initUrl = '/market/shopadmin/getshopinitinfo';
    var registerShopUrl = '/market/shopadmin/registershop';
    var shopInfoUrl = '/market/shopadmin/getshopid?shopId=' + shopId;
    var editShopUrl = '/market/shopadmin/modifyshop';
    if (!isEdit) {
        // 初始化表单选择框
        getShopInitInfo();
    } else {
        getShopInfo(shopId);
    }
    // 通过shopId获取shop 并填充页面
    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function (data) {
            console.log(data);
            if (data.success) {
                var shop = data.shop;
                $('#shop-name').val(shop.shopName);
                $('#shop-addr').val(shop.shopAddr);
                $('#shop-phone').val(shop.phone);
                $('#shop-desc').val(shop.shopDesc);
                var shopCategory = '<option data-id="' + shop.shopCategory.shopCategoryId + '" selected>'
                    + shop.shopCategory.shopCategoryName + '</option>';
                var tempAreaHtml = '';
                data.areaList.map(function(item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
                });
                $('#shop-category').html(shopCategory);
                $('#shop-category').attr('disabled', 'disabled');
                $('#area').html(tempAreaHtml);
                $('#area').attr('data-id', shop.areaId);
            }
        });
    }

    function getShopInitInfo() {
        // 请求拉取json 也可以用$.ajax
        $.getJSON(initUrl, function (data) {
            if (data.success) {
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
        if (isEdit) {
            shop.shopId = shopId;
        }
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
        var verifyCodeActual = $('#j_captcha').val();
        if (!verifyCodeActual) {
            $.toast('请输入验证码!');
            return;
        }
        formData.append('verifyCodeActual', verifyCodeActual);
        console.log('发起请求', formData);
        // 发起请求
        $.ajax({
            url: isEdit ? editShopUrl: registerShopUrl,
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
                $('#captcha_img').click();
            }
        });
    });
});