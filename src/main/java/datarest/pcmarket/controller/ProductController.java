package datarest.pcmarket.controller;

import datarest.pcmarket.entity.Product;
import datarest.pcmarket.payload.ProductDto;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @GetMapping
    public HttpEntity<?> getProducts(){
        List<Product> products = productService.getProductsService();
        return ResponseEntity.status(!products.isEmpty()?200:409).body(products);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @GetMapping("/{id}")
    public HttpEntity<?> getProduct(@PathVariable Integer id){
        Product product = productService.getProductService(id);
        return ResponseEntity.status(product!=null?201:409).body(product);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping("/add")
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto,@RequestBody MultipartFile file){
        Result result = productService.addProductService(productDto);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editProduct(@PathVariable Integer id,@RequestBody ProductDto productDto){
        Result result = productService.editProducService(productDto, id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Integer id){
        Result result = productService.deleteProductService(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
}
