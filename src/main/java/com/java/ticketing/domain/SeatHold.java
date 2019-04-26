package com.java.ticketing.domain;

import java.util.List;

import javax.persistence.Enumerated;

import org.hibernate.annotations.Entity;

import com.java.ticketing.enums.ActivityStatusEnum;

/**
 * Class hold information about seat which are on hold. Every record in this
 * class(table) is attached to timer.. After seat is reserved or timer expired,
 * record will be either marked complete or expired. This class has one to many
 * relationship with ticket.
 * 
 * @author rp105e
 *
 */
@Entity
public class SeatHold {

	private int id;
	private String customerEmail;
	private int numSeats;
	
	@Enumerated
	private ActivityStatusEnum status;

	public ActivityStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ActivityStatusEnum status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

}
