package datarest.pcmarket.service;

import datarest.pcmarket.entity.Order;
import datarest.pcmarket.entity.OrderProduct;
import datarest.pcmarket.entity.Product;
import datarest.pcmarket.payload.OrderProductDto;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.repository.OrderProductRepository;
import datarest.pcmarket.repository.OrderRepository;
import datarest.pcmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderProductService {
    @Autowired
    OrderProductRepository orderProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;

    public List<OrderProduct> getOrderProductsService(){
        return orderProductRepository.findAll();
    }
    public OrderProduct getOrderProductService(Integer id){
        Optional<OrderProduct> optionalOrderProduct = orderProductRepository.findById(id);
        return optionalOrderProduct.orElse(null);
    }
    public Result addOrderProduct(OrderProductDto orderProductDto){
        Optional<Order> optionalOrder = orderRepository.findById(orderProductDto.getOrderId());
        if (!optionalOrder.isPresent()) return new Result("Buyurtma berilmagan",false);
        Optional<Product> optionalProduct = productRepository.findById(orderProductDto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Bundya product mavjud eams.",false);
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(optionalOrder.get());
        Product product = optionalProduct.get();
        orderProduct.setProduct(product);
        orderProduct.setAmount(orderProductDto.getAmount());
        double total_price = orderProductDto.getAmount()*product.getPrice();//umumiy narx
        orderProduct.setTotalPrice(total_price);
        orderProductRepository.save(orderProduct);
        return new Result("Product xxarid qilindi.",true);
    }
    public Result editOrderProductService(OrderProductDto orderProductDto,Integer id){
        Optional<OrderProduct> optionalOrderProduct = orderProductRepository.findById(id);
        OrderProduct orderProduct = optionalOrderProduct.get();

        Optional<Order> optionalOrder = orderRepository.findById(orderProductDto.getOrderId());
        if (!optionalOrder.isPresent()) return new Result("Buyurtma berilmagan",false);

        Optional<Product> optionalProduct = productRepository.findById(orderProductDto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Bundya product mavjud eams.",false);

        orderProduct.setOrder(optionalOrder.get());
        Product product = optionalProduct.get();
        orderProduct.setProduct(product);
        orderProduct.setAmount(orderProductDto.getAmount());
        double total_price = orderProductDto.getAmount()*product.getPrice();//umumiy narx
        orderProduct.setTotalPrice(total_price);
        orderProductRepository.save(orderProduct);
        return new Result("Product xaridlari ma'lumotlari yangilandi.",true);
    }
    public Result deleteOrderProductService(Integer id){
        Optional<OrderProduct> optionalOrderProduct = orderProductRepository.findById(id);
        if (optionalOrderProduct.isPresent()) return new Result("Bunday mahsulot olmagansiz.",false);
        orderProductRepository.deleteById(id);
        return new Result("O'chirildi.",true);
    }
}
