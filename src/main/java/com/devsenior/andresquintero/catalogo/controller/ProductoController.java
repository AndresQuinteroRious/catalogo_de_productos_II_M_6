package com.devsenior.andresquintero.catalogo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.andresquintero.catalogo.model.dto.ProductListResponse;
import com.devsenior.andresquintero.catalogo.model.dto.ProductRequest;
import com.devsenior.andresquintero.catalogo.model.dto.ProductResponse;
import com.devsenior.andresquintero.catalogo.model.shared.Tag;
import com.devsenior.andresquintero.catalogo.service.ProductService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductService productService;

    public ProductoController(ProductService productService) {
        this.productService = productService;
    }

    // Métodos para manejar las solicitudes HTTP

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAll();
    }
    @PostMapping
    public ProductResponse createNewProduct(@RequestBody @Valid ProductRequest productsRequest) {
        return productService.save(productsRequest);
    }

@GetMapping("/{id}")
public ProductResponse getProductById(@PathVariable String id) {
    return productService.getById(id);
}

@PutMapping("/{id}")
public ProductListResponse upgradeProduct(@PathVariable String id, @RequestBody @Valid ProductRequest productRequest) {
    return productService.updateProduct(id, productRequest);
}

@DeleteMapping("/{id}")
public void deleteProduct(@PathVariable String id) {
    productService.delete(id);
}

@GetMapping("/find")
public ProductListResponse findProductByParams(@RequestParam ("b") String textSearch) {
    return productService.findByDescription(textSearch);

}

@GetMapping("/tag")
public ProductListResponse findProductByTag(@RequestParam ( "b") Tag tag) {
    return productService.getByTags(tag);
}


@GetMapping("/especificacion")
public List<ProductResponse> findProductskeyValue(@RequestParam ("t")String key, @RequestParam ("g") String value) {
    return productService.findBySpecificationKeyValue(key, value);
}

}
