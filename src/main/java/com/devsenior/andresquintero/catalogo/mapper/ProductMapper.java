
package com.devsenior.andresquintero.catalogo.mapper;

import org.springframework.stereotype.Component;
import com.devsenior.andresquintero.catalogo.model.document.Product;
import com.devsenior.andresquintero.catalogo.model.dto.ProductRequest;
import com.devsenior.andresquintero.catalogo.model.dto.ProductResponse;

@Component
public class ProductMapper {

    public Product toDocument ( ProductRequest Info) {
var response = new Product();

response.setName(Info.getName());
response.setDescription(Info.getDescription());
response.setPrice(Info.getPrice());
response.setEspecification(Info.getEspecification());
response.setTags(Info.getTags());
return response;
    }

public ProductResponse toResponse ( Product Info) {
var response = new ProductResponse();

response.setId(Info.getId());
response.setName(Info.getName());
response.setDescription(Info.getDescription());
response.setPrice(Info.getPrice());
response.setEspecification(Info.getEspecification());
response.setTags(Info.getTags());
return response;
}

}
