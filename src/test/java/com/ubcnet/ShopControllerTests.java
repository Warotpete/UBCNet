package com.ubcnet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubcnet.controllers.HousingController;
import com.ubcnet.controllers.ShopController;
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
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShopControllerTests {
    @InjectMocks
    private ShopController shopController;

    @Mock
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        shopController = new ShopController(objectMapper);
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(shopController, "appEnv", "local"); // Default to local
    }

    @Mock
    private Model model;


    //Test to verify whether addAttribute invocations were applied to model successfully
    @Test
    void testShopPage() {
        String viewName = shopController.shopPage(model);

        assertEquals("shop", viewName);
        verify(model).addAttribute(eq("title"), eq("Shop"));
        verify(model).addAttribute(eq("shop"), any(List.class));
    }

    //Check to ensure getShop() works as expected
    @Test
    void testGetShop() {
        List<Map<String, Object>> shop = shopController.getShop();

        assertNotNull(shop);
        assertFalse(shop.isEmpty());
    }

    //Check for ordering of shop items
    @Test
    void testGetShopSorting() {
        List<Map<String, Object>> shop = shopController.getShop();

        for (int i = 0; i < shop.size() - 1; i++) {
            int currentId = (Integer) shop.get(i).get("id");
            int nextId = (Integer) shop.get(i + 1).get("id");
            assertTrue(currentId > nextId, "Shop should be sorted in descending order by id");
        }
    }

    /*
        Tests that make sure that the housing controller works for both when running on a local environment
        and when running on the Docker environment ensuring correctness in all intended use environments of
        our controller. IMPORTANT: The relevant JSON file must contain exactly 2 items for the test to work properly.
     */
    @Test
    void testGetShopWithLocalEnvironment() throws IOException {
        List<Map<String, Object>> shop = shopController.getShop();

        assertNotNull(shop);
        assertEquals(2, shop.size());
        assertEquals(2, shop.get(0).get("id"));
        assertEquals(1, shop.get(1).get("id"));
    }

    @Test
    void testGetShopWithDockerEnvironment() throws IOException {
        // Switch to Docker environment
        ReflectionTestUtils.setField(shopController, "appEnv", "docker");

        when(objectMapper.readValue(any(File.class), eq(List.class)))
                .thenThrow(new IOException("File not found"));

        assertTrue(shopController.getShop().isEmpty());
    }
}
