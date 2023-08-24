package com.dgmf;

import com.dgmf.entity.Product;
import com.dgmf.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Long productPrice = 0L;

		for(int i = 1; i <= 100; i++) {
			productPrice += 100L;

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
	}
}
