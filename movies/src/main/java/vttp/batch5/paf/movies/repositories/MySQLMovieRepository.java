package vttp.batch5.paf.movies.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.batch5.paf.movies.models.Movie;

@Repository
public class MySQLMovieRepository {

  @Autowired 
  private JdbcTemplate template;

  public static String  SQL_INSERT_MOVIES =
  """
      insert into imdb(imdb_id,vote_average,vote_count,release_date,revenue,budget,runtime)
      values(?,?,?,?,?,?,?)
      """;

  // TODO: Task 2.3
  // You can add any number of parameters and return any type from the method
  public void batchInsertMovies(List<Movie> movies) {
    List<Object[]> params = movies.stream()
        .map(movie -> new Object[] {movie.getImdbId(), movie.getVoteAverage(),movie.getVoteCount(),movie.getReleaseDate(),movie.getRevenue(),movie.getBudget(),movie.getRuntime()})
        .collect(Collectors.toList());
    int added[] = template.batchUpdate(SQL_INSERT_MOVIES, params);
  }
  
  // TODO: Task 3


}
