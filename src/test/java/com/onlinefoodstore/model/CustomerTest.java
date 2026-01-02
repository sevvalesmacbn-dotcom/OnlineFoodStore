package com.onlinefoodstore.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Customer model sınıfı için basit bir test

public class CustomerTest {

    @Test
    public void testCustomerConstructorWithAllFields(){
        // Arrange & Act
        Customer customer = new Customer("Efe Yucel", "efe", 11111, "CUSTOMER", "0533 467 12 34", "Kırsehir / Merkez");

        //Assert
        assertEquals("Efe Yucel",customer.getName());
        assertEquals("efe", customer.getUsername());
        assertEquals(11111, customer.getPassword());
        assertEquals("CUSTOMER", customer.getRole());
        assertEquals("0533 467 12 34", customer.getPhone());
        assertEquals("Kırsehir / Merkez", customer.getAddress());
    }

    @Test
    public void testCustomerInheritsFromUser(){
        // Arrange & Act
        Customer customer = new Customer();

        // Assert
        assertTrue(customer instanceof User);
    }

    @Test
    public void testCustomerSetters(){
        // Arrange
        Customer customer = new Customer();

        // Act
        customer.setId(1);
        customer.setName("Sude Dogan");
        customer.setUsername("sude");
        customer.setPassword(22222);
        customer.setRole("CUSTOMER");
        customer.setPhone("0555 555 55 55");
        customer.setAddress("İstanbul / Fatih");

        // Assert
        assertEquals(1, customer.getId());
        assertEquals("Sude Dogan", customer.getName());
        assertEquals("sude", customer.getUsername());
        assertEquals(22222, customer.getPassword());
        assertEquals("CUSTOMER", customer.getRole());
        assertEquals("0555 555 55 55", customer.getPhone());
        assertEquals("İstanbul / Fatih", customer.getAddress());
    }

    @Test
    public void testCustomerEmptyConstructor(){
        // Act
        Customer customer = new Customer();

        // Assert
        assertNotNull(customer);
        assertNull(customer.getPhone());
        assertNull(customer.getAddress());
    }

    @Test
    public void testCustomerPhoneAndAddressCanBeNull(){
        // Arrange
        Customer customer = new Customer();

        // Act
        customer.setName("Test Customer");
        customer.setUsername("test");

        // Assert
        assertNull(customer.getPhone());
        assertNull(customer.getAddress());
    }
}
