package com.ubcnet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * IndexController is responsible for handling requests to the root URL ("/")
 * and providing the main page of the application.
 *
 * Representation Invariant:
 * This controller relies on the functionality of the following controllers:
 * @see AnnouncementController
 * @see HousingController
 * @see ShopController
 */
// Marking the class as a web controller (returns a response to the frontend)
@Controller
public class IndexController {

    // Injecting the other Controllers to access announcements, housing, and shop data.
    @Autowired
    private AnnouncementController announcementController;
    @Autowired
    private HousingController housingController;
    @Autowired
    private ShopController shopController;

    /**
     * Handles GET requests from the frontend and serves the index page, which displays some announcements, housings, and shop postings
     * Utilizes the same logic that was present inside the called Controllers: AnnouncementController, HousingController, and ShopController
     *
     * @param model The Model Object used to pass data to the frontend
     * @return The name of the view template to be rendered, in this case, "index", which is the home page.
     */
    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("announcements", announcementController.getAnnouncements());
        model.addAttribute("housing", housingController.getHousing());
        model.addAttribute("shop", shopController.getShop());
        return "index";
    }
}
