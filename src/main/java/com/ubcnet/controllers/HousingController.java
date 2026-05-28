package com.ubcnet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * The HousingController Class is an immutable datatype used to get data from the housing.json file
 *
 * Representation Invariant: The HousingController Class is represented by a List of Maps,
 *                           where each Map represents an individual post. Each Map is
 *                           mapping a String (the name of the input) to an Object (the
 *                           actual post input).
 */
// Marking the class as a web controller (returns a response to the frontend)
@Controller
public class HousingController {

    // Initialize the housing list
    private List<Map<String, Object>> housing;

    // Get the local environment
    @Value("${app.env:local}")
    private String appEnv;

    // Initialize the object mapper
    private final ObjectMapper objectMapper;

    /**
     * Constructor
     *
     * @param objectMapper
     */
    public HousingController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     *  Handles the GET requests for the "/housing page"
     *
     * @param model The Model Object used to pass data to the frontend
     * @return The name of the view template to be rendered, in this case, "housing", which is the housing page.
     */
    @GetMapping("/housing")
    public String housingPage(Model model) {
        model.addAttribute("title", "Housing");
        model.addAttribute("housing", getHousing());
        return "housing";
    }

    /**
     * Housing getter that calls the loadHousing method
     *
     * @return the list of housing, or an empty list if the
     *         loadHousing method is not working
     */
    public List<Map<String, Object>> getHousing(){
        String filePath = determineFilePath(appEnv);
        try {
            return loadHousing(filePath);
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
            return "/app/data/housing.json";
        }
        return "src/main/resources/data/housing.json";
    }

    /**
     * Receiving the data from the json database and storing it inside the housing list
     * initialized inside the HousingController class
     *
     * @param filePath the path to the json database
     * @return housing, the list of housing
     * @throws IOException if the path is not found or the database is not readable
     */
    private List<Map<String, Object>> loadHousing(String filePath) throws IOException {
        // Loading the housing info from JSON file
        File jsonFile = new File(filePath);
        housing = objectMapper.readValue(jsonFile, List.class);
        housing.sort((a, b) -> ((Integer) b.get("id")).compareTo((Integer) a.get("id")));
        return housing;
    }
}
