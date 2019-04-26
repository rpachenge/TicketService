package com.java.ticketing.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.ticketing.domain.SeatHold;
import com.java.ticketing.domain.Ticket;
import com.java.ticketing.enums.ActivityStatusEnum;
import com.java.ticketing.enums.SeatStatusEnum;

@Repository
public class TicketDaoImpl implements TicketDao {

	final static Logger log = Logger.getLogger(TicketDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public List<Ticket> findTicketForStatus(SeatStatusEnum seatStatus, int seatHoldIId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("FROM Ticket WHERE seatStatus = :status and seatHoldId=:seatHoldID ORDER BY id");
		query.setParameter("status", seatStatus);
		query.setParameter("seatHoldID", seatHoldIId);

		return query.list();
	}

	public int numSeatsAvailable() {

		/*
		 * Retrieve all the seats Ids or the count with condition seatstatus =
		 * SeatStatus.NOT_RESERVED
		 */
		int numAvailableSeat = findTicketForStatus(SeatStatusEnum.NOT_RESERVED, 0).size();

		log.debug(" Number of seats which are with NOT_RESERVED State");
		return numAvailableSeat;
	}

	public boolean reserveSeat(int seatHoldIId, String customerEmail) {

		/*
		 * update SeatStatus for each ticker to RESERVE for all the tickets IDS which
		 * are part of seatHoldID if ActivityStatusEnum is not expired or completed.
		 */
		Session session = sessionFactory.getCurrentSession();
		Transaction txn = session.beginTransaction();

		List<Ticket> listTickets = findTicketForStatus(SeatStatusEnum.ON_HOLD, seatHoldIId);
		for (Ticket ticket : listTickets) {
			ticket.setSeatStatus(SeatStatusEnum.RESERVED);
			session.update(ticket);
		}

		// Update SeatHold table
		SeatHold seatHold = (SeatHold) session.createQuery("FROM Seathold WHERE id = :id ")
				.setParameter("id", seatHoldIId).list().get(0);
		seatHold.setStatus(ActivityStatusEnum.COMPLETED);
		session.update(seatHold);

		txn.commit();
		session.close();
		log.debug(" Reserve Seat for SeatHoldID=" + seatHoldIId + "for the customer Email=" + customerEmail);
		return false;
	}

	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {

		/*
		 * Query for seats which are available (NOT_RESERVED) state from Ticket table
		 * and insert new record in SeatHold table with status as IN_PROGRESS and
		 * associate with customer email. 
		 * Add Trigger to each row added to the SeatHold table
		 * and add timer to the SeatHold. Once timer expires, update status to
		 * completed.
		 */

		Session session = sessionFactory.getCurrentSession();
		Transaction txn = session.beginTransaction();

		List<Ticket> listTickets = session.createQuery("Select id FROM Ticket WHERE status = :status ORDER BY id")
				.setParameter("status", SeatStatusEnum.NOT_RESERVED).list();

		SeatHold seatHold = new SeatHold();
		seatHold.setCustomerEmail(customerEmail);
		seatHold.setNumSeats(numSeats);
		seatHold.setStatus(ActivityStatusEnum.IN_PROGRESS);
		session.save(seatHold);

		for (Ticket ticket : listTickets) {
			ticket.setSeatStatus(SeatStatusEnum.ON_HOLD);
			ticket.setCustomerEmail(customerEmail);
			ticket.setSeatHoldId(seatHold.getId());
			session.update(ticket);
		}

		txn.commit();
		session.close();
		log.debug(" Reserve Seat for SeatHoldID=" + numSeats + "for the customer Email=" + customerEmail);

		return seatHold;
	}

}
