package datarest.pcmarket.controller;

import datarest.pcmarket.entity.Order;
import datarest.pcmarket.payload.OrderDto;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping
    public HttpEntity<?> getOrders(){
        List<Order> orders = orderService.getOrdersService();
        return ResponseEntity.status(!orders.isEmpty()?200:409).body(orders);
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOrder(@PathVariable Integer id){
        Order order = orderService.getOrder(id);
        return ResponseEntity.status(order!=null?200:409).body(order);
    }

    @PostMapping("/add")
    public HttpEntity<?> addOrder(OrderDto orderDto){
        Result result = orderService.addOrderService(orderDto);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editOrder(@RequestBody OrderDto orderDto,@PathVariable Integer id){
        Result result = orderService.editOrderService(orderDto, id);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteOrder(@PathVariable Integer id){
        Result result = orderService.deleteOrder(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
}
