package com.shoppingcart.productos.controllers;

import com.shoppingcart.productos.dto.ProductDTO;
import com.shoppingcart.productos.models.entity.Product;
import com.shoppingcart.productos.models.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> allProducts() {
        return productService.findAll().stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice()))
                .collect(Collectors.toList());
    }

    @GetMapping("/products/view/{id}")
    public ProductDTO viewProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The product was not found");
        }
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    @PostMapping("/create-product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product(productDTO.getId(), productDTO.getTitle(), productDTO.getPrice());
        product = productService.save(product);
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    @PutMapping("/update-product/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id) {
        Product productBD = productService.findById(id);
        if (productBD == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The product was not found");
        }
        productBD.setName(productDTO.getTitle());
        productBD.setPrice(productDTO.getPrice());
        productBD = productService.save(productBD);
        return new ProductDTO(productBD.getId(), productBD.getName(), productBD.getPrice());
    }

    @DeleteMapping("/delete-product/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/get-products-fakestore")
    @ResponseStatus(HttpStatus.OK)
    public void getAllProductsFromAPI() {
        productService.getAllProductsFromFakeStore();
    }
}
