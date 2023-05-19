package com.example.be.common;

public class Status {
    //用户信息 0 禁用 1 可使用
    public static final int BANNED = 0;

    public static final int RUNNING = 1;

    //广告审核状态 12 审核中 22 审核未通过 32 审核通过
    public static final int EXAMING = 12;

    public static final int NOT_PASS = 22;

    public static final int PASS = 32;

    //广告支付状态 322 未支付 222 支付成功
    public static final int NOT_PAID = 322;

    public static final int PAID = 222;

    //广告投放状态 2222 投放中 3222 投放结束 1222 强制终止投放
    public static final int ON = 2222;

    public static final int OFF = 3222;

    public static final int STOP = 1222;


}
