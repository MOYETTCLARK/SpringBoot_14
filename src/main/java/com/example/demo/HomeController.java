package com.example.demo;

import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import  org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    DirectorRepository directorRepository;

    @RequestMapping("/")
    public String nidex(Model model){

        // 1st create a director
        Director director = new Director();
        director.setName("Stephen Bullock");
        director.setGenre("Sci Fi");

        // Create a movie
        Movie movie = new Movie();
        movie.setTitle("Star Movie");
        movie.setYear(2017);
        movie.setDescription("About Stars........");

        // Add movie to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);
        movie = new Movie();
        movie.setTitle("DeathStar Ewoks");
        movie.setYear(2011);
        movie.setDescription("About Ewoks on DeathStar ....");
        movies.add(movie);

        // Add list of movies to director's movie list
        director.setMovies(movies);

        // Save director to database
        directorRepository.save(director);

        // Grave  all directors from database & send them to template
        model.addAttribute("directors", directorRepository.findAll());
        return "index";
    }

}
