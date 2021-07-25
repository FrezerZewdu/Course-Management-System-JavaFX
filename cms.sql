-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 18, 2021 at 06:02 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cms`
--

-- --------------------------------------------------------

--
-- Table structure for table `assign`
--

CREATE TABLE `assign` (
  `ID` int(11) NOT NULL,
  `TeachersName` varchar(100) NOT NULL,
  `CourseTitle` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assign`
--

INSERT INTO `assign` (`ID`, `TeachersName`, `CourseTitle`) VALUES
(2, 'Sebagadis ', ' Data communication and computer networking'),
(3, ' d', 'Fundamentals of Programming I');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `CourseTitle` varchar(100) NOT NULL,
  `CourseCode` varchar(25) NOT NULL,
  `Hours` varchar(10) NOT NULL,
  `Cr_Hr` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `CourseTitle`, `CourseCode`, `Hours`, `Cr_Hr`) VALUES
(1, 'Software Component Design', 'SE512', '4', '4'),
(2, 'Fundamentals of Programming I', 'SE1021', '3', '3'),
(3, 'Database Management System', ' SE2061', '4', '3'),
(4, 'Fundamentals of Programming II', ' SE1022', '4', '3'),
(5, 'System Programming', ' SE2044', '3', '3'),
(6, 'Website Development', ' SE3081', '3', '3'),
(7, 'Data communication and computer networking', ' SE3051', '3', '3'),
(8, 'Software Requirments Engineering ', ' SE3082', '4', '3'),
(9, 'Object Oriented Software Requirment Analysis and Design', ' SE2081', '4', '4'),
(10, 'Human Computer Interaction', ' SE3074', '4', '4');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `ID` int(11) NOT NULL,
  `FirstName` varchar(25) NOT NULL,
  `MiddleName` varchar(25) NOT NULL,
  `LastName` varchar(25) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `DOB` varchar(25) NOT NULL,
  `Phone` int(25) NOT NULL,
  `Address` varchar(25) NOT NULL,
  `AcadamicYear` int(4) NOT NULL,
  `Year` varchar(5) NOT NULL,
  `RegType` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`ID`, `FirstName`, `MiddleName`, `LastName`, `Gender`, `DOB`, `Phone`, `Address`, `AcadamicYear`, `Year`, `RegType`) VALUES
(1, 'Sebagadis', 'Kahsay', 'G/Tinsae ', 'Male', '1997-11-14', 924580470, 'A.A', 2021, '3', 'Normal Load'),
(2, 'Abebe', 'Kebede ', 'Abebe', 'Male', '3/8/2021', 911111111, 'A.A', 2021, '2', 'Under Load'),
(3, 'Mmm', 'Sss', 'Aaa', 'Female', '1/5/2021', 911121111, 'BDU', 2021, '3', 'Normal Load');

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `ID` int(11) NOT NULL,
  `FirstName` varchar(25) NOT NULL,
  `MiddleName` varchar(25) NOT NULL,
  `LastName` varchar(25) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Email` varchar(25) NOT NULL,
  `Phone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`ID`, `FirstName`, `MiddleName`, `LastName`, `Gender`, `Email`, `Phone`) VALUES
(1, 'Sebagadis ', 'Kahsay', 'G/Tinsae', 'Male', 'sebexk3@gmail.com', '0924580470'),
(2, 'a', 'a', 'a', 'Male', 'a@gmail.com', '0910000000'),
(3, ' b', ' b', ' b', 'Female', ' b@gmail.com', ' 0911000000'),
(4, ' c', ' c', ' c', 'Male', ' c@gmail.com', '0912000000'),
(5, ' d', ' d', ' d', 'Female', ' d@gmail.com', '0913000000'),
(6, ' e', ' e', ' e', 'Female', ' e@gmail.com', '09140000000'),
(7, ' f', ' f', ' f', 'Male', ' f@gmail.com', '0915000000'),
(8, ' g', ' g', ' g', 'Male', ' g@gmail.com', '0916000000'),
(9, ' h', ' h', ' h', 'Female', ' h@gmail.com', '0917000000');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'sebexk3@gmail.com', 'MTIzNA==');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assign`
--
ALTER TABLE `assign`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `CourseTitle` (`CourseTitle`),
  ADD UNIQUE KEY `CourseCode` (`CourseCode`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Phone` (`Phone`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD UNIQUE KEY `Phone` (`Phone`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assign`
--
ALTER TABLE `assign`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
