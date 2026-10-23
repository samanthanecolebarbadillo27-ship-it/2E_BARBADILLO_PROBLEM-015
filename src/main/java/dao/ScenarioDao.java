package dao;

import model.Luggage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScenarioDao {

    public ScenarioDao() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS luggage ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "passenger_name TEXT NOT NULL, "
                + "flight_no TEXT NOT NULL, "
                + "weight_kg REAL NOT NULL, "
                + "status TEXT NOT NULL)";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public boolean addRecord(Luggage luggage) {
        String sql = "INSERT INTO luggage (passenger_name, flight_no, weight_kg, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, luggage.getPassengerName());
            ps.setString(2, luggage.getFlightNo());
            ps.setDouble(3, luggage.getWeightKg());
            ps.setString(4, luggage.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    public List<Luggage> getAllRecords() {
        List<Luggage> list = new ArrayList<>();
        String sql = "SELECT id, passenger_name, flight_no, weight_kg, status FROM luggage ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Luggage(
                        rs.getInt("id"),
                        rs.getString("passenger_name"),
                        rs.getString("flight_no"),
                        rs.getDouble("weight_kg"),
                        rs.getString("status")));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return list;
    }
}
