package com.example.datastructureprojectone;

public class Flights implements Comparable<Flights> {
	private int fligthnumber;
	private String airlinename;
	private String from;
	private String to;
	private int capacity;
	private LinkedList<Passengers> passdata = new LinkedList<>();
	public Flights() {
		
	}

	public Flights(int fligthnumber, String airlinename, String from, String to, int capacity) {
		super();
		this.fligthnumber = fligthnumber;
		this.airlinename = airlinename;
		this.from = from;
		this.to = to;
		this.capacity = capacity;
	}
	
	
	
	
	

	public Flights(int fligthnumber, String airlinename, String from, String to, int capacity,
			LinkedList<Passengers> passdata) {
		super();
		this.fligthnumber = fligthnumber;
		this.airlinename = airlinename;
		this.from = from;
		this.to = to;
		this.capacity = capacity;
		this.passdata = passdata;
	}

	public int getFligthnumber() {
		return fligthnumber;
	}

	public void setFligthnumber(int fligthnumber) {
		this.fligthnumber = fligthnumber;
	}

	public String getAirlinename() {
		return airlinename;
	}

	public void setAirlinename(String airlinename) {
		this.airlinename = airlinename;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public LinkedList<Passengers> getPassdata() {
		return passdata;
	}

	public void setPassdata(LinkedList<Passengers> passdata) {
		this.passdata = passdata;
	}

	@Override
	public String toString() {
		return "The Fligts :" + fligthnumber + "," + airlinename + "," + from + "," + to + "," + capacity +
				"\n"+passdata.toString()+"\n"+"------------------------\n";
	}
	
	

	@Override
	public int compareTo(Flights o) {

		if (fligthnumber > o.fligthnumber) {
            return 1;
        }
        else if (fligthnumber == o.fligthnumber) {
            return 0;
        }
        else {
            return -1;
        }
	}

	
	
}
