package datarest.pcmarket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDto {
    private Integer orderId;
    private Integer productId;
    private Integer amount;
}
