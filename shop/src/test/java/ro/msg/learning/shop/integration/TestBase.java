package ro.msg.learning.shop.integration;

import ro.msg.learning.shop.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestBase {

    Customer customer = Customer.builder().firstName("FN").lastName("LN").emailAddress("fn.ln@yahoo.com").password("test").username("user").build();

    ProductCategory productCategory = ProductCategory.builder().name("furniture").description("products in it").build();

    Supplier supplier = Supplier.builder().name("Ikea").build();

    Product product = Product.builder().name("Dining table").description("Beautiful table :)").price(BigDecimal.valueOf(129.99)).weight(12.5).imageURL("forget the URL").category(productCategory).supplier(supplier).build();

    Product product2 = Product.builder().name("Dining table 2").description("Beautiful table :)").price(BigDecimal.valueOf(149.99)).weight(18.5).imageURL("forget the URL").category(productCategory).supplier(supplier).build();

    Address address = Address.builder().addressCountry("Germany").addressCounty("Bayern").addressCity("Munchen").addressStreet("Neuburger 22").build();

    Location location = Location.builder().name("MUC").address(address).build();

    Location location2 = Location.builder().name("MUC2").address(address).build();

    Stock stock = Stock.builder().location(location).product(product).quantity(10).build();

    Stock stock2 = Stock.builder().location(location2).product(product).quantity(100).build();

    Stock stock3 = Stock.builder().location(location).product(product2).quantity(10).build();

    List<Stock> stocks = Stream.of(stock, stock2, stock3).collect(Collectors.toList());

    List<Stock> stocksProd = Stream.of(stock2, stock).collect(Collectors.toList());

    List<Stock> stocksProdBigStock = Stream.of(stock2).collect(Collectors.toList());

    List<Stock> stocksProd2 = Stream.of(stock3).collect(Collectors.toList());

}
