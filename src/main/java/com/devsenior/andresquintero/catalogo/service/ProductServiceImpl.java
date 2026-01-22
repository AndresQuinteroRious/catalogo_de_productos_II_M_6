package com.devsenior.andresquintero.catalogo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsenior.andresquintero.catalogo.exception.ProductNotFoundException;
import com.devsenior.andresquintero.catalogo.exception.ProductoIdNotFoundException;
import com.devsenior.andresquintero.catalogo.mapper.ProductMapper;
import com.devsenior.andresquintero.catalogo.model.document.Product;
import com.devsenior.andresquintero.catalogo.model.dto.ProductListResponse;
import com.devsenior.andresquintero.catalogo.model.dto.ProductRequest;
import com.devsenior.andresquintero.catalogo.model.dto.ProductResponse;
import com.devsenior.andresquintero.catalogo.model.shared.Tag;
import com.devsenior.andresquintero.catalogo.repositoy.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    // removed unused save method

    @Override
    public List<ProductResponse> getAll() {

        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());

    }

    @Override
    public ProductResponse getById(String id) {
        validateId(id);
        return productRepository.findById(id)
                .map(productMapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException("producto con id " + id + " no encontrado"));
    }

    @Override
    public ProductResponse getProductById(String id) {
        return getById(id);
    }

    @Override
    public ProductResponse saveProduct(ProductRequest products) {
        Product product = new Product();
        product.setName(products.getName());
        product.setDescription(products.getDescription());
        product.setPrice(products.getPrice());
        product.setTags(products.getTags());

        Product saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }
    
    @Override
    public ProductListResponse updateProduct(String id, ProductRequest products) {
        validateId(id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("producto con id " + id + " no encontrado"));
        product.setName(products.getName());
        product.setDescription(products.getDescription());
        product.setPrice(products.getPrice());
        product.setTags(products.getTags());

        Product update = productRepository.save(product);
        ProductListResponse response = new ProductListResponse();
        response.setProducts(List.of(productMapper.toResponse(update)));
        return response;
    }
    
    @Override
    public void delete(String id) {
        validateId(id);
        productRepository.deleteById(id);
    }
    @Override
    public ProductListResponse findByDescription(String textSearch) {
        List<Product> productos = productRepository.findByNameOrDescriptionRegex(textSearch);

        if (productos.isEmpty()) {
            throw new ProductoIdNotFoundException("No se encontraron productos con la descripción existente");

        }
        ProductListResponse response = new ProductListResponse();
        response.setProducts(productos.stream()
                .map(productMapper::toResponse)
                .toList());
        return response;
    }

    @Override
    public ProductListResponse findByTags(Tag tag) {
        List<Product> productos = productRepository.findByTagsContaining(tag);

        if (productos.isEmpty()) {
            throw new ProductNotFoundException("No se encontraron productos con la etiqueta existente");
        }

        ProductListResponse response = new ProductListResponse();
        response.setProducts(productos.stream()
                .map(productMapper::toResponse)
                .toList());
        return response;
    }

    /* @Override
    public ProductListResponse findByCategory(String category) {
        List<Product> productos = productRepository.findByCategory(category);

        if (productos.isEmpty()) {
            throw new ProductNotFoundException("No se encontraron productos con la categoría existente");
        }

        ProductListResponse response = new ProductListResponse();
        response.setProducts(productos.stream()
                .map(productMapper::toResponse)
                .toList());
        return response;
        
    }
         */

    

    @Override
    public List<ProductResponse> findBySpecificationKeyValue(String key, String value) {

        validateSpecification(key, value);

        return productRepository.findByEspecificationKeyValue(key, value).stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse create(ProductRequest productsRequest) {
        return saveProduct(productsRequest);
    }

    @Override
    public ProductResponse save(ProductRequest productsRequest) {
        return saveProduct(productsRequest);
    }

    @Override
    public ProductListResponse getByTags(Tag tag) {
        return findByTags(tag);
    }

    private void validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new ProductoIdNotFoundException("El id no puede estar vacío");
        }
    }

    private void validateSpecification(String key, String value) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("La clave de especificación no puede estar vacía");
        }
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El valor de especificación no puede estar vacío");
        }
    }

   
}
