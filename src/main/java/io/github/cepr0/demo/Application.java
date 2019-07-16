package io.github.cepr0.demo;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@SpringBootApplication
public class Application {

	private final Faker faker;
	private final CityRepo cityRepo;
	private final CitizenRepo citizenRepo;

	public Application(final CityRepo cityRepo, final CitizenRepo citizenRepo) {
		faker = new Faker();
		this.cityRepo = cityRepo;
		this.citizenRepo = citizenRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onReady() {

		String cityName = faker.country().capital();
		City city = cityRepo.save(new City(cityName));

		log.info("[i] Begin inserting");
		citizenRepo.saveAll(IntStream
				.rangeClosed(1, 1_000)
				.mapToObj(i -> new Citizen(city, faker.name().fullName()))
				.collect(Collectors.toList())
		);
		log.info("[i] Stop inserting");
	}
}
