package bro_nation_fitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {
        "bro_nation_fitness",
        "com.bronationfitness"
})

@EnableJpaRepositories(basePackages = "com.bronationfitness.repository")

@EntityScan(basePackages = "com.bronationfitness.model")

public class BroNationFitnessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BroNationFitnessApplication.class, args);
    }

}