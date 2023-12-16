package com.dgmf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// @EnableTransactionManagement ==> Optional ==> Spring Boot Default Behavior
public class Application // implements CommandLineRunner
{
	/*@Autowired
	private ProductRepository productRepository;*/

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		Long productPrice = 0L;

		for(int i = 1; i <= 100; i++) {
			productPrice += 10L;

			Product product = Product.builder()
					.productName("Product " + i)
					.productDescription("Product " + i + " - Description")
					.productSku("100ABCDEq658f23" + i)
					.productPrice(new BigDecimal(productPrice))
					.isActive(true)
					.imageUrl("product" + i + ".png")
					.build();

			productRepository.save(product);
		}
	}*/
}
