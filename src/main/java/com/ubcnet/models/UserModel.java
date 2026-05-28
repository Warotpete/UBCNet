package com.ubcnet.models;

import java.time.LocalDateTime;

/**
 * A model that represents a particular user of UBCNet
 */
public class UserModel {

    /* Abstraction function
       id is a unique ID that identifies this particular user
       username is a string that represents the unique username of this partucular user
       password is a string that represents the hashed password of this particular user
     */

    // Initializing parameters
    private int id;
    private String username;
    private String password;

    /**
     * Constructor for UserModel
     *
     * @param id must be a unique integer ID for this user
     * @param username is not null
     * @param password is not null, and is a hash (not the actual password)
     */
    public UserModel(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Getter for user ID
     *
     * @return the ID of this user
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for user ID
     *
     * @param id must be a unique integer ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the user's username
     *
     * @return the username of this user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the user's username
     *
     * @param username is not null and is unique
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for the user's hashed password
     *
     * @return the hashed password of this user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the user's hashed password
     *
     * @param password is not null, and is a hash (not the actual password)
     */
    public void setPassword(String password) {
        this.password = password;
    }

    // Override for Hashcode and Equals

    /**
     * Compares the specified Object o with this userModel for equality
     *
     * @param o object to be compared against
     * @return true if the specified Object o is equal to this UserModel
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel model)) return false;

        return id == model.id;
    }

    /**
     * Returns the hashcode for this UserModel
     *
     * @return the hashcode, which is equal to the id (no two ids should be the same)
     */
    @Override
    public int hashCode() {
        return id;
    }
}
