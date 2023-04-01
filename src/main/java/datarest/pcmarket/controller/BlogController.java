package datarest.pcmarket.controller;

import datarest.pcmarket.entity.Blog;
import datarest.pcmarket.payload.BlogDto;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping
    public HttpEntity<?> getBlogs(){
        List<Blog> blogs = blogService.getBlogsService();
        return ResponseEntity.status(blogs.isEmpty()?409:200).body(blogs);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getBlog(@PathVariable Integer id){
        Blog blog = blogService.getBlogService(id);
        return ResponseEntity.status(blog!=null?200:409).body(blog);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addBlog(@RequestBody BlogDto blogDto){
        Result result = blogService.addBlogService(blogDto);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editBlog(@RequestBody BlogDto blogDto,@PathVariable Integer id){
        Result result = blogService.editBlogService(blogDto, id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteBlog(@PathVariable Integer id){
        Result result = blogService.deleteBlogService(id);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }
}
