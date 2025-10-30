package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;



@SpringBootApplication
@EntityScan
public class BackendApplication {

	public static void main(String[] args) {
		// ⚙️ Thêm 2 dòng này TRƯỚC KHI chạy Spring
        System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");
        System.setProperty("sun.net.spi.nameservice.nameservers", "8.8.8.8");
		SpringApplication.run(BackendApplication.class, args);
	}

}
