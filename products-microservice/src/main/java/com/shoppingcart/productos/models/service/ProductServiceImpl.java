package com.shoppingcart.productos.models.service;

import com.shoppingcart.productos.clients.FakeStoreClient;
import com.shoppingcart.productos.dto.ProductDTO;
import com.shoppingcart.productos.models.repository.ProductRepository;
import com.shoppingcart.productos.models.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final FakeStoreClient fakeStoreClient;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, FakeStoreClient fakeStoreClient) {
        this.productRepository = productRepository;
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void getAllProductsFromFakeStore() {
        List<ProductDTO> externalProducts = fakeStoreClient.getProducts();
        List<Product> products = externalProducts.stream()
                .map(dto -> new Product(dto.getId(), dto.getTitle(), dto.getPrice()))
                .collect(Collectors.toList());
        if (products != null) {
            for (Product product : products) {
                Product newProduct = new Product();
                newProduct.setId(product.getId());
                newProduct.setName(product.getName());
                newProduct.setPrice(product.getPrice());
                productRepository.save(newProduct);
            }
        }
    }
}
