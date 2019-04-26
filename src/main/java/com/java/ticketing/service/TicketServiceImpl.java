package com.java.ticketing.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ticketing.dao.TicketDao;
import com.java.ticketing.domain.SeatHold;
import com.java.ticketing.enums.TicketServiceConstants;
import com.java.ticketing.util.TicketServiceUtil;

@Service
public class TicketServiceImpl implements TicketService {

	final static Logger log = Logger.getLogger(TicketServiceImpl.class);

	@Autowired
	private TicketDao ticket;

	public TicketServiceImpl() {

	}

	public TicketServiceImpl(TicketDao ticket) {

		this.ticket = ticket;
	}

	public int numSeatsAvailable() {

		return ticket.numSeatsAvailable();
	}

	public String reserveSeats(int seatHoldId, String customerEmail) {

		if (seatHoldId < 0 || !TicketServiceUtil.isEmailValid(customerEmail)) {
			log.info("Invalid input arguments");
			return TicketServiceConstants.INVALID_INPUT;
		}

		boolean result = ticket.reserveSeat(seatHoldId, customerEmail);
		log.info("Reservation for seatHoldId:" + seatHoldId + " Customer Email: " + customerEmail + " is successful "
				+ result);

		return TicketServiceUtil.getResponseCode(result);
	}

	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {

		if (numSeats < 0 || !TicketServiceUtil.isEmailValid(customerEmail)) {
			log.info("Invalid input arguments");
			// throw exception TicketServiceRuntimeException
			return null;
		}
		SeatHold seatHold = ticket.findAndHoldSeats(numSeats, customerEmail);
		log.info("For total seats :" + numSeats + " Customer Email: " + customerEmail
				+ "  is on hold. Please reserve before it expires.");

		return seatHold;
	}

}
