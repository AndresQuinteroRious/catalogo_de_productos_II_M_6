package com.devsenior.andresquintero.catalogo.service;

import java.util.List;

import com.devsenior.andresquintero.catalogo.model.dto.ProductListResponse;
import com.devsenior.andresquintero.catalogo.model.dto.ProductRequest;
import com.devsenior.andresquintero.catalogo.model.dto.ProductResponse;
import com.devsenior.andresquintero.catalogo.model.shared.Tag;

public interface ProductService {

    ProductResponse saveProduct(ProductRequest product);

    List<ProductResponse> getAll();

    ProductResponse getProductById(String id);

    void delete(String id);

    ProductListResponse findByDescription(String textSearch);

    ProductListResponse findByTags(Tag tag);

    ProductListResponse updateProduct(String id, ProductRequest productRequest);

    List<ProductResponse> findBySpecificationKeyValue(String key, String value);

    ProductResponse create(ProductRequest productsRequest);

    ProductResponse save(ProductRequest productsRequest);

    ProductResponse getById(String id);

    ProductListResponse getByTags(Tag tag);



}
