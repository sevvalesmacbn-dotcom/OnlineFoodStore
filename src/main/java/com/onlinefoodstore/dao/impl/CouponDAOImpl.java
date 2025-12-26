package com.onlinefoodstore.dao.impl;
import com.onlinefoodstore.dao.ICouponDAO;
import com.onlinefoodstore.model.Coupon;
import java.sql.*;
public class CouponDAOImpl implements ICouponDAO{
    private Connection connection;
    public CouponDAOImpl(Connection connection){this.connection=connection;}
    @Override
    public Coupon getCouponByCode(String code) {
        String sql= "SELECT * FROM coupons WHERE code = ?";
        try(PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setString(1,code);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Coupon coupon= new Coupon();
                coupon.setId(rs.getInt("id"));
                coupon.setCode(rs.getString("code"));
                coupon.setDiscountAmount(rs.getDouble("discount_amount"));
                return coupon;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addCoupon(Coupon coupon) {
        String sql="INSERT INTO coupon (code, discount_amount) VALUES (?, ?)";
        try (PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setString(1,coupon.getCode());
            ps.setDouble(2,coupon.getDiscountAmount());
            return ps.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCoupon(int id) {
        String sql="DELETE FROM coupons WHERE id = ?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1,id);
            return ps.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
