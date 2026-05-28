package com.ubcnet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubcnet.controllers.AnnouncementController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnnouncementControllerTest {

    @InjectMocks
    private AnnouncementController announcementController;

    @Mock
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        announcementController = new AnnouncementController(objectMapper);
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(announcementController, "appEnv", "local"); // Default to local
    }

    @Mock
    private Model model;

    /*
    * Test if the announcement Controller create a right String that will be passed to the Frontend,
    * so the fronted can display the right thing from database
    * */
    @Test
    void testAnnouncementsPage() {
        String viewName = announcementController.announcementsPage(model);

        assertEquals("announcements", viewName);
        verify(model).addAttribute(eq("title"), eq("Announcements"));
        verify(model).addAttribute(eq("announcements"), any(List.class));
    }

    /*
     * Test if the announcement controller can retrieve the data from JSON database and
     * pass it as a list
     */
    @Test
    void testGetAnnouncements() {
        List<Map<String, Object>> announcements = announcementController.getAnnouncements();

        assertNotNull(announcements);
        assertFalse(announcements.isEmpty());
    }

    /*
     * Test if the data is retrieved by the announcement controller is properly sorted by the id
     */
    @Test
    void testGetAnnouncementsSorting() {
        List<Map<String, Object>> announcements = announcementController.getAnnouncements();

        for (int i = 0; i < announcements.size() - 1; i++) {
            int currentId = (Integer) announcements.get(i).get("id");
            int nextId = (Integer) announcements.get(i + 1).get("id");
            assertTrue(currentId > nextId, "Announcements should be sorted in descending order by id");
        }
    }

    /*
     * From default the database should contain 2 item each for announcement, so we test if it has that
     * before build the system locally
     */
    @Test
    void testGetAnnouncementsWithLocalEnvironment() throws IOException {
        List<Map<String, Object>> announcements = announcementController.getAnnouncements();

        //from default we have 2 in the server
        assertNotNull(announcements);
        assertEquals(2, announcements.size());
        assertEquals(2, announcements.get(0).get("id"));
        assertEquals(1, announcements.get(1).get("id"));
    }

    /*
     * If we run this code locally it should not get the docker environment
     * it should return empty set
     */
    @Test
    void testGetAnnouncementsWithDockerEnvironment() throws IOException {
        // Switch to Docker environment
        ReflectionTestUtils.setField(announcementController, "appEnv", "docker");

        when(objectMapper.readValue(any(File.class), eq(List.class)))
                .thenThrow(new IOException("File not found"));

        assertTrue(announcementController.getAnnouncements().isEmpty());
    }


}