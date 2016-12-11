-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2016 at 03:01 PM
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

--
-- Dumping data for table `loan`
--

INSERT INTO `loan` (`loan_id`, `title_id`, `user_id`, `withdraw_date`, `return_by_date`, `is_returned`) VALUES
(10, 1, 1, '2016-10-09', '2016-10-16', 1),
(11, 2, 1, '2016-10-16', '2016-10-23', 1),
(12, 3, 1, '2016-10-23', '2016-10-30', 1),
(13, 4, 1, '2016-10-30', '2016-11-06', 1),
(14, 5, 1, '2016-11-06', '2016-11-13', 1),
(15, 6, 1, '2016-11-13', '2016-11-20', 1),
(16, 7, 1, '2016-11-20', '2016-12-27', 0),
(17, 17, 2, '2016-11-06', '2016-11-13', 1),
(18, 18, 2, '2016-11-13', '2016-11-20', 1),
(19, 19, 2, '2016-11-20', '2016-12-27', 0),
(20, 15, 3, '2016-11-06', '2016-11-13', 1),
(21, 16, 3, '2016-11-13', '2016-11-20', 1),
(22, 20, 3, '2016-11-20', '2016-12-27', 0);
-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `role_type_name` varchar(20) NOT NULL,
  `permission` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `security_questions`
--

DROP TABLE IF EXISTS `security_questions`;
CREATE TABLE `security_questions` (
  `sq_id` int(11) NOT NULL,
  `question` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `security_questions`
--

INSERT INTO `security_questions` (`sq_id`, `question`) VALUES
(1, 'What is your mother''s maiden name?'),
(2, 'What was your first pet''s name?'),
(3, 'What is name of the street you grew up on?'),
(4, 'What was the name of your best friend growing up?'),
(5, 'When you were young, what did you want to be when you grew up?'),
(6, 'What was the name of your elementary / primary school?'),
(7, 'What is your oldest cousin''s first and last name?'),
(8, 'What is the first name of the boy or girl that you first kissed?'),
(9, 'Where were you when you had your first alcoholic drink?'),
(10, 'Who was your childhood hero?'),
(11, 'What was your childhood nickname?'),
(12, 'What is the name of your grandmother''s dog?');

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
(1, 'Harry Potter and the Philosophers Stone', 'J.K.Rowling', 'Bloomsbury', 1997, 15),
(2, 'Harry Potter and the Chamber of Secrets', 'J.K.Rowling', 'Bloomsbury', 1998, 13),
(3, 'Harry Potter and the Prisoner of Azkaban', 'J.K.Rowling', 'Bloomsbury', 1999, 17),
(4, 'Harry Potter and the Goblet of Fire', 'J.K.Rowling', 'Bloomsbury', 2000, 11),
(5, 'Harry Potter and the Order of the Phoenix', 'J.K.Rowling', 'Bloomsbury', 2003, 12),
(6, 'Harry Potter and the Half-Blood Prince', 'J.K.Rowling', 'Bloomsbury', 2005, 9),
(7, 'Harry Potter and the Deathly Hallows', 'J.K.Rowling', 'Bloomsbury', 2007, 7),
(8, 'To Kill a MockingBird', 'Harper Lee', 'J. B. Lippincott & Co.', 1960, 10),
(9, 'The Hobbit', 'J. R. R. Tolkien', 'George Allen & Unwin', 1937, 5),
(10, 'The Fellowship of the Ring', 'J. R. R. Tolkien', 'George Allen & Unwin', 1954, 3),
(11, 'The Two Towers', 'J. R. R. Tolkien', 'George Allen & Unwin', 1954, 3),
(12, 'The Return of the King', 'J. R. R. Tolkien', 'George Allen & Unwin', 1955, 3),
(13, 'Computer Networking', 'James F. Kurose & Keith W. Ross', 'Pearson Education Limited', 2000, 1),
(14, 'Gone with the wind', 'Margaret Mitchell', 'Macmillan Publishers', 1936, 15),
(15, 'Shooting an Elephant', 'George Orwell', 'Klett', 2007, 4),
(16, 'Animal Farm', 'George Orwell', 'Secker and Warburg', 1945, 4),
(17, 'Fall of Giants', 'Ken Follett', 'Pan Macmillan', 2010, 4),
(18, 'Winter of the World', 'Ken Follett', 'Dutton Penguin', 2012, 2),
(19, 'Edge of Eternity', 'Ken Follett', 'Pan Macmillan', 2014, 2),
(20, 'Northern Lights', 'Philip Pullman', 'Scholastic Point', 1995, 4);


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
  `role_type_name` varchar(20) NOT NULL DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `email`, `pass`, `salt`, `f_name`, `l_name`, `last_password_change`, `role_type_name`) VALUES
(1, 'ConnorPakenham', 'connorpakenham@gmail.com', 'E0HzZ57e5KiIqkRBgx1spr3oQpAoPuaTSWRqRDzaSac=', 'JQTj41FV9a94cnbZWPWqydGrqMG77y9sWcCaB/F8tMo=', 'Connor', 'Pakenham', '2016-12-12', 'admin');
INSERT INTO `user` (`user_id`, `username`, `email`, `pass`, `salt`, `f_name`, `l_name`, `last_password_change`, `role_type_name`) VALUES
(2, 'LaurenDelaney', 'ren.delaney@gmail.com', 'Wh3Lnk0vNE97bCMyU3U44CQ/qEAJKtl/cIl1L2Lk3VE=', 'iloizPrHLV+OUx8F42332QnFLxpAYAtwOv7TPdiKhMM=', 'Lauren', 'Delaney', '2016-12-12', 'user');
INSERT INTO `user` (`user_id`, `username`, `email`, `pass`, `salt`, `f_name`, `l_name`, `last_password_change`, `role_type_name`) VALUES
(3, 'fgreiner', 'greinerfriedrich@gmail.com', '7yAudW4R5cguCUdmB/S/nejwpDVNhAsJpur4bXH9OTk=', 'Unmis8bPRL8y98+5vlc6n5moCB+tOywHVTOkKDBa74I=', 'Friedrich', 'Greiner', '2016-12-12', 'user');
-- --------------------------------------------------------

--
-- Table structure for table `user_questions`
--

DROP TABLE IF EXISTS `user_questions`;
CREATE TABLE `user_questions` (
  `sq_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `answer` varchar(64) NOT NULL,
  `salt` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_questions`
--

INSERT INTO `user_questions` (`sq_id`, `user_id`, `answer`, `salt`) VALUES
(1, 1, 'aw2z5YNKyWoxJbHCXgI9nbbMJb79uN3YOKE06cLCKdU=', '28CO3jWTj4A+PhhCrsuE0G4GYJwcJy940rQ9ZtM1etU='),
(2, 2, 'sFBy70wKZQZRo75XC3+PqtZmFEkFdRxNPTH2hPLsXsA=', 'wq9mRmjM9w+U2nTx11+CpH9uetHSGp+QSgsIUe+DepE='),
(11, 3, '3Bo5K7swTPT+xqWU4B4YrHHQpzSU3HpX3U/c0+S1+2I=', '/OF85jUXAfGejqIlcO5U5Co2tFMn3qtyWiWRWZ0FgE8=');

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
-- Indexes for table `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`role_type_name`,`permission`);

--
-- Indexes for table `security_questions`
--
ALTER TABLE `security_questions`
  ADD UNIQUE KEY `sq_id` (`sq_id`);

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
-- AUTO_INCREMENT for table `security_questions`
--
ALTER TABLE `security_questions`
  MODIFY `sq_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `title`
--
ALTER TABLE `title`
  MODIFY `title_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
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
