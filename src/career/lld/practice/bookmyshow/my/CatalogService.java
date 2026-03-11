package career.lld.practice.bookmyshow.my;

import java.util.*;

class CatalogService {

    private Map<String, Movie> movies = new HashMap<>();
    private Map<String, Show> shows = new HashMap<>();

    public void addMovie(Movie movie) {
        movies.put(movie.getMovieId(), movie);
    }

    public void addShow(Show show) {
        shows.put(show.getMovie().getMovieId(), show);
    }

    public Collection<Movie> getMovies() {
        return movies.values();
    }

    public Show getShow(String movieId) {
        return shows.get(movieId);
    }
}