package datarest.pcmarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reviews {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Product product;
    private String body;
    private Integer mark;
    private String username;
    private String email;
    private String code;
    @ManyToOne
    private Attachment attachment;
}
