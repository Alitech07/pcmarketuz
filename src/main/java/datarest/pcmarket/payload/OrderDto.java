package datarest.pcmarket.payload;

import datarest.pcmarket.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false,unique = true)
    private String phone_number;
    @Column(nullable = false)
    private String email;
    private String comment;
}
