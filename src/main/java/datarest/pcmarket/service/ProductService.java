package datarest.pcmarket.service;

import datarest.pcmarket.entity.Attachment;
import datarest.pcmarket.entity.Brend;
import datarest.pcmarket.entity.Category;
import datarest.pcmarket.entity.Product;
import datarest.pcmarket.payload.ProductDto;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.repository.AttachmentRepository;
import datarest.pcmarket.repository.BrendRepository;
import datarest.pcmarket.repository.CategoryRepository;
import datarest.pcmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BrendRepository brendRepository;

    public List<Product> getProductsService(){
        return productRepository.findAll();
    }
    public Product getProductService(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }
    public Result addProductService(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (!optionalAttachment.isPresent()) return new Result("Mahsulot rasmi yuklanmadi.",false);
        product.setAttachment(optionalAttachment.get());

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new Result("Bundya category mavjud emas!",false);
        product.setCategory(optionalCategory.get());

        Optional<Brend> optionalBrend = brendRepository.findById(productDto.getBrendId());
        if (!optionalBrend.isPresent()) return new Result("Bunday brend mavjud emas!",false);
        product.setBrend(optionalBrend.get());

        productRepository.save(product);
        return new Result("Yangi product qo'shildi.",true);
    }
    public Result editProducService(ProductDto productDto,Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return new Result("Bundya product mavjud emas!",false);
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (!optionalAttachment.isPresent()) return new Result("Mahsulot rasmi kiritolmadi.!",false);
        product.setAttachment(optionalAttachment.get());

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new Result("Bunday kategoriya mavjud emas!",false);
        product.setCategory(optionalCategory.get());

        Optional<Brend> optionalBrend = brendRepository.findById(productDto.getBrendId());
        if (!optionalBrend.isPresent()) return new Result("Mavjud bo'lmagan brend tanlandi!",false);
        product.setBrend(optionalBrend.get());

        productRepository.save(product);
        return new Result("Product ma'lumotlari yangilandi.",true);
    }
    public Result deleteProductService(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return new Result("Bunday product mavjud emas!",false);
        productRepository.deleteById(id);
        return new Result("Product o'chirildi.",false);
    }
}
