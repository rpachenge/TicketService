package com.java.ticketing.dao;

import com.java.ticketing.domain.SeatHold;

/**
 * DAO Layer, add DB CRUD APIs  
 * @author rp105e
 *
 */
public interface TicketDao {

	/**
	 * Reserve Seats, join SeatHold and Ticket table, update each ticket SeatStatus=SeatStatus.RESERVED.
	 * Update SeatHold status as complete
	 * @param seatHoldIId
	 * @param customerEmail
	 * @return
	 */
	public boolean reserveSeat(int seatHoldIId, String customerEmail);

	/**
	 * Retrieve the count with condition seatstatus = SeatStatus.NOT_RESERVED
	 * 
	 * @return Number of available seats
	 */
	public int numSeatsAvailable();

	/**
	 * Find available seats with condition as seatStatus=SeatStatus.NOT_RESERVED.
	 * Update Ticket object which is equal to numSeats - seatStatus
	 * =SeatStatus.ON_HOLD and customerEmail for each ticket. Update SeatHold.Status
	 * as inprogress. Please note SeatHold ID is associated with the timer, once the
	 * timer expires
	 * 
	 * @param numSeats - Number of seats require to be hold
	 * @param customerEmail - Customer email associated with each ticket/seat.
	 * @return SeatHold object contains details about the ticket IDs, customer email.
	 */
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail);

}
