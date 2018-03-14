import java.util.Date;
import java.util.Locale;

import java.util.Arrays;
import java.util.ArrayList;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class Flight {
    public static ArrayList<Flight> flights = new ArrayList<>();

    private String code, origin, destination, airline, daysOfWeek, seatClass;
    private int departureTime, arrivalTime;
    private double price;

    public Flight(String code, String origin, String dest, String days, 
                    int depart, int arrival, String  airline, double price, String seatClass) {
        this.code = code;
        this.origin = origin;
        this.destination = dest;
        this.daysOfWeek = days;
        this.departureTime = depart;
        this.arrivalTime = arrival;
        this.airline = airline;
        this.price = price;
        this.seatClass = seatClass;
    } 

    public static ArrayList<Flight> searchFlights(String airport, Date date) {
        ArrayList<Flight> matches = new ArrayList<>();
        String day = String.valueOf(date.getDay());

        for (Flight f : flights) {
            if (f.origin.equals(airport) && f.daysOfWeek.contains(day)) {
                matches.add(f);
            }
        }
        return matches;
    } 

    /* Getter Methods */
    public String getCode() { return this.code; }
    public String getOrigin() { return this.origin; }
    public String getDestination() { return this.destination; }
    public String getDaysOfWeek() { return this.daysOfWeek; }
    public int getDepartureTime() { return this.departureTime; }
    public int getArrivalTime() { return this.arrivalTime; }
    public String getAirline() { return this.airline; }
    public double getPrice() { return this.price; }
    public String getSeatClass() { return this.seatClass; }

    /* Setter Methods */
    public void setCode(String newCode) { this.code = newCode; }
    public void setOrigin(String newOrigin) { this.origin = newOrigin; }
    public void setDestination(String newDestination) { this.destination = newDestination; }
    public void setDaysOfWeek(String newDaysOfWeek) { this.daysOfWeek = newDaysOfWeek; }
    public void setDepartureTime(int newDepartureTime) { this.departureTime = newDepartureTime; }
    public void setArrivalTime(int newArrivalTime) { this.arrivalTime = newArrivalTime; }
    public void setAirline(String newAirline) { this.airline = newAirline; }
    public void setPrice(int newPrice) { this.price = newPrice; }
    public void setSeatClass(String newSeatClass) { this.seatClass = newSeatClass; }

    public String toString() {
        return this.code;
    }

    public static void main(String args[]) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Input filename: ");
        String csvFile = keyboard.nextLine();

        String line = "";
        String cvsSplitBy = ", ";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                if (line.equals("")) { break; }
                // use comma as separator
                String[] flight = line.split(cvsSplitBy);
                /* Arguments Order:
                    Code, Origin, Destination, Days of the Week, Departure Time,
                    Arrival Time, Airline, Price, Seat Class
                */
                Flight.flights.add(new Flight(flight[0], flight[1], flight[2], 
                            flight[3], Integer.parseInt(flight[4]), 
                            Integer.parseInt(flight[5]), flight[6], 
                            Double.parseDouble(flight[7]), flight[8]));

            }

        } catch (IOException e) {
            System.out.println("Invalid file");
            e.printStackTrace();
        }
        
        System.out.println("Input airport: ");
        String airport = keyboard.nextLine();
        System.out.println("Input Date (ex: 2018-02-31): ");
        String inputDate = keyboard.nextLine();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = new Date();
        try {
            date = df.parse(inputDate);
        } catch (ParseException e) {
            System.out.println("Invalid format");
        }
        
        // Search and print out the list of flights matching the 
        ArrayList<Flight> matches = searchFlights(airport, date);
        System.out.println(Arrays.toString(matches.toArray()));
        keyboard.close();
    }
}