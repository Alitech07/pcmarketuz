package datarest.pcmarket.payload;

import datarest.pcmarket.entity.Attachment;
import datarest.pcmarket.entity.Brend;
import datarest.pcmarket.entity.Category;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer attachmentId;
    private String name;
    private double price;
    private Integer categoryId;
    private Integer brendId;
    private String description;
}
