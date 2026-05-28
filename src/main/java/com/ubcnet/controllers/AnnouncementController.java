package com.ubcnet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The AnnouncementController Class is an immutable datatype used to get data from the announcements.json file
 *
 * Representation Invariant: The AnnouncementController Class is represented by a List of Maps,
 *                           where each Map represents an individual post. Each Map is
 *                           mapping a String (the name of the input) to an Object (the
 *                           actual post input).
 */
// Marking the class as a web controller (returns a response to the frontend)
@Controller
public class AnnouncementController {

    // Initialize the announcements list
    private List<Map<String, Object>> announcements;

    // Initialize the object mapper
    private final ObjectMapper objectMapper;

    // Get the local environment
    @Value("${app.env:local}")
    private String appEnv;

    /**
     * Constructor
     *
     * @param objectMapper
     */
    public AnnouncementController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     *  Handles the GET requests for the "/announcements page"
     *
     * @param model The Model Object used to pass data to the frontend
     * @return The name of the view template to be rendered, in this case, "announcements", which is the announcements page.
     */
    @GetMapping("/announcements")
    public String announcementsPage(Model model) {
        model.addAttribute("title", "Announcements");
        model.addAttribute("announcements", getAnnouncements());
        return "announcements";
    }

    /**
     * Announcement getter that calls the loadAnnouncements method
     *
     * @return the list of announcements, or an empty list if the
     *         loadAnnouncements method is not working
     */
    public List<Map<String, Object>> getAnnouncements() {
        String filePath = determineFilePath(appEnv);
        try {
            return loadAnnouncements(filePath);
        } catch (IOException e) {
            return List.of();
        }
    }

    /**
     * Determines the file path to the json database, based on whether we are running
     * the website using Docker or the local computer
     *
     * @param environment the environment of where the application is used
     * @return the path to the json database
     */
    private String determineFilePath(String environment) {
        if ("docker".equalsIgnoreCase(environment)) {
            return "/app/data/announcements.json";
        }
        return "src/main/resources/data/announcements.json";
    }

    /**
     * Receiving the data from the json database and storing it inside the announcements list
     * initialized inside the AnnouncementController class
     *
     * @param filePath the path to the json database
     * @return announcements, the list of announcements
     * @throws IOException if the path is not found or the database is not readable
     */
    private List<Map<String, Object>> loadAnnouncements(String filePath) throws IOException {
        File jsonFile = new File(filePath);
        announcements = objectMapper.readValue(jsonFile, List.class);
        // Lambda and Streams yay ;)
        announcements.sort((a, b) -> ((Integer) b.get("id")).compareTo((Integer) a.get("id")));
        return announcements;
    }
}
