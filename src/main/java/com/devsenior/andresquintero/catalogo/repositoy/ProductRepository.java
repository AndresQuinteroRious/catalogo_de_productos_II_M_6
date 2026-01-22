package com.devsenior.andresquintero.catalogo.repositoy;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.devsenior.andresquintero.catalogo.model.document.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("""
            {
                "$or": [
                    { "name" : { "$regex": ?0, "$options": "i" } },
                    { "description": { "$regex": ?0, "$options": "i" } }
                ]

                }

            """)
    List<Product> findByNameOrDescriptionRegex(String text);

    @Query("{ 'especificacion. ?0' : ?1 }")
    List<Product> findByEspecificationKeyValue(String key, String value);

    List<Product> findByTagsContaining(com.devsenior.andresquintero.catalogo.model.shared.Tag tag);

}
