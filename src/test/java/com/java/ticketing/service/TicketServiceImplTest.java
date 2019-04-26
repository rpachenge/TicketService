/**
 * 
 */
package com.java.ticketing.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.java.ticketing.dao.TicketDao;
import com.java.ticketing.domain.SeatHold;
import com.java.ticketing.enums.TicketServiceConstants;

/**
 * @author rp105e
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

	@Mock
	private TicketDao ticketDao;
	
	@Mock
	private SeatHold seatHold;

	@InjectMocks
	private TicketServiceImpl ticketService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(ticketDao);
	}

	@Test
	public void testNumSeatAvailable() {

		assertNotNull(ticketService.numSeatsAvailable());
	}

	@Test
	public void testReserveSeats() {

		when(ticketDao.reserveSeat(1, "test@gmail.com")).thenReturn(Boolean.TRUE);
		assertEquals(TicketServiceConstants.SUCCESS, ticketService.reserveSeats(1, "test@gmail.com"));
	}

	@Test
	public void testReserveSeats_WrongInput() {

		when(ticketDao.reserveSeat(1, "test@gmail.com")).thenReturn(Boolean.TRUE);
		assertEquals(TicketServiceConstants.INVALID_INPUT, ticketService.reserveSeats(1, "testgmail.com"));
		assertEquals(TicketServiceConstants.INVALID_INPUT, ticketService.reserveSeats(-1, "test@gmail.com"));
	}

	@Test
	public void testFindAndHoldSeats() {
		when(ticketDao.findAndHoldSeats(1, "test@gmail.com")).thenReturn(seatHold);
		assertNotNull(ticketService.findAndHoldSeats(1, "test@gmail.com"));
	}
	
	@Test
	public void testFindAndHoldSeats_WrongInput() {

		when(ticketDao.findAndHoldSeats(1, "test@gmail.com")).thenReturn(seatHold);
		assertNull(ticketService.findAndHoldSeats(1, "testgmail.com"));
		assertNull(ticketService.findAndHoldSeats(-1, "test@gmail.com"));
	}

}
