package springtest.test.testcontainers.practice;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class DummyTest {

    static MySQLContainer mySQLContainer = new MySQLContainer()
            .withDatabaseName("dummy");

    @DisplayName("checking for running containers")
    @Test
    void test1() {
        System.out.println("=====================================");
        System.out.println(mySQLContainer.getDatabaseName());
        System.out.println(mySQLContainer.getPassword());

        System.out.println("Hello containers");
    }
}
