package com.msa.tmdb.service;

import com.msa.tmdb.exception.InvalidDataException;
import com.msa.tmdb.exception.NotFoundException;
import com.msa.tmdb.model.Movie;
import com.msa.tmdb.repo.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // CREATE
    public Movie create(Movie movie){
        if(movie == null) {
            throw new InvalidDataException("Invalid Movie: null");
        }

        return movieRepository.save(movie);
    }

    // READ
    public Movie read(long id){
        return movieRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));
    }

    // UPDATE
    public void update(Long id, Movie update){
        if(update == null || id == null){
            throw new InvalidDataException("Invalid Movie: null");
        }

        // Check if exists
        if(movieRepository.existsById(id)){
            Movie movie = movieRepository.getReferenceById(id);
            movie.setName(update.getName());
            movie.setDirector(update.getDirector());
            movie.setActors(update.getActors());
            movieRepository.save(movie);
        }
        else {
            throw new NotFoundException("Movie not found with id: "+ id);
        }

    }

    // DELETE
    public void delete(Long id){
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
        }else {
            throw new NotFoundException("Movie not found with id: "+ id);
        }

    }


}
