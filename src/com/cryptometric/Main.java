package com.cryptometric;

import com.cryptometric.dao.PortfolioDAO;
import com.cryptometric.model.Asset;
import com.cryptometric.service.CryptoService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PortfolioDAO dao = new PortfolioDAO();
        CryptoService service = new CryptoService();

        while (true) {
            System.out.println("\n--- CRYPTOMETRIC MAIN MENU ---");
            System.out.println("1. Add New Asset (Buy)");
            System.out.println("2. View Real-Time Portfolio Report");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = sc.nextInt();
            if (choice == 3) break;

            switch (choice) {
                case 1:
                    System.out.print("Enter Coin Symbol (e.g., BTC, ETH): ");
                    String symbol = sc.next().toUpperCase();
                    System.out.print("Enter Quantity: ");
                    double qty = sc.nextDouble();
                    System.out.print("Enter Purchase Price (USD): ");
                    double price = sc.nextDouble();

                    dao.addAsset(new Asset(symbol, qty, price));
                    break;

                case 2:
                    service.displayPortfolioReport();
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        System.out.println("Exiting CryptoMetric. Happy Trading!");
        sc.close();
    }
}