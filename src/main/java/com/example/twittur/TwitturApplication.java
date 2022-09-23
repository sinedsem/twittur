package com.example.twittur;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import org.cognitor.cassandra.migration.spring.CassandraMigrationAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

@SpringBootApplication
public class TwitturApplication {


    private final static Logger log = LoggerFactory.getLogger(TwitturApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(TwitturApplication.class, args);
    }

    @Bean
    @Qualifier(CassandraMigrationAutoConfiguration.CQL_SESSION_BEAN_NAME)
    public CqlSession cassandraMigrationCqlSession(CqlSessionBuilder cqlSessionBuilder) {
        return cqlSessionBuilder.build();
    }

    @Bean
    @Primary
    public CqlSession applicationCqlSession(CqlSessionBuilder cqlSessionBuilder) {
        return cqlSessionBuilder.build();
    }

    @Bean
    public CommandLineRunner clr(VetRepository vetRepository) {
        return args -> {
            vetRepository.deleteAll();

//            Vet john = new Vet(UUID.randomUUID(), "John", "Doe", new HashSet<>(Arrays.asList("surgery")));
//            Vet jane = new Vet(UUID.randomUUID(), "Jane", "Doe", new HashSet<>(Arrays.asList("radiology, surgery")));

//            Vet savedJohn = vetRepository.save(john);
//            Vet savedJane = vetRepository.save(jane);

            vetRepository.findAll()
                    .forEach(v -> log.info("Vet: {}", v.getFirstName()));

//            vetRepository.findById(savedJohn.getId())
//                    .ifPresent(v -> log.info("Vet by id: {}", v.getFirstName()));

        };
    }

}
