package vttp.batch5.paf.movies.repositories;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.batch5.paf.movies.models.Movie;

@Repository
public class MongoMovieRepository {

    @Autowired
    private MongoTemplate template;


 // TODO: Task 2.3
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
 //
 /*
  * db.imdb.insertMany([
  {_id: <imdb_id>, title: <title>, directors: <directors>, overview: <overview>, tagline: <tagline>, genres: <genres>, imdb_rating: <imdb_rating>,imdb_votes: <imdb_votes>}
    .
    .
    .
  ])
  */
 public void batchInsertMovies(List<Movie> movies) {

    List<Document> docsToInsert = new ArrayList<>();
    for(Movie movie: movies){
        JsonObject movieObject = Movie.insertObject(movie);
        String jsonStr = movieObject.toString();
        Document doc = Document.parse(jsonStr);
        docsToInsert.add(doc);
    }
    Collection<Document> newDocs = template.insert(docsToInsert, "imdb");

 }

 // TODO: Task 2.4
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here

 /*
    db.errors.insert({
    ids: <id Array>
    error: <error message>
    timestamp: <timestamp>
    })
  */
 //
 public void logError(List<String> ids, String errorMsg) {

    JsonArrayBuilder idArrayBuilder = Json.createArrayBuilder(); 
    for(String id: ids){
        idArrayBuilder.add(id);
    }


    JsonObject errorObject = Json.createObjectBuilder()
                                .add("imdb_ids:",idArrayBuilder.build())
                                .add("error:",errorMsg)
                                .add("timestamp",new Date().toString())
                                .build();

    String errorString = errorObject.toString();
    Document docToInsert = Document.parse(errorString);

    var result = template.insert(docToInsert,"errors");

 }

 // TODO: Task 3
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here

 // db.imdb.distinct('directors')

public List<String> distictDirectors(){
    return template.findDistinct(
        new Query(), "directors","imdb", String.class);
}


/*
db.imdb.find({
    directors: {
        $regex: "<name>",
        $options: "i"
    }
})
 */
public long movieCount(String director){
    
    Criteria criteria = Criteria.where("directors").regex(director,"i");
    Query query = Query.query(criteria);
    long count = template.count(query,"imdb");
    return count;
}


}
