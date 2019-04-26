#Ticket Service API
--------------------------------
#It has three APIs:
* numSeatsAvailable()
* findAndHoldSeats(int numSeats, String customerEmail)   -- Block the Seats
* reserveSeats(int seatHoldId, String customerEmail)  -- Reserve blocked seat

#Assumptions:
* Simple API, can be used as a standalone application
* As there is no real Database is intregrated, so application cannot be tested with actual values.
* There are two tables, Ticket & SeatHold. It is assumed that ticket table will be loaded with the records (which is equivalent to the number of seats available)
	before deploying the application to SystemTest or Production. Default value of each ticket will be NOT_RESERVED.
*Database trigger will be added to seathold table row. For every new row, trigger will be active and expired after set time and set the status equal to EXPIRED.


#Testing the application:
* Junit and Mockito used to test the code.
* To run unit test use below command.
mvn clean
mvn test
