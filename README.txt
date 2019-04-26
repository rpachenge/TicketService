Ticket Service API
--------------------------------
APIs:
It has three APIs:
1. numSeatsAvailable()
2. findAndHoldSeats(int numSeats, String customerEmail)   -- Block the Seats
3. reserveSeats(int seatHoldId, String customerEmail)  -- Reserve blocked seat

Assumptions:
1. Simple API, can be used as a standalone application
2. As there is no real Database is intregrated, so application cannot be tested with actual values.
3. There are two tables, Ticket & SeatHold. It is assumed that ticket table will be loaded with the records (which is equivalent to the number of seats available)
	before deploying the application to SystemTest or Production. Default value of each ticket will be NOT_RESERVED.
3.Database trigger will be added to seathold table row. For every new row, trigger will be active and expired after set time and set the status equal to EXPIRED.


Testing the application:
1. Junit and Mockito used to test the code.
2. To run unit test use below command.
mvn test
  