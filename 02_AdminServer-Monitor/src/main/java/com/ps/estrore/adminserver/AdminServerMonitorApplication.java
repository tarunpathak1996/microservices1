package com.ps.estrore.adminserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class AdminServerMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServerMonitorApplication.class, args);
	}

}
