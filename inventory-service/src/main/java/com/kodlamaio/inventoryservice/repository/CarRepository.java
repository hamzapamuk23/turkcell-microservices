package com.kodlamaio.inventoryservice.repository;

import com.kodlamaio.inventoryservice.entities.Car;
import com.kodlamaio.inventoryservice.entities.enums.State;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    /*
    Repository'de eğer ki veriyi değiştirmek için bir query yazılacaksa, veri değişikliği sırasında bir hata olduğu takdirde kaydı koruması için aşşağıdaki
     @Modifying ve @Transactional anatasyonları kullanılır.
    */

    @Modifying
    @Transactional
    @Query(value = "UPDATE Car set state = :state WHERE id = :id")
    void changeStateByCarId(State state, UUID id);
}
