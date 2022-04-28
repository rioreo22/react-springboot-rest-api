package com.example.gccoffee.repository;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_latest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ProductJDBCRepositoryTest {

    private static final Product newProduct = new Product(UUID.randomUUID(), "new-product", Category.COFFEE_BEAN_PACKAGE, 1000L);
    static EmbeddedMysql embeddedMySql;
    @Autowired
    ProductRepository productRepository;

    @AfterAll
    static void cleanUp() {
        embeddedMySql.stop();
    }

    @BeforeAll
    static void setup() {
        MysqldConfig config = aMysqldConfig(v5_7_latest)
                .withCharset(Charset.UTF8)
                .withPort(2215)
                .withUser("test", "test1234!")
                .withTimeZone("Asia/Seoul")
                .build();
        embeddedMySql = anEmbeddedMysql(config)
                .addSchema("test-order_mgmt", ScriptResolver.classPathScripts("schema.sql"))
                .start();
    }

    @Test
    @Order(1)
    @DisplayName("상품을 추가할 수 있다.")
    void testInsert() {
        productRepository.insert(newProduct);
        List<Product> all = productRepository.findAll();

        assertThat(all).isNotEmpty();
    }


    @Test
    @Order(2)
    @DisplayName("상품을 아이디로 조회할 수 있다.")
    void testFindById() {
        UUID productId = newProduct.getProductId();
        Optional<Product> product = productRepository.findById(productId);
        assertThat(product).isNotEmpty();
    }


    @Test
    @Order(3)
    @DisplayName("상품을 이름으로 조회할 수 있다.")
    void testFindByName() {
        String productName = newProduct.getProductName();
        Optional<Product> product = productRepository.findByName(productName);
        assertThat(product).isNotEmpty();
    }

    @Test
    @Order(4)
    @DisplayName("상품을 카테고리로 조회할 수 있다.")
    void testFindByCategory() {
        List<Product> products = productRepository.findByCategory(Category.COFFEE_BEAN_PACKAGE);
        assertThat(products).isNotEmpty();
    }

    @Test
    @Order(5)
    @DisplayName("상품을 수정할 수 있다.")
    void testUpdate() {
        newProduct.setProductName("updated-product");
        productRepository.update(newProduct);
        Optional<Product> byId = productRepository.findById(newProduct.getProductId());
        assertThat(byId).isNotEmpty();
        assertThat(byId.get().getProductName()).isEqualTo("updated-product");
    }

    @Test
    @Order(6)
    @DisplayName("상품을 전체 삭제할 수 있다.")
    void testDeleteAll() {
        productRepository.deleteAll();
        List<Product> all = productRepository.findAll();
        assertThat(all).isEmpty();
    }

}