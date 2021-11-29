package com.app.entrypoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.app.api.LeagueApi;

@SpringBootApplication(scanBasePackages = {"com.app.api", "com.app.service","com.app.util"})
//@ComponentScan(basePackageClasses=LeagueApi.class)
//@ComponentScan(basePackageClasses=LeagueApi.class)
@EnableJpaRepositories("com.app.model")
@EntityScan("com.app.entites")

public class ChampionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChampionApplication.class, args);
	}

}
