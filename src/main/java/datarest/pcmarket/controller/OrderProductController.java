package datarest.pcmarket.controller;

import datarest.pcmarket.entity.OrderProduct;
import datarest.pcmarket.entity.Product;
import datarest.pcmarket.payload.OrderProductDto;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.repository.OrderProductRepository;
import datarest.pcmarket.repository.ProductRepository;
import datarest.pcmarket.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order/product")
public class OrderProductController {

    @Autowired
    OrderProductService orderProductService;

    @GetMapping
    public HttpEntity<?> getOrderProducts(){
        List<OrderProduct> orderProducts = orderProductService.getOrderProductsService();
        return ResponseEntity.status(orderProducts.isEmpty()?409:200).body(orderProducts);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOrderProduct(@PathVariable Integer id){
        OrderProduct orderProduct = orderProductService.getOrderProductService(id);
        return ResponseEntity.status(orderProduct!=null?200:409).body("Bunday product savatda mavjud emas");
    }
    @PostMapping("/add")
    public HttpEntity<?> addOrderProduct(@RequestBody OrderProductDto orderProductDto){
        Result result = orderProductService.addOrderProduct(orderProductDto);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @PutMapping("/{id}")
    public HttpEntity<?> editBasket(@PathVariable Integer id,@RequestBody OrderProductDto orderProductDto){
        Result result = orderProductService.editOrderProductService(orderProductDto, id);
        return ResponseEntity.status(result.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteBasket(@PathVariable Integer id){
        Result result = orderProductService.deleteOrderProductService(id);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }
}
