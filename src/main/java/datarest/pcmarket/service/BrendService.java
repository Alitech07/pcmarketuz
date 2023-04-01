package datarest.pcmarket.service;

import datarest.pcmarket.entity.Attachment;
import datarest.pcmarket.entity.Brend;
import datarest.pcmarket.payload.BrendDto;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.repository.AttachmentRepository;
import datarest.pcmarket.repository.BrendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrendService {
    @Autowired
    BrendRepository brendRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    public List<Brend> getBrendsService(){
        return brendRepository.findAll();
    }
    public Brend getBrendService(Integer id){
        Optional<Brend> optionalBrend = brendRepository.findById(id);
        return optionalBrend.orElse(null);
    }
    public Result addBrendService(BrendDto brendDto){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(brendDto.getPhotoId());
        if (!optionalAttachment.isPresent()) return new Result("Brend photosi topilmadi.",false);
        Brend brend = new Brend();
        brend.setAttachment(optionalAttachment.get());
        brend.setName(brendDto.getName());
        brend.setDescription(brendDto.getDescription());
        return new Result("Brend qo'shildi.",true);
    }
    public Result editBrendService(BrendDto brendDto,Integer id){
        Optional<Brend> optionalBrend = brendRepository.findById(id);
        if (!optionalBrend.isPresent()) return new Result("brend topilmadi.",false);
        Brend brend = optionalBrend.get();
        brend.setName(brendDto.getName());
        brend.setDescription(brendDto.getDescription());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(brendDto.getPhotoId());
        if (!optionalAttachment.isPresent()) return new Result("Photo topulmadi.",false);
        brend.setAttachment(optionalAttachment.get());
        brendRepository.save(brend);
        return new Result("Brend ma'lumotlari yangilandi.",true);
    }
    public Result deleteBrendService(Integer id){
        Optional<Brend> optionalBrend = brendRepository.findById(id);
        if (!optionalBrend.isPresent()) return new Result("Bunday brend mavjud emas.",false);
        brendRepository.deleteById(id);
        return new Result("Brend o'chirildi.",true);
    }
}
