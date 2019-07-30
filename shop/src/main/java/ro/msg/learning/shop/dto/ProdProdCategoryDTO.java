package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdProdCategoryDTO extends BaseDTO {
    private static final long serialVersionUID = -2115255161025891876L;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private Long category;
    private Long supplier;
    private String imageURL;
    private String categoryName;
    private String categoryDescription;

    public ProdProdCategoryDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        weight = product.getWeight();
        category = product.getCategory().getId();
        supplier = product.getSupplier().getId();
        imageURL = product.getImageURL();
        categoryName = product.getCategory().getName();
        categoryDescription = product.getCategory().getDescription();
    }

    public Product getProduct(ProductCategory category, Supplier supplier) {
        return Product.builder().name(name)
                .description(description).price(price).weight(weight).imageURL(imageURL).supplier(supplier).category(category).build();
    }
}
