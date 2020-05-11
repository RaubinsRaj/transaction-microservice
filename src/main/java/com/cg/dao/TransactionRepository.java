package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

	@Query("from Transaction where userId=?1")
	List<Transaction>findByUserId(String userId);
	
	@Query("from Transaction where bookingId=?1")
	Transaction findByBookingId(int bookingId);
	
}
