package com.insside.biller.budget;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.insside.biller.discount.Discount;
import com.insside.biller.payment.Payment;

import lombok.Data;

@Data
@Entity
@Table(name="budget")
public class Budget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer number;
	
	@Column(name="creation_date")
	private Date creationDate;
	
	@Column(name="expiration_days")
	private Integer expirationDays;

	@Column(name="client_name")
	private String clientName;

	@Column(name="payment_condition")
	private Integer paymentCondition;

	@OneToOne
	@JoinColumn(name="id_payment")
	private Payment payment;

	@OneToOne
	@JoinColumn(name="id_discount")
	private Discount discount;

}