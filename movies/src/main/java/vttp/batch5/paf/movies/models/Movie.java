package vttp.batch5.paf.movies.models;

import java.sql.Date;

import jakarta.json.Json;

import jakarta.json.JsonObject;

public class Movie {

    private String imdbId;
    private double voteAverage;
    private int voteCount;
    private Date releaseDate;
    private int revenue;
    private int budget;
    private int runtime;

    private String title;
    private String directors;
    private String overview;
    private String tagline;
    private String genres;
    private int imdbRating;
    private int imdbVotes;
    
    public Movie(String imdbId, double voteAverage, int voteCount, Date releaseDate, int revenue, int budget,
            int runtime, String title, String directors, String overview, String tagline, String genres, int imdbRating,
            int imdbVotes) {
        this.imdbId = imdbId;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.budget = budget;
        this.runtime = runtime;
        this.title = title;
        this.directors = directors;
        this.overview = overview;
        this.tagline = tagline;
        this.genres = genres;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
    }

    public Movie() {
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(int imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(int imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public static Movie toMovie(JsonObject movieObject){

        Date date = Date.valueOf(movieObject.getString("release_date"));

        return new Movie(

            movieObject.getString("imdb_id"),
            movieObject.getJsonNumber("vote_average").doubleValue(),
            movieObject.getJsonNumber("vote_count").intValue(),
            date,
            movieObject.getJsonNumber("revenue").intValue(),
            movieObject.getJsonNumber("budget").intValue(),
            movieObject.getJsonNumber("runtime").intValue(),
            movieObject.getString("title"),
            movieObject.getString("director"),
            movieObject.getString("overview"),
            movieObject.getString("tagline"),
            movieObject.getString("genres"),
            movieObject.getJsonNumber("imdb_rating").intValue(),
            movieObject.getJsonNumber("imdb_votes").intValue()


        );

        

        }
        public static JsonObject insertObject(Movie movie){

            JsonObject toInsert= Json.createObjectBuilder()
                                .add("_id", movie.getImdbId())
                                .add("title",movie.getTitle())
                                .add("directors",movie.getDirectors())
                                .add("overview",movie.getOverview())
                                .add("tagline",movie.getTagline())
                                .add("genres",movie.getGenres())
                                .add("imdb_rating",movie.getImdbRating())
                                .add("imdb_votes",movie.getImdbVotes())
                                .build();
            return toInsert;
    }

    
}
