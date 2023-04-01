package datarest.pcmarket.service;

import datarest.pcmarket.entity.Attachment;
import datarest.pcmarket.entity.Product;
import datarest.pcmarket.entity.Reviews;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.payload.ReviewsDto;
import datarest.pcmarket.repository.AttachmentRepository;
import datarest.pcmarket.repository.ProductRepository;
import datarest.pcmarket.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsService {
    @Autowired
    ReviewsRepository reviewsRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    public List<Reviews> getAllReviewsService(){
        return reviewsRepository.findAll();
    }
    public Reviews getReviewsService(Integer id){
        Optional<Reviews> optionalReviews = reviewsRepository.findById(id);
        return optionalReviews.orElse(null);
    }
    public Result addReviewsService(ReviewsDto reviewsDto){
        Optional<Product> optionalProduct = productRepository.findById(reviewsDto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Bunday product yuq",false);
        Reviews reviews = new Reviews();
        reviews.setProduct(optionalProduct.get());
        reviews.setUsername(reviewsDto.getUsername());
        reviews.setMark(reviewsDto.getMark());
        reviews.setBody(reviewsDto.getBody());
        reviews.setEmail(reviewsDto.getEmail());
        reviews.setCode(reviewsDto.getCode());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(reviewsDto.getAttachmentId());
        reviews.setAttachment(optionalAttachment.get());
        reviewsRepository.save(reviews);
        return new Result("Izoh qo'shildi.",true);
    }
    public Result editReviewsService(ReviewsDto reviewsDto,Integer id){
        Optional<Reviews> optionalReviews = reviewsRepository.findById(id);
        if (!optionalReviews.isPresent()) return new Result("Bunday Izoh mavjud emas",false);
        Reviews reviews = optionalReviews.get();
        Optional<Product> optionalProduct = productRepository.findById(reviewsDto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Bunday product yuq",false);
        reviews.setProduct(optionalProduct.get());
        reviews.setUsername(reviewsDto.getUsername());
        reviews.setMark(reviewsDto.getMark());
        reviews.setBody(reviewsDto.getBody());
        reviews.setEmail(reviewsDto.getEmail());
        reviews.setCode(reviewsDto.getCode());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(reviewsDto.getAttachmentId());
        reviews.setAttachment(optionalAttachment.get());
        reviewsRepository.save(reviews);
        return new Result("Izoh yangilandi.",true);
    }
    public Result deleteReviewsService(Integer id){
        Optional<Reviews> optionalReviews = reviewsRepository.findById(id);
        if (!optionalReviews.isPresent()) return new Result("Bunday izoh bildirilmagan",false);
        reviewsRepository.deleteById(id);
        return new Result("izoh o'chirildi.",true);
    }
}
