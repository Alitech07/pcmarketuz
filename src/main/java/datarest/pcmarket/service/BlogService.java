package datarest.pcmarket.service;

import datarest.pcmarket.entity.Attachment;
import datarest.pcmarket.entity.Blog;
import datarest.pcmarket.entity.Brend;
import datarest.pcmarket.payload.BlogDto;
import datarest.pcmarket.payload.BrendDto;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.repository.AttachmentRepository;
import datarest.pcmarket.repository.BlogRepository;
import datarest.pcmarket.repository.BrendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    public List<Blog> getBlogsService(){
        return blogRepository.findAll();
    }
    public Blog getBlogService(Integer id){
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        return optionalBlog.orElse(null);
    }
    public Result addBlogService(BlogDto blogDto){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(blogDto.getPhotoId());
        if (!optionalAttachment.isPresent()) return new Result("Blog photosi topilmadi.",false);
        Blog blog = new Blog();
        blog.setAttachment(optionalAttachment.get());
        blog.setTitle(blogDto.getTitle());
        blog.setBody(blogDto.getBody());
        return new Result("Blog qo'shildi.",true);
    }
    public Result editBlogService(BlogDto blogDto,Integer id){
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        if (!optionalBlog.isPresent()) return new Result("Blog topilmadi.",false);
        Blog blog = optionalBlog.get();
        blog.setTitle(blogDto.getTitle());
        blog.setBody(blogDto.getBody());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(blogDto.getPhotoId());
        if (!optionalAttachment.isPresent()) return new Result("Photo topulmadi.",false);
        blog.setAttachment(optionalAttachment.get());
        blogRepository.save(blog);
        return new Result("Blog ma'lumotlari yangilandi.",true);
    }
    public Result deleteBlogService(Integer id){
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        if (!optionalBlog.isPresent()) return new Result("Bunday blog mavjud emas.",false);
        blogRepository.deleteById(id);
        return new Result("Blog o'chirildi.",true);
    }
}
