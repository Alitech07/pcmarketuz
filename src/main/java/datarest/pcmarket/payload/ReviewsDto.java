package datarest.pcmarket.payload;

import datarest.pcmarket.entity.Attachment;
import datarest.pcmarket.entity.Product;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsDto {
    private Integer productId;
    private String body;
    private Integer mark;
    private String username;
    private String email;
    private String code;
    private Integer attachmentId;
}
