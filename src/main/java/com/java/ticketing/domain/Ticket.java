package com.java.ticketing.domain;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import org.hibernate.type.EnumType;
import org.springframework.beans.factory.annotation.Autowired;

import com.java.ticketing.enums.SeatStatusEnum;

/**
 * Domain Entity. Each ticket has Non-Null parameters as seatnumber, customerEmail & seatStatus.
 * Each seat is associated with one Ticket.
 * @author rp105e
 *
 */
@Entity
public class Ticket {

	private String id;
	private String customerEmail;
	private int seatNumber;
	private int seatHoldId;
	
	@Enumerated
	private SeatStatusEnum seatStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getSeatHoldId() {
		return seatHoldId;
	}

	public void setSeatHoldId(int seatHoldId) {
		this.seatHoldId = seatHoldId;
	}
	
	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public SeatStatusEnum getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatusEnum seatStatus) {
		this.seatStatus = seatStatus;
	}

}
