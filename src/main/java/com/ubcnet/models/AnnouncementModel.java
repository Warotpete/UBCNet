package com.ubcnet.models;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * A model that represents a particular announcement.
 */
public class AnnouncementModel {

    /* Abstraction function:
       id is a unique integer ID that identifies this particular announcement
       title is a string that represents the title of the announcement
       message is a string that represents the message/details of the announcement
       timeCreated is a string that represents the date and time when the announcement was created
     */

    // Initializing parameters
    private int id;
    private String title;
    private String message;
    private String timeCreated;

    /**
     * Constructor for an AnnouncementModel
     *
     * @param id must be a unique integer ID for this announcement
     * @param title is not null
     * @param message is not null
     */
    public AnnouncementModel(int id, String title, String message) {
        this.id = id;
        this.title = title;
        this.message = message;

        // Setting the current time as timeCreated
        Instant instant = Instant.now();
        ZoneId z = ZoneId.of( "America/Vancouver" );
        ZonedDateTime currentTime = instant.atZone( z );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.timeCreated = currentTime.format(formatter);
    }

    /**
     * Getter for announcement ID
     *
     * @return the ID of this announcement
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for announcement ID
     *
     * @param id must be a unique integer ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for announcement title
     *
     * @return the title of this announcement
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for announcement title
     *
     * @param title the title of this announcement
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for announcement message
     *
     * @return the message of this announcement
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for announcement message
     *
     * @param message the message of this announcement
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter for announcement creation time
     *
     * @return the creation time of this announcement in dd-mm-yyyy hh:mm:ss format
     */
    public String getCreated() {
        return timeCreated;
    }

    /**
     * Setter for announcement creation Time
     *
     * @param time is not null and is in Vancouver time zone
     */
    public void setCreated(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.timeCreated = time.format(formatter);
    }

    // Override for Hashcode and Equals
    /**
     * Compares the specified Object o with this AnnouncementModel for equality
     *
     * @param o object to be compared against
     * @return true if the specified Object o is equal to this AnnouncementModel
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnnouncementModel model)) return false;

        return id == model.id;
    }

    /**
     * Returns the hashcode for this AnnouncementModel
     *
     * @return the hashcode, which is equal to the id (no two ids should be the same)
     */
    @Override
    public int hashCode() {
        return id;
    }
}