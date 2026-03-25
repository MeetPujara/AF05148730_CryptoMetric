package com.cryptometric.dao;

import com.cryptometric.database.DBConnection;
import com.cryptometric.model.Asset;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PortfolioDAO {

    // CREATE: Save a new crypto purchase
    public void addAsset(Asset asset) {
        String sql = "INSERT INTO my_portfolio (symbol, quantity, buy_price) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, asset.getSymbol());
            pstmt.setDouble(2, asset.getQuantity());
            pstmt.setDouble(3, asset.getBuyPrice());
            pstmt.executeUpdate();
            System.out.println("Asset added to portfolio successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ: Get all holdings from the database
    public List<Asset> getAllAssets() {
        List<Asset> list = new ArrayList<>();
        String sql = "SELECT * FROM my_portfolio";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Asset asset = new Asset(
                        rs.getString("symbol"),
                        rs.getDouble("quantity"),
                        rs.getDouble("buy_price")
                );
                asset.setId(rs.getInt("id"));
                list.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}