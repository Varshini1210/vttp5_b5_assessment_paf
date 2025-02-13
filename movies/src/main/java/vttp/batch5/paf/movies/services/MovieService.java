package vttp.batch5.paf.movies.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp.batch5.paf.movies.models.InsertionErrorException;
import vttp.batch5.paf.movies.models.Movie;
import vttp.batch5.paf.movies.repositories.MongoMovieRepository;
import vttp.batch5.paf.movies.repositories.MySQLMovieRepository;

@Service
public class MovieService {

  @Autowired
  private MongoMovieRepository mongoRepo;

  @Autowired
  private MySQLMovieRepository sqlRepo;

  // TODO: Task 2

  @Transactional
  public void insertBatch(List<Movie> movies){
    try{
      sqlRepo.batchInsertMovies(movies);
      mongoRepo.batchInsertMovies(movies);
    }
    catch(Exception e){
      List<String> ids = new ArrayList<>();
      for (Movie movie: movies){
        ids.add(movie.getImdbId());
      }

      mongoRepo.logError(ids, e.getMessage());
      throw new InsertionErrorException(e.getMessage(),ids);
    }
    
    
  }
  

  // TODO: Task 3
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public void getProlificDirectors() {
  }


  // TODO: Task 4
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public void generatePDFReport() {

  }

}
