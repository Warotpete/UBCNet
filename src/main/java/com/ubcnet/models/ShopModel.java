package com.ubcnet.models;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * A model that represents a particular shop listing
 */
public class ShopModel {

    /* Abstraction function:
       id is a unique integer ID that identifies this particular shop listing
       title is a string that represents the title of this shop listing
       image is a string that represents the URL that points to the image of this shop listing
       description is a string that represents the description of this shop listing
       price is a double value that represents the price of this shop listing
       condition is a string that represents the condition of this shop listing
       email is a string that represents the contact information of the seller of this shop listing
       timeCreated is a string that represents the date and time when this listing was created
     */

    // Initializing parameters for item
    private int id;
    private String title;
    private String image;
    private String description;
    private double price;
    private String condition;
    private String email;
    private String timeCreated;

    /**
     * Constructor for ShopModel
     *
     * @param id must be a unique integer ID for this shop listing
     * @param title is not null
     * @param image is not null and represents a valid URL to an image
     * @param description is not null
     * @param price is not negative
     * @param condition is not null
     * @param email is not null
     */
    public ShopModel(int id, String title, String image, String description, double price, String condition, String email) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
        this.price = price;
        this.condition = condition;
        this.email = email;

        // Setting the current time as timeCreated
        Instant instant = Instant.now();
        ZoneId z = ZoneId.of( "America/Vancouver" );
        ZonedDateTime currentTime = instant.atZone( z );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.timeCreated = currentTime.format(formatter);
    }

    /**
     * Getter for shop listing ID
     *
     * @return the ID of this housing listing
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for shop listing ID
     *
     * @param id must be a unique integer ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for shop listing title
     *
     * @return the title of this shop listing
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for housing shop title
     *
     * @param title the title of this shop listing
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for shop listing image
     *
     * @return the URL of the image of this shop listing
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter for the shop listing image
     *
     * @param image the URL of the image of this shop listing, must be a valid image URL
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Getter for the shop listing description
     *
     * @return the description of this shop listing
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the shop listing description
     *
     * @param description the description of this shop listing
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the shop listing price
     *
     * @return the price of this shop listing
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for the shop listing price
     *
     * @param price the price of this shop listing, is not negative
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for the shop listing condition
     *
     * @return the condition of this shop listing
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Setter for the shop listing condition
     *
     * @param condition the condition of this shop listing
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Getter for the contact information of the shop listing seller
     *
     * @return the contact information of the seller of this shop listing
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for the contact information of the shop listing seller
     *
     * @param email the contact information of the seller of this shop listing
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for shop listing creation time
     *
     * @return the creation time of this shop listing in dd-mm-yyyy hh:mm:ss format
     */
    public String getCreated() {
        return timeCreated;
    }

    /**
     * Setter for shop listing creation time
     *
     * @param time is not null and is in Vancouver time zone
     */
    public void setCreated(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.timeCreated = time.format(formatter);
    }

    // Override for Hashcode and Equals

    /**
     * Compares the specified Object o with this ShopModel for equality
     *
     * @param o object to be compared against
     * @return true if the specified Object o is equal to this ShopModel
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopModel model)) return false;

        return id == model.id;
    }

    /**
     * Returns the hashcode for this ShopModel
     *
     * @return the hashcode, which is equal to the id (no two ids should be the same)
     */
    @Override
    public int hashCode() {
        return id;
    }
}