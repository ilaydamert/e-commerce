package com.ecommerce.ecommerce.Services;
import com.ecommerce.ecommerce.DTOs.Product.ProductCreateDto;
import com.ecommerce.ecommerce.DTOs.Product.ProductDto;
import com.ecommerce.ecommerce.DTOs.ResponseDto;
import com.ecommerce.ecommerce.Exceptions.NotEnoughProduct;
import com.ecommerce.ecommerce.Exceptions.ProductNotExistException;
import com.ecommerce.ecommerce.Models.Product;
import com.ecommerce.ecommerce.Repositories.ProductRepository;
import com.ecommerce.ecommerce.Response.MessageStrings;
import com.ecommerce.ecommerce.Response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ecommerce.ecommerce.Response.MessageStrings.*;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartService cartService;
    public List<ProductDto> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto productDto = getDtoFromProduct(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }
    public static ProductDto getDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto(product);
        return productDto;
    }
    public static Product getProductFromDto(ProductDto productDto) {
        Product product = new Product(productDto);
        return product;
    }
    public ProductDto addProduct(ProductCreateDto productCreateDto) {
            Product product = new Product(productCreateDto);
            productRepository.save(product);
            ProductDto productDto=getDtoFromProduct(product);
            return productDto;
    }
    public ResponseDto updateProduct(ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
        try{
            if (!optionalProduct.isPresent()){
                throw new ProductNotExistException(MessageStrings.PRODUCT_NOT_EXIST);
            }
            Product product = getProductFromDto(productDto);
            product.setId(productDto.getId());
            productRepository.save(product);
            return new ResponseDto(ResponseStatus.success.toString(), PRODUCT_UPDATE);
        }
        catch (ProductNotExistException e){
            return new ResponseDto(ResponseStatus.error.toString(), PRODUCT_NOT_EXIST);
        }
    }
    public Product getProductById(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new ProductNotExistException("id " + productId);
        return optionalProduct.get();
    }
    public void deleteById(int id){
        Product product=productRepository.findById(id);
        cartService.deleteByProduct(product);
        productRepository.deleteById(id);
    }
    public void stolen(Product product,int itemNumber){
        int newStock=product.getStock()-itemNumber;
        product.setStock(newStock);
    }
    public void deletedCart(Product oldProduct, int cartQuantity){
        int newStock=oldProduct.getStock()+cartQuantity;
        oldProduct.setStock(newStock);

    }
    public void updateStock(Product oldProduct,Product newProduct,int oldQuantity,int newQuantity){
        if(oldProduct.getId()==newProduct.getId()){
            if(oldQuantity<=newQuantity){
                int difference=newQuantity-oldQuantity;
                if(newProduct.getStock()<difference){
                    throw new NotEnoughProduct(NOT_ENOUGH_PRODUCT);
                }
                else{
                    int New=newProduct.getStock()-difference;
                    newProduct.setStock(New);
                }
            }
            else{
                int difference=oldQuantity-newQuantity;
                int New=newProduct.getStock()+difference;
                newProduct.setStock(New);
            }
        }
       else{
           oldProduct.setStock(oldProduct.getStock()+oldQuantity);
           if(newProduct.getStock()<=newQuantity){
               throw new NotEnoughProduct(NOT_ENOUGH_PRODUCT);

           }
           else{
               stolen(newProduct,newQuantity);
           }

        }
    }
}