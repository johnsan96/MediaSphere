package com.john.backendMediaSphere.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;   
    private String description;
    private String type;

    Media() {
    }

    Media(String title, String description, String type) {

        this.title = title;
        this.description = description;
        this.type = type;
      
    }

 
    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return this.type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Media))  
            return false;
        Media media = (Media) o;
        return Objects.equals(this.id, media.id) && Objects.equals(this.title, media.title)
                && Objects.equals(this.type, media.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.description, this.type);
    }

    @Override
    public String toString() {
        return "Media{" + "id=" + this.id + ", title='" + this.title + '\'' + ", description='" + this.description + '\'' + ", type='" + this.type + '\'' +  '}';
    }
}
