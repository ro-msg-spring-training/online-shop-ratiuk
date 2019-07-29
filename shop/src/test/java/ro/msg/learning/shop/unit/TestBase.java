package ro.msg.learning.shop.unit;

import ro.msg.learning.shop.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestBase {

    protected ProductCategory productCategory = ProductCategory.builder().name("furniture").description("products in it").build();

    protected Supplier supplier = Supplier.builder().name("Ikea").build();

    protected Product product = Product.builder().name("Dining table").description("Beautiful table :)").price(BigDecimal.valueOf(129.99)).weight(12.5).imageURL("forget the URL").category(productCategory).supplier(supplier).build();

    protected Product product2 = Product.builder().name("Dining table 2").description("Beautiful table :)").price(BigDecimal.valueOf(149.99)).weight(18.5).imageURL("forget the URL").category(productCategory).supplier(supplier).build();

    protected Address address = Address.builder().addressCountry("Germany").addressCounty("Bayern").addressCity("Munchen").addressStreet("Neuburger 22").build();

    protected Location location = Location.builder().name("MUC").address(address).build();

    protected Location location2 = Location.builder().name("MUC2").address(address).build();

    protected Stock stock = Stock.builder().location(location).product(product).quantity(10).build();

    protected Stock stock2 = Stock.builder().location(location2).product(product).quantity(100).build();

    protected Stock stock3 = Stock.builder().location(location).product(product2).quantity(10).build();

    protected List<Stock> stocks = Stream.of(stock, stock2, stock3).collect(Collectors.toList());

    protected List<Stock> stocksProd = Stream.of(stock2, stock).collect(Collectors.toList());

    protected List<Stock> stocksProdBigStock = Stream.of(stock2).collect(Collectors.toList());

    protected List<Stock> stocksProd2 = Stream.of(stock3).collect(Collectors.toList());


     void setIds() {
         productCategory.setId(1L);
         supplier.setId(1L);
         product.setId(1L);
         product2.setId(2L);
         address.setId(1L);
         location.setId(1L);
         location2.setId(2L);
         stock.setId(1L);
         stock2.setId(2L);
         stock3.setId(3L);
    }
}
