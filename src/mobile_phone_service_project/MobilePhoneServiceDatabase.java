package mobile_phone_service_project;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

import static mobile_phone_service_project.MyProjectUtils.*;

public class MobilePhoneServiceDatabase {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(url,userName, "");
            Statement statement = connection.createStatement();

            //create table if it doesn't exist
            statement.execute("CREATE TABLE IF NOT EXISTS Customer_Information_And_Repair_Part " +
                    "(Id INT AUTO_INCREMENT PRIMARY KEY," +
                    "Customer_name VARCHAR(100)," +
                    "Customer_ph_number INT," +
                    "Customer_email VARCHAR(50)," +
                    "Customer_address VARCHAR(200)," +
                    "Device_information VARCHAR(200)" +
                    "Repair_part VARCHAR(50)," +
                    "Error_message VARCHAR(300)" +
                    "Service_provider_name VARCHAR(100)," +
                    "Service_receive_time VARCHAR(30))");
//            Connection connection1 = DriverManager.getConnection(url,userName,"");
//            Statement statement1 = connection1.createStatement();
//            statement1.execute("CREATE TABLE IF NOT EXISTS Service_Provider_Information" +
//                    "(Id INT AUTO_INCREMENT PRIMARY KEY," +
//                    "Service_provider_name VARCHAR(100)," +
//                    "Service_provider_ph_number INT," +
//                    "Service_provider_address VARCHAR(300)," +
//                    "Service_provider_start_working_date VARCHAR(50))");
//            Connection connection2 = DriverManager.getConnection(url,userName,"");
//            Statement statement2 = connection2.createStatement();
//            statement2.execute("CREATE TABLE IF NOT EXISTS Phone_Returning_To_Customer_Time " +
//                    "(Id_from_Customer_Information INT," +
//                    "Returning_Received_Customer_Name VARCHAR(100)," +
//                    "Returning_Time VARCHAR(30))");

            while(true){
                System.out.println("Mobile Phone Service Menu");
                System.out.println("1. Customer Information,Repair Part And Error Message");
                System.out.println("2. Service Provider Information");
                System.out.println("3. Mobile Phone Returning Time");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = new Scanner(System.in).nextInt();

                switch (choice){
                    case 1:
                        while (true){
                            System.out.println("Customer Information,Repair Part And Error Message Menu");
                            System.out.println("1. Add Customer Information");
                            System.out.println("2. List All Information");
                            System.out.println("3. Search Customer Information");
                            System.out.println("4. Update Customer Information");
                            System.out.println("5. Update Customer Information");
                            System.out.println("6. Exit");
                            System.out.print("Enter your choice: ");

                            int choice1 = new Scanner(System.in).nextInt();

                            switch (choice1){
                                case 1:
                                    System.out.print("Enter Customer Name: ");
                                    String customerName = new Scanner(System.in).nextLine();
                                    System.out.print("Enter Phone Number: ");
                                    int phoneNumber = new Scanner(System.in).nextInt();
                                    System.out.print("Enter E-mail: ");
                                    String email = new Scanner(System.in).nextLine();
                                    System.out.print("Enter Address: ");
                                    String address = new Scanner(System.in).nextLine();
                                    System.out.print("Enter Device Information: ");
                                    String deviceInformation = new Scanner(System.in).nextLine();
                                    System.out.print("Enter Repair Parts: ");
                                    String repairPart = new Scanner(System.in).nextLine();
                                    System.out.print("Enter Error Message: ");
                                    String errorMessage = new Scanner(System.in).nextLine();
                                    System.out.print("Enter Service Provider Name: ");
                                    String serviceProviderName = new Scanner(System.in).nextLine();
                                    LocalDateTime now = LocalDateTime.now();
                                    String insertQuery = "INSERT INTO Customer_Information_And_Repair_Part " +
                                            "(Customer_name, Customer_ph_number, Customer_email, Customer_address, Device_information, Repair_part, Error_message, Service_provider_name, Service_receive_time)" +
                                            "VALUES(? ,? ,? ,? ,? ,? ,? ,? ,?)";
                                    PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                                    insertStatement.setString(1,customerName);
                                    insertStatement.setInt(2,phoneNumber);
                                    insertStatement.setString(3,email);
                                    insertStatement.setString(4,address);
                                    insertStatement.setString(5,deviceInformation);
                                    insertStatement.setString(6,repairPart);
                                    insertStatement.setString(7,errorMessage);
                                    insertStatement.setString(8,serviceProviderName);
                                    insertStatement.setString(9, String.valueOf(now));
                                    insertStatement.executeUpdate();
                                    System.out.println("Information added Successfully!");
                                    break;
                                case 2:
                                    ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer_Information_And_Repair_Part");
                                    while (resultSet.next()){
                                        System.out.println("ID: " + resultSet.getString("Id"));
                                        System.out.println("Name: " + resultSet.getString("Customer_name"));
                                        System.out.println("Phone Number: " + resultSet.getString("Customer_ph_number"));
                                        System.out.println("E-mail: " + resultSet.getString("Customer_email"));
                                        System.out.println("Address: " + resultSet.getString("Customer_address"));
                                        System.out.println("Device Information: " + resultSet.getString("Device_information"));
                                        System.out.println("Repair Part(s): " + resultSet.getString("Repair_part"));
                                        System.out.println("Error Message: " + resultSet.getString("Error Message"));
                                        System.out.println("Service Provider Name: " + resultSet.getString("Service_provider_name"));
                                        System.out.println("Service received time: " + resultSet.getString("Service_receive_time"));
                                    }
                            }
                        }

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
