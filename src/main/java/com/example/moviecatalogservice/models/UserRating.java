package com.example.moviecatalogservice.models;
import java.util.List;

public class UserRating {
    private List<Rating> userrating;



    public List<Rating> getUserrating() {

        return userrating;
    }

    public void setUserrating(List<Rating> userrating) {

        this.userrating = userrating;
    }
}
