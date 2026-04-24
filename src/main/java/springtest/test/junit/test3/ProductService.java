package springtest.test.junit.test3;

public class ProductService {


    public boolean isValidName(String name) {
        return name != null && !name.isBlank();
    }

    public boolean isValidPrice(int price) {
        return price > 0;
    }


    public Product createProduct(String name, int price) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("상품명이 유효하지 않습니다.");
        }
        if (!isValidPrice(price)) {
            throw new IllegalArgumentException("가격은 0보다 커야 합니다.");
        }
        return new Product(name, price);
    }

}
