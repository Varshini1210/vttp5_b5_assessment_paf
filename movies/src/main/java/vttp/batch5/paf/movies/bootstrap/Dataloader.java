package vttp.batch5.paf.movies.bootstrap;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.stream.JsonParser;
import vttp.batch5.paf.movies.models.Movie;
import vttp.batch5.paf.movies.services.MovieService;

@Component
public class Dataloader implements CommandLineRunner {

  private int batchCount;

  public int getBatchCount() {
    return batchCount;
  }

  public void setBatchCount(int batchCount) {
    this.batchCount = batchCount;
  }

  @Autowired
  private MovieService movieService;

  //TODO: Task 2

  @Override
  public void run(String... args) throws Exception {

    
    //configure with load flag

    ApplicationArguments argsOptions = new DefaultApplicationArguments(args);

    String moviesZipFileName = "../data/movies_post_2010.zip";
    
		if (argsOptions.containsOption("load")){
			moviesZipFileName = argsOptions.getOptionValues("load").get(0);
    }

    Path zipPath = Paths.get(moviesZipFileName);

    ZipFile zipFile = new ZipFile(moviesZipFileName);

    Enumeration<? extends ZipEntry> entries = zipFile.entries();
    String line;
    StringBuilder builder = new StringBuilder();
    JsonArrayBuilder jArrayBuilder = Json.createArrayBuilder();

    String[] stringAttributes = {"title","status","release_date","imdb_id","original_language","overview","tagline","genres","spoken_languages","casts","director","poster_path"};
    String[] numberAttributes = {"vote_average","vote_count","revenue","runtime","budget","popularity","imdb_rating","imdb_votes"};
    
    List<Movie> movies = new ArrayList<>();

    while(entries.hasMoreElements()){
        ZipEntry entry = entries.nextElement();
        InputStream stream = zipFile.getInputStream(entry);
        InputStreamReader isr = new InputStreamReader(stream);
        
        LineNumberReader lnr= new LineNumberReader(isr);
        while (null != (line=lnr.readLine())){
          JsonReader jreader = Json.createReader(new StringReader(line));
          try{
            JsonObject movieObject = jreader.readObject();
            int release_year = Integer.parseInt(movieObject.getString("release_date").substring(0,4));
            if (release_year<=2018){
              continue;
            }
            Iterator<String> keys = movieObject.keySet().iterator();

            while (keys.hasNext()){
              String key = keys.next();
              if(Arrays.asList(stringAttributes).contains(key)){
                try{
                  movieObject.getString(key);
                }
                catch (Exception e){
                  movieObject.put(key,null);
                }
              }
              if(Arrays.asList(numberAttributes).contains(key)){
                try{
                  movieObject.getJsonNumber(key);
                }
                catch (Exception e){
                  movieObject.put(key,null);
                }
              }
              

              }
              
            
            jArrayBuilder.add(movieObject);
            movies.add(Movie.toMovie(movieObject));
            
          }
          catch (Exception e){
            continue;
          // builder.append(line);
        }
        
    }

    JsonArray moviesArray = jArrayBuilder.build();
    System.out.println(moviesArray.isEmpty());
    System.out.println(moviesArray.size());
    System.out.println(movies.size());

    int batchCount = 0;
    List<Movie> batchMovies = new ArrayList<>();
    
    for (int i =0; i < moviesArray.size();i++){
        System.out.println(batchCount);
        batchMovies.add(movies.get(i));
        batchCount++;
        if (batchCount== 25){
          //batch insert
          try{
            movieService.insertBatch(batchMovies);
          //reset count
            this.batchCount =0;
            batchMovies.clear();
          }
          catch(Exception e){
            this.batchCount = 0;
            batchMovies.clear();
            continue;
          }
          
          
         
          
        }
        

      }
  
   

   



   
    
    }


  }

  

}
