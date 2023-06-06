package com.fvukic.webshop.repository;

import com.fvukic.webshop.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Integer> {
}
