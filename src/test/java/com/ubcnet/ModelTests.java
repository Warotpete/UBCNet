package com.ubcnet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubcnet.controllers.HousingController;
import com.ubcnet.controllers.ShopController;
import com.ubcnet.models.AnnouncementModel;
import com.ubcnet.models.HousingModel;
import com.ubcnet.models.ShopModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ModelTests {

    /*
     * Tests the correctness and functionality of every single method that Announcement model has
     */

    @Test
    void testAnnouncementModel(){
        AnnouncementModel test1 = new AnnouncementModel(10, "Okay", "What happend?");

        assertEquals(10, test1.getId());
        test1.setId(15);
        assertEquals(15, test1.getId());

        assertEquals("Okay", test1.getTitle());
        test1.setTitle("Why");
        assertEquals("Why", test1.getTitle());

        assertEquals("What happend?", test1.getMessage());
        test1.setMessage("ABC");
        assertEquals("ABC", test1.getMessage());

        assertTrue(test1.equals(new AnnouncementModel(15, "Why", "ABC")));



        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        test1.setCreated(currentTime);
        assertEquals(currentTime.format(formatter), test1.getCreated());
    }

    /*
     * Tests the correctness and functionality of every single method that Housing Model has
     */
    @Test
    void testHousingModel(){
        HousingModel test1 = new HousingModel(12,"Title","photo.com","Housing Description",5.4,"Some Location","email@gmail.com");

        assertEquals(12, test1.getId());
        test1.setId(15);
        assertEquals(15, test1.getId());

        assertEquals("Title", test1.getTitle());
        test1.setTitle("Why");
        assertEquals("Why", test1.getTitle());

        assertEquals("photo.com",test1.getImage());
        test1.setImage("newphoto.com");
        assertEquals("newphoto.com",test1.getImage());

        assertEquals("Housing Description", test1.getDescription());
        test1.setDescription("ABC");
        assertEquals("ABC", test1.getDescription());

        assertEquals(5.4,test1.getRentPerMonth());
        test1.setRentPerMonth(2.7);
        assertEquals(2.7,test1.getRentPerMonth());

        assertEquals("Some Location",test1.getLocation());
        test1.setLocation("Another Location");
        assertEquals("Another Location",test1.getLocation());

        assertEquals("email@gmail.com",test1.getEmail());
        test1.setEmail("anotheremail@gmail.com");
        assertEquals("anotheremail@gmail.com",test1.getEmail());

        assertTrue(test1.equals(new HousingModel(15, "Why", "newphoto.com","ABC",2.7,"Another Location","anotheremail@gmail.com")));



        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        test1.setCreated(currentTime);
        assertEquals(currentTime.format(formatter), test1.getCreated());
    }

    /*
     * Tests the correctness and functionality of every method that ShopModel has
     */
    @Test
    void testShopModel(){
        ShopModel test1 = new ShopModel(8,"Title","photo.com","Shop Description",50,"Condition","email@gmail.com");

        assertEquals(8, test1.getId());
        test1.setId(15);
        assertEquals(15, test1.getId());

        assertEquals("Title", test1.getTitle());
        test1.setTitle("Why");
        assertEquals("Why", test1.getTitle());

        assertEquals("photo.com",test1.getImage());
        test1.setImage("newphoto.com");
        assertEquals("newphoto.com",test1.getImage());

        assertEquals("Shop Description", test1.getDescription());
        test1.setDescription("ABC");
        assertEquals("ABC", test1.getDescription());

        assertEquals(50,test1.getPrice());
        test1.setPrice(2.7);
        assertEquals(2.7,test1.getPrice());

        assertEquals("Condition",test1.getCondition());
        test1.setCondition("Another Condition");
        assertEquals("Another Condition",test1.getCondition());

        assertEquals("email@gmail.com",test1.getEmail());
        test1.setEmail("anotheremail@gmail.com");
        assertEquals("anotheremail@gmail.com",test1.getEmail());

        assertTrue(test1.equals(new ShopModel(15,"Why", "newphoto.com","ABC",2.7,"Another Condition","anotheremail@gmail.com")));



        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        test1.setCreated(currentTime);
        assertEquals(currentTime.format(formatter), test1.getCreated());
    }

}
