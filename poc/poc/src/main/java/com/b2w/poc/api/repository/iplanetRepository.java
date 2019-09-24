package com.b2w.poc.api.repository;

import com.b2w.poc.api.document.planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface iplanetRepository extends MongoRepository<planet,String> {
    @Query("{'nome':?0}")
    Optional<planet> listarPorNome(String nome);
}
