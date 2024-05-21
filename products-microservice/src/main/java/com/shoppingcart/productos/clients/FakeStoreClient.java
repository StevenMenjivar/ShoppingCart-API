package com.shoppingcart.productos.clients;

import com.shoppingcart.productos.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "fakestore-api", url = "https://fakestoreapi.com")
public interface FakeStoreClient {
    @GetMapping("/products")
    List<ProductDTO> getProducts();
}
