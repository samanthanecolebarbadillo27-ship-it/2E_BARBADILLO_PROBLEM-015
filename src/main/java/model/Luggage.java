package model;

public class Luggage {
    private int id;
    private String passengerName;
    private String flightNo;
    private double weightKg;
    private String status;

    public Luggage(String passengerName, String flightNo, double weightKg, String status) {
        this.passengerName = passengerName;
        this.flightNo = flightNo;
        this.weightKg = weightKg;
        this.status = status;
    }

    public Luggage(int id, String passengerName, String flightNo, double weightKg, String status) {
        this(passengerName, flightNo, weightKg, status);
        this.id = id;
    }

    public int getId() { return id; }
    public String getPassengerName() { return passengerName; }
    public String getFlightNo() { return flightNo; }
    public double getWeightKg() { return weightKg; }
    public String getStatus() { return status; }
}
