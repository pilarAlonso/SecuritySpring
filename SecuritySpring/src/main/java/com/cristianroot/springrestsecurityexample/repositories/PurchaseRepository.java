package com.cristianroot.springrestsecurityexample.repositories;

import com.cristianroot.springrestsecurityexample.entities.MusicGroup;
import com.cristianroot.springrestsecurityexample.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.awt.SunToolkit;

import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	Optional<Purchase> findByOrderNumberIgnoreCase(String number);
}
