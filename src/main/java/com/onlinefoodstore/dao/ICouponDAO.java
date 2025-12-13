package com.onlinefoodstore.dao;
import com.onlinefoodstore.model.Coupon;
public interface ICouponDAO {
    Coupon getCouponByCode(String code);
    boolean addCoupon(Coupon coupon);
    boolean deleteCoupon(int id);

}
