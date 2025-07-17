package com.varunp.JobListing.repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.varunp.JobListing.model.Post;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepository{
    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByText(String text) {
        final List<Post> posts = new ArrayList<>();
        //fill the list
        MongoDatabase database = client.getDatabase("joblistingapp");
        MongoCollection<Document> collection = database.getCollection("jobpost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("index", "default")
                                .append("text",
                                        new Document("query",text)
                                                .append("path", Arrays.asList("profile", "desc","techs")))),
                new Document("$sort",
                        new Document("exp", 1L)),
                new Document("$limit", 3L)));

    //doc received in each iteration must be added to posts  List
        result.forEach(doc -> posts.add(converter.read(Post.class,doc)));
        return posts;
    }

}
