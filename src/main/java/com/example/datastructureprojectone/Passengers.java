package com.example.datastructureprojectone;

public class Passengers implements Comparable<Passengers> {

	private int numflight;
	private long numticket;
	private String fullname;
	private String passport;
	private String nationality;
	private String data;
	

	public Passengers(int numflight, long numticket, String fullname, String passport, String nationality,
			String data) {
		super();
		this.numflight = numflight;
		this.numticket = numticket;
		this.fullname = fullname;
		this.passport = passport;
		this.nationality = nationality;
		this.data = data;
	}

	public int getNumflight() {
		return numflight;
	}

	public void setNumflight(int numflight) {
		this.numflight = numflight;
	}

	public long getNumticket() {
		return numticket;
	}

	public void setNumticket(long numticket) {
		this.numticket = numticket;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}
	
	

	@Override
	public String toString() {
		return "" + numflight + "," + numticket + "," + fullname + "," + passport + "," + nationality + "," + data
				+ "\n";
	}

	@Override
	public int compareTo(Passengers o) {
		return this.fullname.compareTo(o.fullname);
	}

}
