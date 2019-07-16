package io.github.cepr0.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepo extends JpaRepository<Citizen, Long> {
}
