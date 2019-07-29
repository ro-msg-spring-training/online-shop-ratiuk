package ro.msg.learning.shop.converter;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.controller.ProductController;
import ro.msg.learning.shop.dto.ProdProdCategoryDTO;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ProductResourceAssembler implements ResourceAssembler<ProdProdCategoryDTO, Resource<ProdProdCategoryDTO>> {

    @Override
    public Resource<ProdProdCategoryDTO> toResource(ProdProdCategoryDTO prodProdCategoryDTO) {
        return new Resource<>(prodProdCategoryDTO,
                linkTo(methodOn(ProductController.class).readProduct(prodProdCategoryDTO.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).readAllProducts()).withRel("products"));
    }
}
