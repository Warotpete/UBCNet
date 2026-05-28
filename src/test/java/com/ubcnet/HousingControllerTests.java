package com.ubcnet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubcnet.controllers.HousingController;
import com.ubcnet.models.AnnouncementModel;
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

public class HousingControllerTests {
    @InjectMocks
    private HousingController housingController;

    @Mock
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        housingController = new HousingController(objectMapper);
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(housingController, "appEnv", "local"); // Default to local
    }

    @Mock
    private Model model;


    //Test to verify whether addAttribute invocations were applied to model successfully
    @Test
    void testHousingPage() {
        String viewName = housingController.housingPage(model);

        assertEquals("housing", viewName);
        verify(model).addAttribute(eq("title"), eq("Housing"));
        verify(model).addAttribute(eq("housing"), any(List.class));
    }


    //Check for getHousing() method
    @Test
    void testGetHousing() {
        List<Map<String, Object>> housing = housingController.getHousing();

        assertNotNull(housing);
        assertFalse(housing.isEmpty());
    }

    //Check for ordering of housing items
    @Test
    void testGetHousingSorting() {
        List<Map<String, Object>> housing = housingController.getHousing();
        for (int i = 0; i < housing.size() - 1; i++) {
            int currentId = (Integer) housing.get(i).get("id");
            int nextId = (Integer) housing.get(i + 1).get("id");
            assertTrue(currentId > nextId, "Housing should be sorted in descending order by id");
        }
    }

    /*
        Tests that make sure that the housing controller works for both when running on a local environment
        and when running on the Docker environment ensuring correctness in all intended use environments of
        our controller. IMPORTANT: The relevant JSON file must contain exactly 2 items for the test to work properly.
     */
    @Test
    void testGetHousingWithLocalEnvironment() throws IOException {
        List<Map<String, Object>> housing = housingController.getHousing();

        assertNotNull(housing);
        assertEquals(2, housing.size());
        assertEquals(2, housing.get(0).get("id"));
        assertEquals(1, housing.get(1).get("id"));
    }

    @Test
    void testGetHousingWithDockerEnvironment() throws IOException {
        // Switch to Docker environment
        ReflectionTestUtils.setField(housingController, "appEnv", "docker");

        when(objectMapper.readValue(any(File.class), eq(List.class)))
                .thenThrow(new IOException("File not found"));

        assertTrue(housingController.getHousing().isEmpty());
    }
}
