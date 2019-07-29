package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.converter.ProductResourceAssembler;
import ro.msg.learning.shop.dto.ProdProdCategoryDTO;
import ro.msg.learning.shop.service.ProductService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private ProductResourceAssembler resourceAssembler;

    @GetMapping("/products")
    public List<ProdProdCategoryDTO> readAllProducts() {
        return productService.readAll();
    }

    @GetMapping("/products/{id}")
    public ProdProdCategoryDTO readProduct(@PathVariable Long id) {
        return productService.readById(id);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/products")
    public ResponseEntity createProduct(@RequestBody ProdProdCategoryDTO prodProdCategoryDTO) throws URISyntaxException {
        Resource<ProdProdCategoryDTO> resource = resourceAssembler.toResource(productService.create(prodProdCategoryDTO));

        return ResponseEntity.created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @PutMapping("/products")
    public ResponseEntity updateProduct(@RequestBody ProdProdCategoryDTO prodProdCategoryDTO) throws URISyntaxException {
        ProdProdCategoryDTO updatedProduct = productService.update(prodProdCategoryDTO.getId(), prodProdCategoryDTO);

        Resource<ProdProdCategoryDTO> resource = resourceAssembler.toResource(updatedProduct);

        return ResponseEntity.created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }
}
