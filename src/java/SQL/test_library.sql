-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 20, 2016 at 05:17 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test_library`
--
CREATE DATABASE IF NOT EXISTS `test_library` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test_library`;

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
CREATE TABLE `loan` (
  `loan_id` int(11) NOT NULL,
  `title_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `withdraw_date` date NOT NULL,
  `return_by_date` date NOT NULL,
  `is_returned` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loan`
--

INSERT INTO `loan` (`loan_id`, `title_id`, `user_id`, `withdraw_date`, `return_by_date`, `is_returned`) VALUES
(10, 2, 8, '2016-11-20', '2016-11-27', 1),
(11, 3, 8, '2016-11-20', '2016-11-27', 1),
(12, 2, 8, '2016-11-20', '2016-11-27', 1),
(13, 2, 8, '2016-11-20', '2016-11-27', 1),
(14, 3, 8, '2016-11-20', '2016-11-27', 1),
(15, 2, 8, '2016-11-20', '2016-11-27', 1),
(16, 2, 8, '2016-11-20', '2016-11-27', 1),
(18, 3, 8, '2016-11-20', '2016-11-27', 1),
(19, 2, 8, '2016-11-20', '2016-11-27', 1),
(20, 2, 8, '2016-11-20', '2016-11-27', 1),
(22, 3, 8, '2016-11-20', '2016-11-27', 1),
(23, 2, 8, '2016-11-20', '2016-11-27', 1),
(24, 2, 8, '2016-11-20', '2016-11-27', 0);

-- --------------------------------------------------------

--
-- Table structure for table `title`
--

DROP TABLE IF EXISTS `title`;
CREATE TABLE `title` (
  `title_id` int(11) NOT NULL,
  `book_title` varchar(150) NOT NULL,
  `author` varchar(100) NOT NULL,
  `publisher` varchar(100) NOT NULL,
  `year_published` int(4) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `title`
--

INSERT INTO `title` (`title_id`, `book_title`, `author`, `publisher`, `year_published`, `stock`) VALUES
(2, 'Harry Potter and the Philosopher''s Stone', 'J.K.Rowling', 'Bloomsbury', 1997, 15),
(3, 'To Kill a MockingBird', 'Harper Lee', 'J. B. Lippincott & Co.', 1960, 10);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `pass` varchar(64) NOT NULL,
  `salt` varchar(64) NOT NULL,
  `f_name` varchar(50) NOT NULL,
  `l_name` varchar(50) NOT NULL,
  `last_password_change` date NOT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `email`, `pass`, `salt`, `f_name`, `l_name`, `last_password_change`, `is_admin`) VALUES
(8, 'admin', 'admin@library.com', 'L7q9PMoQ/iSd0XXVv3rX829CyMSS9Ha4rkrVuwnvQZ0=', '9J6kIsveP+qFZ/c+dn+/sByb4dWd3deO/4Ac9gSFZk8=', 'admin', 'admin', '2016-11-01', 0);

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--
DROP TABLE IF EXISTS `permissions`
CREATE TABLE `permissions` (
	`role_type` tinyint(1) NOT NULL,
	`permission` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permissions`
--
INSERT INTO `permissions` (`role_type`, `permission`) VALUES
(1,'sign_up'),
(1,'login'),
(1,'view_titles'),
(1,'view_current_loan'),
(1,'add_loan'),
(1,'return_loan'),
(0,'sign_up'),
(0,'login'),
(0,'view_titles'),
(0,'view_current_loan'),
(0,'add_loan'),
(0,'return_loan'),
(0,'add_title'),
(0,'edit_title'),
(0,'add_remove_copy'),
(0,'delete_title'),
(0,'delete_user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`loan_id`),
  ADD KEY `title_to_loan_fk` (`title_id`),
  ADD KEY `user_to_loan_fk` (`user_id`);

--
-- Indexes for table `title`
--
ALTER TABLE `title`
  ADD PRIMARY KEY (`title_id`),
  ADD UNIQUE KEY `book_title_uq` (`book_title`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username_uq` (`username`),
  ADD UNIQUE KEY `email_uq` (`email`);

--
-- Indexes for table `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`role_type`, `permission`);
  
  
--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `loan`
--
ALTER TABLE `loan`
  MODIFY `loan_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `title`
--
ALTER TABLE `title`
  MODIFY `title_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `title_to_loan_fk` FOREIGN KEY (`title_id`) REFERENCES `title` (`title_id`),
  ADD CONSTRAINT `user_to_loan_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
