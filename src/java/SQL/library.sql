-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 17, 2016 at 03:35 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--
CREATE DATABASE IF NOT EXISTS `library` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `library`;

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
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `loan`
--
ALTER TABLE `loan`
  MODIFY `loan_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `title`
--
ALTER TABLE `title`
  MODIFY `title_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;
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
