package com.ratna.cloud.function.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ratna.cloud.function.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {

}
