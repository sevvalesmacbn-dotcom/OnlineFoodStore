package com.onlinefoodstore.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
 //User model sınıfı icin basit bir test
public class UserTest {
    @Test
     public void testUserConstructorWithAllFields(){
        // Arrange & Act
        User user = new User("Fatih Terim", "fatih", 12345, "CUSTOMER");

        //Assert
        assertEquals("Fatih Terim", user.getName());
        assertEquals("fatih",user.getUsername());
        assertEquals(12345, user.getPassword());
        assertEquals("CUSTOMER", user.getRole());
    }

    @Test
     public void testUserSetters(){
        //Arrange
        User user = new User();

        //Act
        user.setId(1);
        user.setName("Mehmet Demir");
        user.setUsername("mehmet");
        user.setPassword(54321);
        user.setRole("ADMIN");

        //Assert
        assertEquals(1, user.getId());
        assertEquals("Mehmet Demir", user.getName());
        assertEquals("mehmet", user.getUsername());
        assertEquals(54321, user.getPassword());
        assertEquals("ADMIN", user.getRole());
    }

    @Test
     public void testUserEmptyConstructor(){
        //Act
        User user = new User();

        //Assert
        assertNotNull(user);
        assertEquals(0, user.getId());
        assertNull(user.getName());
    }

    @Test
     public void testUserRoleCanBeCustomer(){
        // Arrange & Act
        User user = new User("Test User", "test", 999 , "CUSTOMER");

        //Assert
        assertEquals("CUSTOMER", user.getRole());
    }
}
