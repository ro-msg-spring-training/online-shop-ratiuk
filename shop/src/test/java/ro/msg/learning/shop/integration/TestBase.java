package ro.msg.learning.shop.integration;

import ro.msg.learning.shop.model.*;

import java.math.BigDecimal;

public class TestBase {

    protected Customer customer = Customer.builder().firstName("FN").lastName("LN").emailAddress("fn.ln@yahoo.com").password("test").username("user").build();

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

}
