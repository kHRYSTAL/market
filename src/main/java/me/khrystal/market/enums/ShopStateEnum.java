package me.khrystal.market.enums;

/**
 * Created by kHRYSTAL on 18/7/2.
 */
public enum ShopStateEnum {
    CHECK(0, "审核中"), OFFLINE(-1, "非法店铺"), SUCCESS(1, "操作成功"),
    PASS(2, "通过认证"), INNER_ERROR(-1001, "内部系统错误"), NULL_SHOP_ID(-1002, "ShopId为空"),
    NULL_SHOP(-1003, "shop信息为空");

    private int state;
    private String stateInfo;

    // 不希望程序外部改变Enum值 在程序中 默认当作常量使用
    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 依据传入的state返回相应的enum值
     */
    public static ShopStateEnum stateOf(int state) {
        for (ShopStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
