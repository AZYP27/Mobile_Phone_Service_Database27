package mobile_phone_service_project;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Scanner;

import static mobile_phone_service_project.MyProjectUtils.*;

/**
 * Version 1.0
 * Release date 21.9.2023
 * #AZYP
 */

/**
 * version 2.0
 * add user authentication
 * update at 22.9.2023
 * #AZYP
 */
public class MobilePhoneServiceDatabase {
    public static void runMobilePhoneService(Connection connection, Statement statement) throws SQLException {
        while(true){
            System.out.println("Mobile Phone Service Menu");
            System.out.println("1. Customer Information,Repair Part And Error Message");
            System.out.println("2. Service Provider Information");
            System.out.println("3. Mobile Phone Returning Time");
            System.out.println("4. Login Info Update");
            System.out.println("5. Exit");
            System.out.print("Enter your choice(Integer only): ");

            int choice = new Scanner(System.in).nextInt();

            switch (choice){
                case 1:
                    while (true){
                        System.out.println(" ");
                        System.out.println("Customer Information,Repair Part And Error Message Menu");
                        System.out.println("1. Add Customer Information");
                        System.out.println("2. List All Information");
                        System.out.println("3. Search Customer Information By ID");
                        System.out.println("4. Update Customer Information");
                        System.out.println("5. Exit");
                        System.out.print("Enter your choice(Integer only): ");

                        int choice1 = new Scanner(System.in).nextInt();

                        switch (choice1){
                            case 1:
                                System.out.print("Enter Customer Name: ");
                                String customerName = new Scanner(System.in).nextLine();
                                System.out.print("Enter Phone Number: ");
                                String phoneNumber = new Scanner(System.in).nextLine();
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
                                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                                String formatDateTime = now.format(dateTimeFormatter);
                                String insertQuery = "INSERT INTO Customer_Information_And_Repair_Part " +
                                        "(Customer_name, Customer_email,Customer_ph_number, Customer_address, Device_information, Repair_part, Error_message, Service_provider_name, Service_receive_time)" +
                                        "VALUES(? ,? ,? ,? ,? ,? ,? ,?, ?)";
                                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                                insertStatement.setString(1,customerName);
                                insertStatement.setString(2,phoneNumber);
                                insertStatement.setString(3,email);
                                insertStatement.setString(4,address);
                                insertStatement.setString(5,deviceInformation);
                                insertStatement.setString(6,repairPart);
                                insertStatement.setString(7,errorMessage);
                                insertStatement.setString(8,serviceProviderName);
                                insertStatement.setString(9, String.valueOf(formatDateTime));
                                insertStatement.executeUpdate();
                                System.out.println("Information added Successfully!");
                                System.out.println(" ");
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
                                    System.out.println("Error Message: " + resultSet.getString("Error_message"));
                                    System.out.println("Service Provider Name: " + resultSet.getString("Service_provider_name"));
                                    System.out.println("Service received time: " + resultSet.getString("Service_receive_time"));
                                    System.out.println(" ");
                                }
                                break;
                            case 3:
                                while(true){
                                    System.out.print("Enter ID: ");
                                    int idForSearch = new Scanner(System.in).nextInt();
                                    ResultSet resultSet2 = statement.executeQuery("SELECT * FROM Customer_Information_And_Repair_Part WHERE id =" + idForSearch);
                                    while (resultSet2.next()) {
                                        System.out.println("ID: " + resultSet2.getString("Id"));
                                        System.out.println("Name: " + resultSet2.getString("Customer_name"));
                                        System.out.println("Phone Number: " + resultSet2.getString("Customer_ph_number"));
                                        System.out.println("E-mail: " + resultSet2.getString("Customer_email"));
                                        System.out.println("Address: " + resultSet2.getString("Customer_address"));
                                        System.out.println("Device Information: " + resultSet2.getString("Device_information"));
                                        System.out.println("Repair Part(s): " + resultSet2.getString("Repair_part"));
                                        System.out.println("Error Message: " + resultSet2.getString("Error_message"));
                                        System.out.println("Service Provider Name: " + resultSet2.getString("Service_provider_name"));
                                        System.out.println("Service received time: " + resultSet2.getString("Service_receive_time"));
                                        System.out.println(" ");
                                    }
                                    break;
                                }
                                break;
                            case 4:
                                System.out.print("Enter the ID of the Customer to update: ");
                                int cusInfoIdToUpdate = new Scanner(System.in).nextInt();

                                ResultSet checkResult = statement.executeQuery("SELECT * FROM Customer_Information_And_Repair_Part WHERE id = " + cusInfoIdToUpdate);
                                if (!checkResult.next()) {
                                    System.out.println("Customer with ID " + cusInfoIdToUpdate + " does not exist.");
                                    System.out.println(" ");
                                    break;
                                }
                                System.out.println(" ");
                                System.out.println("Select field to update");
                                System.out.println("1. Name");
                                System.out.println("2. Phone Number");
                                System.out.println("3. E-mail");
                                System.out.println("4. Address");
                                System.out.println("5. Device Info");
                                System.out.println("6. Repair Part");
                                System.out.println("7. Error Message");
                                System.out.println("8. Service provider name");
                                System.out.println("9. Exit");
                                System.out.print("Enter your choice(Integer only): ");
                                int updateChoice = new Scanner(System.in).nextInt();

                                String updateField = "";
                                String newValue = "";

                                switch (updateChoice){
                                    case 1:
                                        updateField = "Customer_name";
                                        System.out.print("Enter the new Customer name: ");
                                        newValue = new Scanner(System.in).nextLine();
                                        break;
                                    case 2:
                                        updateField = "Customer_ph_number";
                                        System.out.print("Enter the new Customer Phone Number: ");
                                        newValue = new Scanner(System.in).nextLine();
                                        break;
                                    case 3:
                                        updateField = "Customer_email";
                                        System.out.print("Enter the new Customer E-mail: ");
                                        newValue = new Scanner(System.in).nextLine();
                                        break;
                                    case 4:
                                        updateField = "Customer_address";
                                        System.out.print("Enter the new Customer Address: ");
                                        newValue = new Scanner(System.in).nextLine();
                                        break;
                                    case 5:
                                        updateField = "Device_information";
                                        System.out.print("Enter the new Device Information: ");
                                        newValue = new Scanner(System.in).nextLine();
                                        break;
                                    case 6:
                                        updateField = "Repair_part";
                                        System.out.print("Enter the new Repair Part: ");
                                        newValue = new Scanner(System.in).nextLine();
                                        break;
                                    case 7:
                                        updateField = "Error_message";
                                        System.out.print("Enter the new Error Message: ");
                                        newValue = new Scanner(System.in).nextLine();
                                        break;
                                    case 8:
                                        updateField = "Service_provider_name";
                                        System.out.print("Enter the new Customer name: ");
                                        newValue = new Scanner(System.in).nextLine();
                                        break;
                                    case 9:
                                        System.exit(0);
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again.");
                                        break;
                                }
                                if (!updateField.isEmpty()) {
                                    String updateQuery = "UPDATE Customer_Information_And_Repair_Part SET " + updateField + " = ? WHERE id = ?";
                                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                                    updateStatement.setString(1,newValue);
                                    updateStatement.setInt(2, cusInfoIdToUpdate);
                                    int rowsUpdated = updateStatement.executeUpdate();

                                    if (rowsUpdated > 0) {
                                        System.out.println("Customer information updated successfully!");
                                        System.out.println(" ");
                                    } else {
                                        System.out.println("Failed to update information.");
                                        System.out.println(" ");
                                    }
                                }
                                break;
                            case 5:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                System.out.println(" ");
                                break;
                        }
                        break;
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println(" ");
                        System.out.println("Service Provider Information Menu");
                        System.out.println("1. Add Service Provider Information");
                        System.out.println("2. List All Information");
                        System.out.println("3. Update Service Provider Information");
                        System.out.println("4. Exit");
                        System.out.print("Enter your choice(Integer only): ");

                        int choice1 = new Scanner(System.in).nextInt();

                        switch (choice1){
                            case 1:
                                System.out.print("Enter Service Provider Name: ");
                                String serviceProviderName = new Scanner(System.in).nextLine();
                                System.out.print("Enter Service Provider Phone Number: ");
                                String phoneNumber = new Scanner(System.in).nextLine();
                                System.out.print("Enter Service Provider Address: ");
                                String address = new Scanner(System.in).nextLine();
                                LocalDateTime now = LocalDateTime.now();
                                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                                String formatDateTime = now.format(dateTimeFormatter);
                                String insertQuery = "INSERT INTO Service_Provider_Information " +
                                        "( Service_provider_name, Service_provider_ph_number, Service_provider_address, Service_provider_start_working_date)" +
                                        "VALUES(? ,? ,? ,?)";
                                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                                insertStatement.setString(1,serviceProviderName);
                                insertStatement.setString(2,phoneNumber);
                                insertStatement.setString(3,address);
                                insertStatement.setString(4,String.valueOf(formatDateTime));
                                insertStatement.executeUpdate();
                                System.out.println("Information added Successfully!");
                                System.out.println(" ");
                                break;
                            case 2:
                                ResultSet resultSet = statement.executeQuery("SELECT * FROM Service_Provider_Information");
                                while (resultSet.next()){
                                    System.out.println("ID: " + resultSet.getString("Id"));
                                    System.out.println("Service Provider Name: " + resultSet.getString("Service_provider_name"));
                                    System.out.println("Service Provider Phone Number: " + resultSet.getString("Service_provider_ph_number"));
                                    System.out.println("Service Provider Address: "+ resultSet.getString("Service_provider_address"));
                                    System.out.println("Start Working Date: "+ resultSet.getString("Service_provider_start_working_date"));
                                    System.out.println(" ");
                                }
                                break;
                            case 3:
                                System.out.print("Enter the ID of the service provider to update: ");
                                int serviceProviderInfoIdToUpdate = new Scanner(System.in).nextInt();

                                ResultSet checkResult = statement.executeQuery("SELECT * FROM Service_Provider_Information WHERE id = " + serviceProviderInfoIdToUpdate);
                                if (!checkResult.next()) {
                                    System.out.println("Service provider with ID " + serviceProviderInfoIdToUpdate + " does not exist.");
                                    System.out.println(" ");
                                    break;
                                }
                                System.out.println(" ");
                                System.out.println("Select field to update");
                                System.out.println("1. Name");
                                System.out.println("2. Phone Number");
                                System.out.println("3. Address");
                                System.out.print("Enter your choice(Integer only): ");
                                int updateChoice = new Scanner(System.in).nextInt();
                                String updateField = "";
                                String newValue = "";
                                switch (updateChoice) {
                                    case 1:
                                        updateField = "Service_provider_name";
                                        System.out.print("Enter the new service provider name: ");
                                        newValue = new Scanner(System.in).nextLine();
                                        break;
                                    case 2:
                                        updateField = "Service_provider_ph_number";
                                        System.out.print("Enter the new service provider Phone Number: ");
                                        newValue = new Scanner(System.in).nextLine();
                                        break;
                                    case 3:
                                        updateField = "Service_provider_address";
                                        System.out.print("Enter the new address : ");
                                        newValue = new Scanner(System.in).nextLine();
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again.");
                                        break;
                                }
                                if (!updateField.isEmpty()) {
                                    String updateQuery = "UPDATE Service_Provider_Information SET " + updateField + " = ? WHERE id = ?";
                                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                                    updateStatement.setString(1, newValue);
                                    updateStatement.setInt(2, serviceProviderInfoIdToUpdate);
                                    int rowsUpdated = updateStatement.executeUpdate();

                                    if (rowsUpdated > 0) {
                                        System.out.println("Service provider information updated successfully!");
                                        System.out.println(" ");
                                    } else {
                                        System.out.println("Failed to update information.");
                                        System.out.println(" ");
                                    }
                                    break;
                                }
                                break;
                            case 4:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                System.out.println(" ");
                                break;
                        }
                        break;
                    }
                    break;
                case 3:
                    System.out.println(" ");
                    System.out.println("Mobile Phone Returning Time Information Menu");
                    System.out.println("1. Add Mobile Phone Returning Time Information");
                    System.out.println("2. List All Information");
                    System.out.println("3. Update Mobile Phone Returning Time Information");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice(Integer only): ");

                    int choice1 = new Scanner(System.in).nextInt();
                    switch (choice1){
                        case 1:
                            System.out.print("Enter ID of service receiving time : ");
                            int idOfServiceReceivingTime = new Scanner(System.in).nextInt();
                            System.out.print("Enter Returning Received Customer Name: ");
                            String returningReceivedCustomerName = new Scanner(System.in).nextLine();
                            LocalDateTime now = LocalDateTime.now();
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                            String formatDateTime = now.format(dateTimeFormatter);
                            String insertQuery = "INSERT INTO Phone_Returning_To_Customer " +
                                    "( Id_from_Customer_Information, Returning_Received_Customer_Name, Returning_Time)" +
                                    "VALUES(? ,? ,?)";
                            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                            insertStatement.setInt(1,idOfServiceReceivingTime);
                            insertStatement.setString(2,returningReceivedCustomerName);
                            insertStatement.setString(3,String.valueOf(formatDateTime));
                            insertStatement.executeUpdate();
                            System.out.println("Information added Successfully!");
                            System.out.println(" ");
                            break;
                        case 2:
                            ResultSet resultSet = statement.executeQuery("SELECT * FROM Phone_Returning_To_Customer");
                            while (resultSet.next()){
                                System.out.println("ID of Returning Received Time : " + resultSet.getString("Id"));
                                System.out.println("ID of Service Receiving Time  : " + resultSet.getInt("Id_from_Customer_Information"));
                                System.out.println("Returning Received Customer Name: " + resultSet.getString("Returning_Received_Customer_Name"));
                                System.out.println("Returning Received Time: " + resultSet.getString("Returning_Time"));
                                System.out.println(" ");
                            }
                            break;
                        case 3:
                            System.out.print("Enter the ID of the service provider to update: ");
                            int returningReceivedTimeIdToUpdate = new Scanner(System.in).nextInt();

                            ResultSet checkResult = statement.executeQuery("SELECT * FROM Phone_Returning_To_Customer WHERE id = " + returningReceivedTimeIdToUpdate);
                            if (!checkResult.next()) {
                                System.out.println("Service provider with ID " + returningReceivedTimeIdToUpdate + " does not exist.");
                                System.out.println(" ");
                                break;
                            }
                            System.out.println(" ");
                            System.out.println("Select field to update");
                            System.out.println("1. ID of Service Receiving Time");
                            System.out.println("2. Returning Received Customer Name");
                            System.out.print("Enter your choice(Integer only): ");
                            int updateChoice = new Scanner(System.in).nextInt();
                            String updateField = "";
                            String newValue = "";
                            int newValue1 ;
                            switch (updateChoice) {
                                case 1:
                                    updateField = "Id_from_Customer_Information";
                                    System.out.print("Enter the new Id from Customer Information: ");
                                    newValue = new Scanner(System.in).nextLine();
                                    break;
                                case 2:
                                    updateField = "Returning_Received_Customer_Name";
                                    System.out.print("Enter the new Returning Received Customer Name: ");
                                    newValue = new Scanner(System.in).nextLine();
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    System.out.println(" ");
                                    break;
                            }
                            if (!updateField.isEmpty()) {
                                String updateQuery = "UPDATE Phone_Returning_To_Customer SET " + updateField + " = ? WHERE id = ?";
                                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                                if (updateChoice==1){
                                    updateStatement.setInt(1,Integer.parseInt(newValue));
                                }else {
                                    updateStatement.setString(1, newValue);
                                }
                                updateStatement.setInt(2, returningReceivedTimeIdToUpdate);
                                int rowsUpdated = updateStatement.executeUpdate();

                                if (rowsUpdated > 0) {
                                    System.out.println("Phone_Returning_To_Customer updated successfully!");
                                    System.out.println(" ");
                                } else {
                                    System.out.println("Failed to update information.");
                                    System.out.println(" ");
                                }
                                break;
                            }
                            break;
                        case 4:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            System.out.println(" ");
                            break;
                    }
                    break;
                case 4:
                    System.out.println(" ");
                    System.out.println("Choice Info You Want to Update");
                    System.out.println("1. E-mail");
                    System.out.println("2. Password");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice(Integer only): ");

                    int choice2 = new Scanner(System.in).nextInt();

                    switch (choice2){
                        case 1:
                            String newValue;
                            String oldEmail = email;
                            System.out.print("Enter new E-mail: ");
                            newValue = new Scanner(System.in).nextLine();
                            String updateQuery = "UPDATE User_Table SET email = ? WHERE email = ?";
                            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                            updateStatement.setString(1,newValue);
                            updateStatement.setString(2,oldEmail);
                            int rowsUpdated = updateStatement.executeUpdate();

                            if (rowsUpdated > 0) {
                                System.out.println("E-mail updated successfully!");
                                System.out.println(" ");
                            } else {
                                System.out.println("Failed to update E-mail.");
                                System.out.println(" ");
                            }
                            break;
                        case 2:
                            String newValue1 = " ";
                            String oldEmail2 = email;
                            System.out.print("Enter new Password: ");
                            newValue = new Scanner(System.in).nextLine();
                            String updateQuery1 = "UPDATE User_Table SET password = ? WHERE email = ?";
                            PreparedStatement updateStatement1 = connection.prepareStatement(updateQuery1);
                            updateStatement1.setString(1,newValue);
                            updateStatement1.setString(2,oldEmail2);
                            int rowsUpdated1 = updateStatement1.executeUpdate();

                            if (rowsUpdated1 > 0) {
                                System.out.println("Password updated successfully!");
                                System.out.println(" ");
                            } else {
                                System.out.println("Failed to update password.");
                                System.out.println(" ");
                            }
                            break;
                        case 3:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            System.out.println(" ");
                            break;
                    }
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println(" ");
                    break;
            }
        }
    }
    public static void registerUser(Connection connection, String email, String password) throws SQLException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        String insertQuery = "INSERT INTO User_Table (email, password) VALUES (?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setString(1, email);
            insertStatement.setString(2, hashedPassword);
            insertStatement.executeUpdate();
        }
    }

    public static boolean loginUser(Connection connection, String email, String password) throws SQLException {
        String selectQuery = "SELECT password FROM User_Table WHERE email = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setString(1, email);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPasswordHash = resultSet.getString("password");
                    return BCrypt.checkpw(password, storedPasswordHash);
                }
            }
        }
        return false;
    }

    public static String email = "";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(url,userName, "");
            Statement statement = connection.createStatement();
//            Class.forName("com.mysql.cj.jdbc.Driver");

            //create table if it doesn't exist
            statement.execute("CREATE TABLE IF NOT EXISTS Customer_Information_And_Repair_Part " +
                    "(Id INT AUTO_INCREMENT PRIMARY KEY," +
                    "Customer_name VARCHAR(100)," +
                    "Customer_ph_number VARCHAR(50)," +
                    "Customer_email VARCHAR(50)," +
                    "Customer_address VARCHAR(200)," +
                    "Device_information TEXT," +
                    "Repair_part VARCHAR(50)," +
                    "Error_message TEXT," +
                    "Service_provider_name VARCHAR(100)," +
                    "Service_receive_time TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS Service_Provider_Information" +
                    "(Id INT AUTO_INCREMENT PRIMARY KEY," +
                    "Service_provider_name VARCHAR(100)," +
                    "Service_provider_ph_number VARCHAR(300)," +
                    "Service_provider_address VARCHAR(300)," +
                    "Service_provider_start_working_date VARCHAR(50))");
            statement.execute("CREATE TABLE IF NOT EXISTS Phone_Returning_To_Customer " +
                    "(Id INT AUTO_INCREMENT PRIMARY KEY," +
                    "Id_from_Customer_Information INT," +
                    "Returning_Received_Customer_Name VARCHAR(100)," +
                    "Returning_Time TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS User_Table " +
                    "(Id INT AUTO_INCREMENT PRIMARY KEY," +
                    "email VARCHAR(100)," +
                    "password VARCHAR(100))");



            while (true) {
                System.out.println("Mobile Phone Service Menu");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.print("Enter your choice(Integer only): ");

                int authChoice = new Scanner(System.in).nextInt();

                switch (authChoice) {
                    case 1:
                        System.out.print("Enter E-mail: ");
                        email = new Scanner(System.in).nextLine();
                        System.out.print("Enter password: ");
                        String password = new Scanner(System.in).nextLine();
                        boolean isAuthenticated = loginUser(connection, email, password);
                        if (isAuthenticated) {
                            System.out.println("Login successful!");
                            runMobilePhoneService(connection, statement);
                        } else {
                            System.out.println("Login failed. Please try again.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter E-mail: ");
                        email = new Scanner(System.in).nextLine();
                        System.out.print("Enter password: ");
                        password = new Scanner(System.in).nextLine();
                        registerUser(connection, email, password);
                        System.out.println("Registration successful!");
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}