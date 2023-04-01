package datarest.pcmarket.service;

import datarest.pcmarket.entity.Order;
import datarest.pcmarket.payload.OrderDto;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.repository.OrderRepository;
import datarest.pcmarket.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;


    public List<Order> getOrdersService(){
        return orderRepository.findAll();
    }
    public Order getOrder(Integer id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }
    public Result addOrderService(@Valid OrderDto orderDto){
        Order order = new Order();
        order.setName(orderDto.getName());
        order.setEmail(orderDto.getEmail());
        order.setPhone_number(orderDto.getPhone_number());
        order.setAddress(orderDto.getAddress());
        order.setComment(orderDto.getComment());
        order.setCode(UUID.randomUUID().toString());
        order.setDateTime(LocalDateTime.now());
        orderRepository.save(order);
        return new Result("Buyurtma tasdiqlandi.",true);
    }
    public Result editOrderService(OrderDto orderDto,Integer id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) return new Result("Bunday buyurma mavjud emas.",false);
        Order order = optionalOrder.get();
        order.setName(orderDto.getName());
        order.setEmail(orderDto.getEmail());
        order.setPhone_number(orderDto.getPhone_number());
        order.setAddress(orderDto.getAddress());
        order.setComment(orderDto.getComment());
        order.setDateTime(LocalDateTime.now());
        orderRepository.save(order);
        return new Result("Buyurtma tasdiqlandi.",true);
    }
    public Result deleteOrder(Integer id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) return new Result("Bunday order mavjud.",false);
        orderRepository.deleteById(id);
        return new Result("Order ochirildi.",true);
    }
}
