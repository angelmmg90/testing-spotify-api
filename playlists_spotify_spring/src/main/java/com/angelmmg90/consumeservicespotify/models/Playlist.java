/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angelmmg90.consumeservicespotify.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author amacdong
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Playlist {
    private boolean collaborative;
    private String description;
    private String href;
    private String id;
    private String name;
    private ArrayList<Image> images = new ArrayList<>();

    
    
    
    
    
    public Playlist(){
    
    }

    public boolean isCollaborative() {
        return collaborative;
    }

    public void setCollaborative(boolean collaborative) {
        this.collaborative = collaborative;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
    
    @Override
    public String toString() {
        return "Playlist{" +
                "collaborative='" + collaborative + '\'' +
                ", description=" + description + '\'' +                
                ", href=" + href + '\'' +
                ", id=" + id + '\'' +
                ", name=" + name +
                '}';
    }
    
    
}
