package com.example.moviecatalogservice.resources;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;
import com.example.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;
       @RequestMapping("{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
                      UserRating ratings= restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" +userId , UserRating.class);

             return ratings.getUserrating().stream().map(rating -> {
              Movie movie=restTemplate.getForObject("http://localhost:8082/movies/" +rating.getMovieId() , Movie.class);

              return new CatalogItem(movie.getName(), "Testing",rating.getRating());})
                  .collect(Collectors.toList());


    }
}
