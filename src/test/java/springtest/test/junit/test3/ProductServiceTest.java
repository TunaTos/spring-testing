package springtest.test.junit.test3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import springtest.test.junit.test3.Product;
import springtest.test.junit.test3.ProductService;

import static org.junit.jupiter.api.Assertions.*;


class ProductServiceTest {

    static ProductService productService;
    Product product;

    @BeforeAll
    static void initProductService() {
        productService = new ProductService();
    }

    @BeforeEach
    void setProduct() {
        product = new Product("name", 11);
    }

    @ParameterizedTest
    @DisplayName("ValueSource test")
    @ValueSource(strings = {"사과", "바나나", "포도"})
    void test1(String arg) {

        assertTrue(productService.isValidName(arg));
    }

    @ParameterizedTest
    @DisplayName("ValueSource test for Ints")
    @ValueSource(ints = {1, 10000, 99999})
    void test2(Integer arg) {
        assertTrue(productService.isValidPrice(arg));
    }

    @ParameterizedTest
    @DisplayName("ValueSource test for null and empty")
    @NullAndEmptySource
    void test3(String arg) {
        assertFalse(productService.isValidName(arg));
    }

    @ParameterizedTest
    @DisplayName("CSV test")
    @CsvSource({
            "사과, 1000",
            "바나나, 2000",
            "포도, 3000"
    })
    void test4(String name, Integer price) {
        product.setName(name);
        product.setPrice(price);

        assertAll(
                () -> assertEquals(name, product.getName()),
                () -> assertEquals(price, product.getPrice())
        );
    }

    @DisplayName("test5")
    @CsvSource({
            "사과, -1",
            "'', 1000"
    })
    void test5(String name, int price) {

        assertThrows(IllegalArgumentException.class, () -> {
            productService.createProduct(name, price);
        });
    }



}



