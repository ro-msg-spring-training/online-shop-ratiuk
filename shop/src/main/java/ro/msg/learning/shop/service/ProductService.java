package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProdProdCategoryDTO;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.IProductCategoryRepository;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.ISupplierRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final IProductRepository productRepository;
    private final IProductCategoryRepository productCategoryRepository;
    private final ISupplierRepository supplierRepository;

    public ProdProdCategoryDTO create(ProdProdCategoryDTO prodProdCategoryDTO) {

        ProductCategory category = productCategoryRepository.findById(prodProdCategoryDTO.getCategory()).get();
        Supplier supplier = supplierRepository.findById(prodProdCategoryDTO.getSupplier()).get();

        Product product = productRepository.save(prodProdCategoryDTO.getProduct(category, supplier));
        prodProdCategoryDTO.setId(product.getId());

        return prodProdCategoryDTO;
    }

    @Transactional
    public ProdProdCategoryDTO update(Long id, ProdProdCategoryDTO prodProdCategoryDTO) {
        ProductCategory category = productCategoryRepository.findById(prodProdCategoryDTO.getCategory()).get();
        Supplier supplier = supplierRepository.findById(prodProdCategoryDTO.getSupplier()).get();
        productRepository.findById(id).ifPresent( product -> prodProdCategoryDTO.getProduct(category, supplier));

        return prodProdCategoryDTO;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProdProdCategoryDTO> readAll() {
        Iterable<Product> products = productRepository.findAll();
        List<ProdProdCategoryDTO> dtoList = new ArrayList<>();

        products.forEach(product -> dtoList.add(new ProdProdCategoryDTO(product)));
        return dtoList;
    }

    public ProdProdCategoryDTO readById(Long id) {
        return new ProdProdCategoryDTO(productRepository.findById(id).get());
    }

}
