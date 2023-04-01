package datarest.pcmarket.service;

import datarest.pcmarket.entity.Attachment;
import datarest.pcmarket.entity.Blog;
import datarest.pcmarket.entity.Product;
import datarest.pcmarket.entity.ProductInfo;
import datarest.pcmarket.payload.BlogDto;
import datarest.pcmarket.payload.ProductInfoDto;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.repository.AttachmentRepository;
import datarest.pcmarket.repository.BlogRepository;
import datarest.pcmarket.repository.ProductInfoRepository;
import datarest.pcmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInfoService {
    @Autowired
    ProductInfoRepository productInfoRepository;
    @Autowired
    ProductRepository productRepository;

    public List<ProductInfo> getProductInfosService(){
        return productInfoRepository.findAll();
    }
    public ProductInfo getProductInfoService(Integer id){
        Optional<ProductInfo> optionalProductInfo = productInfoRepository.findById(id);
        return optionalProductInfo.orElse(null);
    }
    public Result addProductInfoService(ProductInfoDto productInfoDto){
        Optional<Product> optionalProduct = productRepository.findById(productInfoDto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Product topilmadi.",false);
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProduct(optionalProduct.get());
        productInfo.setBody(productInfoDto.getBody());
        productInfoRepository.save(productInfo);
        return new Result("Product xarakteristika qo'shildi.",true);
    }
    public Result editBlogService(ProductInfoDto productInfoDto,Integer id){
        Optional<ProductInfo> optionalProductInfo = productInfoRepository.findById(id);
        if (!optionalProductInfo.isPresent()) return new Result("Product info topilmadi.",false);
        ProductInfo productInfo = optionalProductInfo.get();
        productInfo.setBody(productInfoDto.getBody());

        Optional<Product> optionalProduct = productRepository.findById(productInfoDto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Product topulmadi.",false);
        productInfo.setProduct(optionalProduct.get());
        productInfoRepository.save(productInfo);
        return new Result("Product ma'lumotlari yangilandi.",true);
    }
    public Result deleteProductInfoService(Integer id){
        Optional<ProductInfo> optionalProductInfo = productInfoRepository.findById(id);
        if (!optionalProductInfo.isPresent()) return new Result("Bunday blog mavjud emas.",false);
        productInfoRepository.deleteById(id);
        return new Result("Product malumoti o'chirildi.",true);
    }
}
