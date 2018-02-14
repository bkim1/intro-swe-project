import java.util.Date;
import java.util.Locale;

import java.util.ArrayList;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class Flight {
    public static ArrayList<Flight> flights;

    private String code, origin, destination, airline, daysOfWeek;
    private int departureTime, arrivalTime, price;

    public Flight(String code, String origin, String dest, String days, 
                    int depart, int arrival, String  airline, int price) {
        this.code = code;
        this.origin = origin;
        this.destination = dest;
        this.daysOfWeek = days;
        this.departureTime = depart;
        this.arrivalTime = arrival;
        this.airline = airline;
        this.price = price;
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
    public int getPrice() { return this.price; }

    /* Setter Methods */
    public void setCode(String newCode) { this.code = newCode; }
    public void setOrigin(String newOrigin) { this.origin = newOrigin; }
    public void setDestination(String newDestination) { this.destination = newDestination; }
    public void setDaysOfWeek(String newDaysOfWeek) { this.daysOfWeek = newDaysOfWeek; }
    public void setDepartureTime(int newDepartureTime) { this.departureTime = newDepartureTime; }
    public void setArrivalTime(int newArrivalTime) { this.arrivalTime = newArrivalTime; }
    public void setAirline(String newAirline) { this.airline = newAirline; }
    public void setPrice(int newPrice) { this.price = newPrice; }

    public static void main(String args[]) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Input filename: ");
        String csvFile = keyboard.nextLine();

        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] flight = line.split(cvsSplitBy);

                flights.add(new Flight(flight[0], flight[1], flight[2], 
                            flight[3], Integer.parseInt(flight[4]), 
                            Integer.parseInt(flight[5]), flight[6], 
                            Integer.parseInt(flight[7])));

            }

        } catch (IOException e) {
            System.out.println("Invalid file");
            e.printStackTrace();
        }
        
        System.out.println("Input airport: ");
        String airport = keyboard.nextLine();
        System.out.println("Input Date (ex: 2018-02-31 13:30: ");
        String inputDate = keyboard.nextLine();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        Date date = new Date();
        try {
            date = df.parse(inputDate);
        } catch (ParseException e) {
            System.out.println("Invalid format");
        }
        

        ArrayList<Flight> matches = searchFlights(airport, date);
        System.out.println(matches);
    }
}