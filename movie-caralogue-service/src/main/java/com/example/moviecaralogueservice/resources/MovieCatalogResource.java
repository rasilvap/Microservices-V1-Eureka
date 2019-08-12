package com.example.moviecaralogueservice.resources;

import com.example.moviecaralogueservice.models.CatalogItem;
import com.example.moviecaralogueservice.models.Movie;
import com.example.moviecaralogueservice.models.Rating;
import com.example.moviecaralogueservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private WebClient.Builder builder;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
        return ratings.getUserRatings().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movies-data-service/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), rating.getRating() + "");
        }).collect(Collectors.toList());
       /* List<Rating> ratings = Arrays.asList(
                new Rating("movie One", 1),
                new Rating("movie Two", 2)
        );*/
      /*  WebClient.Builder builder = WebClient.builder();
        return ratings.stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), movie.getName());
        }).collect(Collectors.toList());

        return Collections.singletonList(
                new CatalogItem("Transformers", "4")
        );*/
    }
}
