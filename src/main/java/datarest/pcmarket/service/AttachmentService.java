package datarest.pcmarket.service;

import datarest.pcmarket.entity.Attachment;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    public Attachment getAttachmentService(Integer id){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        return optionalAttachment.orElse(null);
    }
    public Result addAttachmentService(Attachment attachment){
        attachmentRepository.save(attachment);
        return new Result("rasim yuklandi.",true);
    }
    public Result editAttachmentService(Attachment attachment,Integer id){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) return new Result("Bunday rasim mavjud emas.",false);
        Attachment attachment1 = optionalAttachment.get();
        attachment1.setName(attachment.getName());
        attachment1.setSize(attachment.getSize());
        attachment1.setContent_type(attachment.getContent_type());
        attachmentRepository.save(attachment1);
        return new Result("Rasm yuklandin.",true);
    }
    public Result deleteAttachment(Integer id){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) return new Result("Bunday rasim mavjud eams",false);
        attachmentRepository.deleteById(id);
        return new Result("Rasm o'chirildi.",true);
    }
}
