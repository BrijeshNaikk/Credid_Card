package com.ofss.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofss.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findAllByCustomerId(Long customerId);

	Optional<Transaction> findByTransactionIdAndCustomerId(
	        Long transactionId,
	        Long customerId
	);
	
	List<Transaction> findByTransactionDateTimeBetweenOrderByTransactionDateTimeDesc(
	        LocalDateTime fromDateTime,
	        LocalDateTime toDateTime
	);

	List<Transaction> findByMerchantIdOrderByTransactionDateTimeDesc(
	        Long merchantId
	);

	List<Transaction> findByAmountBetweenOrderByTransactionDateTimeDesc(
	        BigDecimal minimumAmount,
	        BigDecimal maximumAmount
	);

	List<Transaction> findByCardNumberAndCustomerIdAndTransactionDateTimeBetweenOrderByTransactionDateTimeDesc(
	        String cardNumber,
	        Long customerId,
	        LocalDateTime fromDateTime,
	        LocalDateTime toDateTime
	);
}