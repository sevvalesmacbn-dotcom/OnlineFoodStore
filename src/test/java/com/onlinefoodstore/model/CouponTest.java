package com.onlinefoodstore.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Coupon model sınıfı icin basit test

public class CouponTest {

    @Test
    public void testCouponConstructorWithCodeAndDiscount(){
        // Arrange & Act
        Coupon coupon = new Coupon("YENIYIL26",26.0);

        // Assert
        assertEquals("YENIYIL26", coupon.getCode());
        assertEquals(26.0, coupon.getDiscountAmount());
    }

    @Test
    public void testCouponSetters(){
        // Arrange
        Coupon coupon = new Coupon();

        // Act
        coupon.setId(1);
        coupon.setCode("INDIRIM50");
        coupon.setDiscountAmount(50.0);

        // Assert
        assertEquals(1, coupon.getId());
        assertEquals("INDIRIM50", coupon.getCode());
        assertEquals(50.0, coupon.getDiscountAmount());
    }

    @Test
    public void testCouponEmptyConstructor(){
        // Act
        Coupon coupon = new Coupon();

        // Assert
        assertNotNull(coupon);
        assertEquals(0, coupon.getId());
        assertNull(coupon.getCode());
        assertEquals(0.0, coupon.getDiscountAmount());
    }

    @Test
    public void testCouponDiscountCanBeHighValue(){
        // Arrange & Act
        Coupon coupon = new Coupon("VIP100", 100.0);

        // Assert
        assertEquals(100.0, coupon.getDiscountAmount());
    }
}
