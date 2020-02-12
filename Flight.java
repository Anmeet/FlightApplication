/* This is the Flight class which contain the constructor ,getter and setter method to get ,set departure,
arrival,yearmonth,price*/


public class Flight {
    String departure;  //variables declared
    String arrival;
    String yearmonth;
    double price;

      //constructor
	public Flight(String flightDeparture, String flightArrival, String flightYearmonth , double flightPrice )
	{
	   departure = flightDeparture;
           arrival = flightArrival;
           yearmonth = flightYearmonth;
           price = flightPrice;

	}
    //mutator method
	public void setDeparture(String d)
	{
	   departure=d;
	}

	public void setArrival(String a)
	{
           arrival=a;
	}
        public void setYearmonth(String y)
	{
	   yearmonth=y;
	}

	public void setPrice(double p)
	{
           price=p;
	}

    //accessor method
	public String getDeparture()
	{
	   return departure;
	}

	public String getArrival()
	{
	   return arrival;
	}
        public String getYearmonth()
	{
	   return yearmonth;
	}
        public double getPrice()
	{
	   return price;
	}


}
