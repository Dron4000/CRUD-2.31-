package com.javaspring.ProductService;

import com.javaspring.ProductRepository.ProductRepository;
import com.javaspring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
@Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }




  public   List<Product>getAllProducts(){
        List<Product>productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        return  productList;
    }
  public   void  saveProduct(Product product){
        productRepository.save(product);
    }
  public   void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
  public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }
}
