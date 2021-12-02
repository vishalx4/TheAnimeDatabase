package com.example.theanimedatabase.Model;

public class Anime {
    String name,url,image_url,rated,description,episodes;

    public Anime(String name, String url, String image_url, String rated, String description, String episodes) {
        this.name = name;
        this.url = url;
        this.image_url = image_url;
        this.rated = rated;
        this.description = description;
        this.episodes = episodes;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
