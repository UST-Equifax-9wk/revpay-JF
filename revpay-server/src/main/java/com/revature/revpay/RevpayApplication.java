package com.revature.revpay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan(basePackages = "com.revature.revpay.controllers, com.revature.revpay.services, com.revature.revpay.repositories, com.revature.revpay.entities")
@SpringBootApplication
public class RevpayApplication {


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RevpayApplication.class, args);
	}

}
