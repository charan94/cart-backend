package org.commkart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class CmkFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmkFrontendApplication.class, args);
	}

}
