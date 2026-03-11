package career.lld.practice.bookmyshow.mi1;

class Movie {

    private String movieId;
    private String title;
    private int duration;

    public Movie(String movieId, String title, int duration) {
        this.movieId = movieId;
        this.title = title;
        this.duration = duration;
    }

    public String getMovieId() {
        return movieId;
    }
}