package com.ubcnet.models;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * A model that represents a particular housing listing.
 */
public class HousingModel {

    /* Abstraction function:
       id is a unique integer ID that identifies this particular housing listing
       title is a string that represents the title of this housing listing
       image is a string that represents the URL that points to the image of this housing listing
       description is a string that represents the description of this housing listing
       rentPerMonth is a double value that represents the rent per month of this housing listing
       location is a string that represents the location of this housing listing
       email is a string that represents the contact information of the owner of this housing listing
       timeCreated is a string that represents the date and time when this listing was created
     */

    // Initializing parameters
    private int id;
    private String title;
    private String image;
    private String description;
    private double rentPerMonth;
    private String location;
    private String email;
    private String timeCreated;

    /**
     * Constructor for HousingModel
     *
     * @param id must be a unique integer ID for this housing listing
     * @param title is not null
     * @param image is not null and represents a valid URL to an image
     * @param description is not null
     * @param rentPerMonth is not negative
     * @param location is not null
     * @param email is not null
     */
    public HousingModel(int id, String title, String image, String description, double rentPerMonth, String location, String email) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
        this.rentPerMonth = rentPerMonth;
        this.location = location;
        this.email = email;

        // Setting the current time as timeCreated
        Instant instant = Instant.now();
        ZoneId z = ZoneId.of( "America/Vancouver" );
        ZonedDateTime currentTime = instant.atZone( z );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.timeCreated = currentTime.format(formatter);
    }

    /**
     * Getter for housing listing ID
     *
     * @return the ID of this housing listing
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for housing listing ID
     *
     * @param id must be a unique integer ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for housing listing title
     *
     * @return the title of this housing listing
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for housing listing title
     *
     * @param title the title of this housing listing
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for housing listing image
     *
     * @return the URL of the image of this housing listing
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter for the housing listing image
     *
     * @param image the URL of the image of this housing listing, must be a valid image URL
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Getter for the housing listing description
     *
     * @return the description of this housing listing
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the housing listing description
     *
     * @param description the description of this housing listing
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the housing listing rent per month
     *
     * @return the rent per month of this housing listing
     */
    public double getRentPerMonth() {
        return rentPerMonth;
    }

    /**
     * Setter for the housing listing rent per month
     *
     * @param rentPerMonth the rent per month of this housing listing, is not negative
     */
    public void setRentPerMonth(double rentPerMonth) {
        this.rentPerMonth = rentPerMonth;
    }

    /**
     * Getter for the housing listing location
     *
     * @return the location of this housing listing
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter for the housing listing location
     *
     * @param location the location of this housing listing
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter for the contact information of the housing listing owner
     *
     * @return the contact information of the owner of this housing listing
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for the contact information of the housing listing owner
     *
     * @param email the contact information of the owner of this housing listing
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for housing listing creation time
     *
     * @return the creation time of this housing listing in dd-mm-yyyy hh:mm:ss format
     */
    public String getCreated() {
        return timeCreated;
    }

    /**
     * Setter for housing listing creation time
     *
     * @param time is not null and is in Vancouver time zone
     */
    public void setCreated(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.timeCreated = time.format(formatter);
    }

    // Override for Hashcode and Equals

    /**
     * Compares the specified Object o with this HousingModel for equality
     *
     * @param o object to be compared against
     * @return true if the specified Object o is equal to this HousingModel
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HousingModel model)) return false;

        return id == model.id;
    }

    /**
     * Returns the hashcode for this HousingModel
     *
     * @return the hashcode, which is equal to the id (no two ids should be the same)
     */
    @Override
    public int hashCode() {
        return id;
    }
}