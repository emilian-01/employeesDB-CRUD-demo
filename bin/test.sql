-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2019 at 07:39 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `EMPLOYEE_ID` int(11) NOT NULL,
  `FIRST_NAME` varchar(20) NOT NULL,
  `LAST_NAME` varchar(20) NOT NULL,
  `EMAIL` varchar(20) NOT NULL,
  `PHONE_NUMBER` varchar(20) NOT NULL,
  `HIRE_DATE` date NOT NULL,
  `JOB_ID` varchar(20) NOT NULL,
  `SALARY` int(16) NOT NULL,
  `COMMISSION_PCT` double NOT NULL,
  `MANAGER_ID` int(16) NOT NULL,
  `DEPARTMENT_ID` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`EMPLOYEE_ID`, `FIRST_NAME`, `LAST_NAME`, `EMAIL`, `PHONE_NUMBER`, `HIRE_DATE`, `JOB_ID`, `SALARY`, `COMMISSION_PCT`, `MANAGER_ID`, `DEPARTMENT_ID`) VALUES
(1, 'asdf', 'asdf', 'nona@hotmail.com', '64132', '1995-06-19', '54', 700, 7, 2, 4),
(2, 'mira', 'mira', 'mira@gmail.com', '475468513468', '1998-02-01', '123', 999999, 100, 1, 2),
(3, 'ani', 'ani', 'ani@hotmail.com', '4786514689', '2005-05-06', '984', 0, 0, 2, 1),
(4, 'lomi', 'lomi', 'lomi@OTR_ME_ZEMER', '0900221514', '1997-01-01', '46', 200, 2, 1, 4),
(6, 'tom', 'tom', 'tom@gamil.com', '56568', '2015-04-08', '58', 2634, 8, 6, 2),
(19, 'nona', 'nona', 'nona@gmail.com', '45668', '2007-06-07', '123', 17350, 9, 3, 215),
(21, 'beni', 'aha', 'we@gmail.com', '49615', '2010-05-05', '54', 500, 1, 7, 6),
(22, 'noizy', 'otr', 'noizy@gmail.com', '54887', '2011-11-07', '13', 6150, 3, 9, 75),
(23, 'qwer', 'asdf', 'werwq@live.com', '5648894', '1993-03-26', '632', 500, 5, 2, 3),
(24, 'jkavs', 'ewfjkn', 'adn@live.com', '894156', '2017-03-04', '24', 590014, 7, 2410, 245),
(25, 'alma', 'alma', 'alma@yahoo.com', '646543', '2019-06-06', '2', 74789, 15, 12, 7),
(26, 'smira', 'ab', 'ab@live.com', '894156', '2017-03-04', '24', 590014, 7, 2410, 245),
(27, 'fe', 'df', 'ehr', '695985', '2015-05-03', '77', 361296, 16, 87, 82);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`EMPLOYEE_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `EMPLOYEE_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
