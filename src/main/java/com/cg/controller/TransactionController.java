package com.cg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.dao.TransactionRepository;
import com.cg.entity.Transaction;


@RestController
@CrossOrigin(origins= "*")
public class TransactionController {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(path="/transactions/userTransactions")
	public Transaction cancelBooking(@RequestBody Transaction transaction)
	{
		
		Transaction inTransaction=restTemplate.getForObject("http://localhost:3000/bookmanagement/booking/bookingId/"+transaction.getBookingId() , Transaction.class);
		Transaction userTransaction=new Transaction();
		userTransaction.setBookingId(inTransaction.getBookingId());
		userTransaction.setUserId(inTransaction.getUserId());
		userTransaction.setDateOfJourney(inTransaction.getDateOfJourney());
		userTransaction.setBusId(inTransaction.getBusId());
		
		userTransaction.setSeatNo(inTransaction.getSeatNo());
		userTransaction.setFare(inTransaction.getFare());
		return  transactionRepository.save(userTransaction);
		
	}
	
	
	
	@GetMapping("/transactions/userId/refundMoney/bookingId/{bookingId}")
	public float totalRefundMoney(@PathVariable int bookingId) {
		float totalAmountPaid=0;
		Transaction userTransaction=new Transaction();
		userTransaction=transactionRepository.findByBookingId(bookingId);
		
		totalAmountPaid=userTransaction.getFare();
		
		return totalAmountPaid;	
	}
	
	
	

}