package com.ubcnet.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubcnet.models.AnnouncementModel;
import com.ubcnet.models.HousingModel;
import com.ubcnet.models.ShopModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * PostController handles creating new posts for Announcements, Housing, and Shop sections,
 * and saving the data inside the json database.
 *
 * Representation Invariant:
 * This controller relies on other controllers to fetch existing data
 * It also appends new data to json files.
 *
 */
// Marking the class as a web controller (returns a response to the frontend)
@Controller
public class PostController {

    // Injecting the other Controllers to access announcements, housing, and shop data.
    @Autowired
    private AnnouncementController announcementController;
    @Autowired
    private HousingController housingController;
    @Autowired
    private ShopController shopController;

    /**
     * Handles GET requests for the "/post" frontend
     *
     * @return The name of the view template to be rendered, in this case, "post", which represents the post page
     */
    @GetMapping("/post")
    public String postPage() {
        return "post";
    }

    /**
     * Gets the user input from the javascript frontend (PostMapping). Not
     * all types of posts have the same parameter, which is why not all are required.
     *
     * @param postType
     * @param title
     * @param message
     * @param image
     * @param description
     * @param rentPerMonth
     * @param location
     * @param price
     * @param condition
     * @param email
     * @param model
     * @return
     * @throws URISyntaxException if the inputs were not received
     */
    @PostMapping("/create-post")
    public String createPost(
            @RequestParam("postType") String postType,
            @RequestParam("title") String title,
            @RequestParam(value = "message", required = false) String message,
            @RequestParam(value = "images", required = false) String image,
            @RequestParam(value = "description", required = false) String description,

            //for housing
            @RequestParam(value = "rentPerMonth", required = false) Double rentPerMonth,
            @RequestParam(value = "location", required = false) String location,

            //for shop
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "condition", required = false) String condition,
            @RequestParam(value = "email", required = false) String email,
            Model model
    ) throws URISyntaxException {
        System.out.println(postType + title);

        // Determine the file path based on an environment variable
        String environment = System.getenv("APP_ENV"); // Use APP_ENV to determine environment
        String filePath;

        /**
         * If the post is for announcements, create a new announcementModel with the information
         * from the user and save it in the json database.
         *
         * The path is dependent on whether the environment is docker or the local computer.
         */
        if (postType.equalsIgnoreCase("announcement")) {
            int newId = announcementController.getAnnouncements().size() + 1;
            // Create a new AnnouncementModel object
            AnnouncementModel newAnnouncement = new AnnouncementModel(newId, title, message);
            if ("docker".equalsIgnoreCase(environment)) {
                filePath = "/app/data/announcements.json"; // Path for Docker
            } else {
                filePath = "src/main/resources/data/announcements.json"; // Path for local
            }

            // Append the new post data to the JSON file
            appendToJsonFile(newAnnouncement, filePath);
        }

        /**
         * If the post is for housing, create a new announcementModel with the information
         * from the user and save it in the json database.
         *
         * The path is dependent on whether the environment is docker or the local computer.
         */
        else if (postType.equalsIgnoreCase("housing")) {
            String imageAsString;
            int newId = housingController.getHousing().size() + 1;
            /**
             * The image URL is turned into a bufferedImage
             * The bufferedImage is turned into a ByteArrayOutputStream
             * The ByteArrayOutputStream is turned into an array of bytes
             * The array of bytes is turned into a String that represents the bufferedImage
             *
             * Even though we store the URL into json, the purpose of this was that if the URL cannot
             * turn into an image, the URL does not represent an image and an error is called inside the javascript
             */
            try {
                URL url = new URI(image).toURL();
                BufferedImage buffer = ImageIO.read(url);

                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                ImageIO.write(buffer, "png", byteStream);
                byte[] byteArray = byteStream.toByteArray();
                imageAsString =  Base64.getEncoder().encodeToString(byteArray);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Create a new HousingModel object
            HousingModel newHousing = new HousingModel(newId, title, image, description, rentPerMonth, location, email);
            if ("docker".equalsIgnoreCase(environment)) {
                filePath = "/app/data/housing.json"; // Path for Docker
            } else {
                filePath = "src/main/resources/data/housing.json"; // Path for local
            }
            // File path to the existing JSON file
            File jsonFile = new File(filePath);

            // Append the new post data to the JSON file
            appendToJsonFile(newHousing, filePath);
        }

        /**
         * If the post is for shop, create a new announcementModel with the information
         * from the user and save it in the json database.
         *
         * The path is dependent on whether the environment is docker or the local computer.
         */
        else if (postType.equalsIgnoreCase("shop")) {
            String imageAsString;
            int newId = shopController.getShop().size() + 1;
            /**
             * The image URL is turned into a bufferedImage
             * The bufferedImage is turned into a ByteArrayOutputStream
             * The ByteArrayOutputStream is turned into an array of bytes
             * The array of bytes is turned into a String that represents the bufferedImage
             *
             * Even though we store the URL into json, the purpose of this was that if the URL cannot
             * turn into an image, the URL does not represent an image and an error is called inside the javascript
             */
            try {
                URL url = new URI(image).toURL();
                BufferedImage buffer = ImageIO.read(url);

                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                ImageIO.write(buffer, "png", byteStream);
                byte[] byteArray = byteStream.toByteArray();
                imageAsString =  Base64.getEncoder().encodeToString(byteArray);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Create a new ShopModel object
            ShopModel newShop = new ShopModel(newId, title, image, description, price, condition, email);
            if ("docker".equalsIgnoreCase(environment)) {
                filePath = "/app/data/shop.json"; // Path for Docker
            } else {
                filePath = "src/main/resources/data/shop.json"; // Path for local
            }
            // File path to the existing JSON file
            File jsonFile = new File(filePath);

            // Append the new post data to the JSON file
            appendToJsonFile(newShop, filePath);
        }

        // Redirect back to the index page
        return "redirect:/";
    }

    /**
     * Appends a new post to a json file database.
     *
     * @param newPost The new post to be added.
     * @param filePath The file path of the json file.
     * @param <T> The type of the post (Announcement, Housing, or Shop).
     */
    private <T> void appendToJsonFile(T newPost, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);

        List<T> posts;

        try {
            // Check if the file exists
            if (file.exists()) {
                // Read the existing JSON data into a List
                posts = objectMapper.readValue(file, new TypeReference<List<T>>() {});
            } else {
                // If the file doesn't exist, create a new list
                posts = new ArrayList<>();
            }

            // Add the new post data to the list
            posts.add(newPost);

            // Write the updated list back to the file
            objectMapper.writeValue(file, posts);

            System.out.println("Data appended to file successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to append to JSON file: " + e.getMessage());
        }
    }
}
