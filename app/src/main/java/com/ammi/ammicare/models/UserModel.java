package com.ammi.ammicare.models;

import java.util.HashMap;

public class UserModel {

    private String name;
    private String email;
    private String profileImageLink;
    private String firstName;
    private String lastName;
    private String gender;
    private String link;
    private String id;
    private String uid;
    private String isVerified;
    private String locale;
    private String provider;
    private String childDateOfBirth;
    private HashMap<String, Object> timestampLastSeen;
    private HashMap<String, Object> timestampJoined;

    public UserModel(String name, String email, String profileImageLink, String firstName, String lastName, String gender, String link, String id, String uid, String isVerified, String locale, String provider, String childDateOfBirth, HashMap<String, Object> timestampLastSeen, HashMap<String, Object> timestampJoined) {
        this.name = name;
        this.email = email;
        this.profileImageLink = profileImageLink;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.link = link;
        this.id = id;
        this.uid = uid;
        this.isVerified = isVerified;
        this.locale = locale;
        this.provider = provider;
        this.childDateOfBirth = childDateOfBirth;
        this.timestampLastSeen = timestampLastSeen;
        this.timestampJoined = timestampJoined;
    }

    public String getChildDateOfBirth() {
        return childDateOfBirth;
    }

    public void setChildDateOfBirth(String childDateOfBirth) {
        this.childDateOfBirth = childDateOfBirth;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public UserModel() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfileImageLink() {
        return profileImageLink;
    }

    public void setProfileImageLink(String profileImageLink) {
        this.profileImageLink = profileImageLink;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public HashMap<String, Object> getTimestampLastSeen() {
        return timestampLastSeen;
    }

    public void setTimestampLastSeen(HashMap<String, Object> timestampLastSeen) {
        this.timestampLastSeen = timestampLastSeen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, Object> getTimestampJoined() {
        return timestampJoined;
    }

    public void setTimestampJoined(HashMap<String, Object> timestampJoined) {
        this.timestampJoined = timestampJoined;
    }
}