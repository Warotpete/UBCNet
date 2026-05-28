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
 * The ShopController Class is an immutable datatype used to get data from the shop.json file
 *
 * Representation Invariant: The ShopController Class is represented by a List of Maps,
 *                           where each Map represents an individual post. Each Map is
 *                           mapping a String (the name of the input) to an Object (the
 *                           actual post input).
 */
// Marking the class as a web controller (returns a response to the frontend)
@Controller
public class ShopController {

    // Initialize the shop list
    private List<Map<String, Object>> shop;

    // Get the local environment
    @Value("${app.env:local}")
    private String appEnv; // Injected environment variable

    // Initialize the object mapper
    private final ObjectMapper objectMapper;

    /**
     * Constructor
     *
     * @param objectMapper
     */
    public ShopController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     *  Handles the GET requests for the "/shop page"
     *
     * @param model The Model Object used to pass data to the frontend
     * @return The name of the view template to be rendered, in this case, "shop", which is the shop page.
     */
    @GetMapping("/shop")
    public String shopPage(Model model) {
        model.addAttribute("title", "Shop");
        model.addAttribute("shop", getShop());
        return "shop";
    }

    /**
     * Shop getter that calls the loadShop method
     *
     * @return the list of shop, or an empty list if the
     *         loadShop method is not working
     */
    public List<Map<String, Object>> getShop(){
        String filePath = determineFilePath(appEnv);
        try {
            return loadShop(filePath);
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
            return "/app/data/shop.json";
        }
        return "src/main/resources/data/shop.json";
    }

    /**
     * Receiving the data from the json database and storing it inside the shop list
     * initialized inside the ShopController class
     *
     * @param filePath the path to the json database
     * @return shop, the list of shop
     * @throws IOException if the path is not found or the database is not readable
     */
    private List<Map<String, Object>> loadShop(String filePath) throws IOException {
        // Loading the shop info from JSON file
        File jsonFile = new File(filePath);
        shop = objectMapper.readValue(jsonFile, List.class);
        shop.sort((a, b) -> ((Integer) b.get("id")).compareTo((Integer) a.get("id")));
        return shop;
    }
}
