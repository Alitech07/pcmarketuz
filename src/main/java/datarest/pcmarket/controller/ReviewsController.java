package datarest.pcmarket.controller;

import datarest.pcmarket.entity.Brend;
import datarest.pcmarket.entity.Reviews;
import datarest.pcmarket.payload.BrendDto;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.payload.ReviewsDto;
import datarest.pcmarket.service.BrendService;
import datarest.pcmarket.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {
    @Autowired
    ReviewsService reviewsService;

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping
    public HttpEntity<?> getReviewss(){
        List<Reviews> reviews = reviewsService.getAllReviewsService();
        return ResponseEntity.status(reviews.isEmpty()?409:200).body(reviews);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getReviews(@PathVariable Integer id){
        Reviews reviews = reviewsService.getReviewsService(id);
        return ResponseEntity.status(reviews!=null?200:409).body(reviews);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addReviews(@RequestBody ReviewsDto reviewsDto){
        Result result = reviewsService.addReviewsService(reviewsDto);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editReviews(@RequestBody ReviewsDto reviewsDto,@PathVariable Integer id){
        Result result = reviewsService.editReviewsService(reviewsDto,id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteReviews(@PathVariable Integer id){
        Result result = reviewsService.deleteReviewsService(id);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }
}
