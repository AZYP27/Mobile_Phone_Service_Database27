-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 23, 2023 at 09:47 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mobile_phone_service_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer_information_and_repair_part`
--

CREATE TABLE `customer_information_and_repair_part` (
  `Id` int(11) NOT NULL,
  `Customer_name` varchar(100) DEFAULT NULL,
  `Customer_ph_number` varchar(50) DEFAULT NULL,
  `Customer_email` varchar(50) DEFAULT NULL,
  `Customer_address` varchar(200) DEFAULT NULL,
  `Device_information` text DEFAULT NULL,
  `Repair_part` varchar(50) DEFAULT NULL,
  `Error_message` text DEFAULT NULL,
  `Service_provider_name` varchar(100) DEFAULT NULL,
  `Service_receive_time` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer_information_and_repair_part`
--

INSERT INTO `customer_information_and_repair_part` (`Id`, `Customer_name`, `Customer_ph_number`, `Customer_email`, `Customer_address`, `Device_information`, `Repair_part`, `Error_message`, `Service_provider_name`, `Service_receive_time`) VALUES
(2, 'Aung Zayar Phyo', 'aunglayazyp.aungzayarphyo@gmail.com', '09970069040', 'Q-238, U Wisara Road, 41Ward, North Dagon, Yangon.', 'iPhoneX', 'Display', 'Green Display Error', 'AZYP', '22-09-2023 01:54:37');

-- --------------------------------------------------------

--
-- Table structure for table `phone_returning_to_customer`
--

CREATE TABLE `phone_returning_to_customer` (
  `Id` int(11) NOT NULL,
  `Id_from_Customer_Information` int(11) DEFAULT NULL,
  `Returning_Received_Customer_Name` varchar(100) DEFAULT NULL,
  `Returning_Time` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phone_returning_to_customer`
--

INSERT INTO `phone_returning_to_customer` (`Id`, `Id_from_Customer_Information`, `Returning_Received_Customer_Name`, `Returning_Time`) VALUES
(2, 2, 'Aung Lay', '22-09-2023 02:14:11');

-- --------------------------------------------------------

--
-- Table structure for table `phone_returning_to_customer_time`
--

CREATE TABLE `phone_returning_to_customer_time` (
  `Id` int(11) NOT NULL,
  `Id_from_Customer_Information` int(11) DEFAULT NULL,
  `Returning_Received_Customer_Name` varchar(100) DEFAULT NULL,
  `Returning_Time` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `service_provider_information`
--

CREATE TABLE `service_provider_information` (
  `Id` int(11) NOT NULL,
  `Service_provider_name` varchar(100) DEFAULT NULL,
  `Service_provider_ph_number` varchar(300) DEFAULT NULL,
  `Service_provider_address` varchar(300) DEFAULT NULL,
  `Service_provider_start_working_date` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `service_provider_information`
--

INSERT INTO `service_provider_information` (`Id`, `Service_provider_name`, `Service_provider_ph_number`, `Service_provider_address`, `Service_provider_start_working_date`) VALUES
(1, 'Aung Gyi', '09426938859', 'Q-238, U Wisara Road, 41Ward, North Dagon, Yangon.', '22-09-2023 01:57:25');

-- --------------------------------------------------------

--
-- Table structure for table `user_table`
--

CREATE TABLE `user_table` (
  `Id` int(11) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`Id`, `email`, `password`) VALUES
(2, 'aunglayazyp.aungzayarphyo@gmail.com', '$2a$10$eM6Zb98P9SrsaZaruITTWeU8aHUkwQZMklcjm6Mf04W9IZdwNg80q'),
(3, 'minnkhantko@gamil.com', '$2a$10$GonFV6eFKEGs72MRrfhTTuMFo.Jma2w0mmUW26I9wn81rW.9TJnQe');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer_information_and_repair_part`
--
ALTER TABLE `customer_information_and_repair_part`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `phone_returning_to_customer`
--
ALTER TABLE `phone_returning_to_customer`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `phone_returning_to_customer_time`
--
ALTER TABLE `phone_returning_to_customer_time`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `service_provider_information`
--
ALTER TABLE `service_provider_information`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer_information_and_repair_part`
--
ALTER TABLE `customer_information_and_repair_part`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `phone_returning_to_customer`
--
ALTER TABLE `phone_returning_to_customer`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `phone_returning_to_customer_time`
--
ALTER TABLE `phone_returning_to_customer_time`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `service_provider_information`
--
ALTER TABLE `service_provider_information`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
