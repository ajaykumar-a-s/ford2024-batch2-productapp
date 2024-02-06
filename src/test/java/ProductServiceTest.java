import dto.Product;
import exception.ProductException;
import exception.UpdateProductException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ProductService;
import service.ProductServiceImpl;

import java.util.Collection;

public class ProductServiceTest {

//    //Create
//    Product addProduct(Product newProduct)throws ProductException;
//    //Read
//    Product getProductById(Integer id)throws ProductException;
//    //Update
//    Product updateProduct(Product product)throws UpdateProductException;
//    // Delete
//    Boolean deleteProdctById(Integer id)throws ProductException;
//    // get all products
//    Collection<Product> getAllProducts();

//
ProductService dummyProductService = new ProductServiceImpl();



    @BeforeAll // test data preparation
    public static void beforeAllTestCases(){

    }

    //+ve
    @Test

    

    @DisplayName("Adding new Product Test & @#$% :)")
    public void addProductTest(){
        ProductService productService = new ProductServiceImpl();
        try {
            Assertions.assertNotNull(productService.addProduct(new Product(1, "iPhone", 55000.0)));
        } catch (ProductException e) {
            e.printStackTrace();
        }

    }

    //-ve
    @Test
    public void ShouldThrowExceptionWhenAddProductReceivesNullTest() {
        ProductService productService = new ProductServiceImpl();

        Assertions.assertThrows(ProductException.class, () -> productService.addProduct(null));
    }

    @Test
    public void shouldSendExceptionMessageAddProduct() {
        ProductService productService = new ProductServiceImpl();

        try {
            Assertions.assertThrows(ProductException.class, () -> productService.addProduct(null));
            productService.addProduct(null);
        } catch (ProductException e) {

            Assertions.assertEquals("New Product cant be null.", e.getMessage());
        }
    }

    @Test
    public void AddProductShouldThrowExceptionIfProductAleradyExists() {
        ProductService productService = new ProductServiceImpl();
        try {
            Assertions.assertNotNull(
                    productService.addProduct(new Product(44, "Mouse", 1250.0)));
        } catch (ProductException e) {
            e.printStackTrace();
        }

        try {
            Assertions.assertThrows(ProductException.class, () -> productService.addProduct(new Product(44, "Mouse", 1250.0)));
            productService.addProduct(new Product(44, "Mouse", 1250.0));
        } catch (ProductException e) {

            Assertions.assertEquals("Product already exists!", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenGivenInvalidId() {
        ProductService productService = new ProductServiceImpl();
        Assertions.assertThrows(ProductException.class, () -> productService.getProductById(0));
    }

    @Test
    public void shouldSendExceptionMessageInvalidId() {
        ProductService productService = new ProductServiceImpl();
        try {
            productService.getProductById(0);
        } catch (ProductException e) {
            Assertions.assertEquals("Product Not Found", e.getMessage());
        }
    }



    @Test
    public void updateProductShouldThrowExceptionIfUnavailableId() {
        ProductService productService = new ProductServiceImpl();
        Assertions.assertThrows(UpdateProductException.class, () -> productService.updateProduct(new Product(44, "Mouse", 1250.0)));

    }

    @Test
    public void updateProductShouldThrowExceptionMessageForUnavailableId(){
        ProductService productService = new ProductServiceImpl();
        try {
            productService.updateProduct(new Product(44, "Mouse", 1250.0));
        } catch (UpdateProductException e) {
            Assertions.assertEquals("Unavailable ID", e.getMessage());
        }
    }

    @Test
    public void deleteProductShouldThrowExceptionForUnavailableId(){
        ProductService productService = new ProductServiceImpl();
        Assertions.assertThrows(ProductException.class, ()->productService.deleteProdctById(0));
    }

    @Test
    public void deleteProductShouldThrowExceptionMessageForUnavailableId(){
        ProductService productService = new ProductServiceImpl();
        try {
            productService.deleteProdctById(0);
        } catch (ProductException e) {
            Assertions.assertEquals("Unavailable ID", e.getMessage());
        }
    }
}
