package datarest.pcmarket.controller;

import datarest.pcmarket.entity.ProductInfo;
import datarest.pcmarket.payload.ProductInfoDto;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productinfo")
public class ProductInfoController {
    @Autowired
    ProductInfoService productInfoService;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @GetMapping
    public HttpEntity<?> getProductInfos(){
        List<ProductInfo> productInfos = productInfoService.getProductInfosService();
        return ResponseEntity.status(!productInfos.isEmpty()?200:409).body(productInfos);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getProductInfo(@PathVariable Integer id){
        ProductInfo productInfo = productInfoService.getProductInfoService(id);
        return ResponseEntity.status(productInfo!=null?200:409).body(productInfo);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping("/add")
    public HttpEntity<?> addProductInfo(@RequestBody ProductInfoDto productInfoDto){
        Result result = productInfoService.addProductInfoService(productInfoDto);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editProductInfo(@PathVariable Integer id,@RequestBody ProductInfoDto productInfoDto){
        Result result = productInfoService.editBlogService(productInfoDto, id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Integer id){
        Result result = productInfoService.deleteProductInfoService(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
}
