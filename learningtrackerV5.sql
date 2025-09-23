-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 23, 2025 at 01:12 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `learningtracker`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `Admin_ID` int(11) NOT NULL,
  `Admin_LName` varchar(60) NOT NULL,
  `Admin_MName` varchar(60) NOT NULL,
  `Admin_FName` varchar(60) NOT NULL,
  `Barangay` varchar(255) NOT NULL,
  `City` varchar(255) NOT NULL,
  `Province` varchar(255) NOT NULL,
  `Country` varchar(255) NOT NULL,
  `Admin_Contact_Number` varchar(255) NOT NULL,
  `Admin_Email` varchar(100) NOT NULL,
  `Username` varchar(100) NOT NULL,
  `Admin_Password` varchar(24) NOT NULL,
  `Admin_Profile_Picture` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Admin_ID`, `Admin_LName`, `Admin_MName`, `Admin_FName`, `Barangay`, `City`, `Province`, `Country`, `Admin_Contact_Number`, `Admin_Email`, `Username`, `Admin_Password`, `Admin_Profile_Picture`) VALUES
(1, 'Dom', 'Au', 'Lau', 'Nag', 'Ga', 'LU', 'lu', '09876543321', '@gmail.com', 'ngf', 'ns', 'profile_pics/student_ngf.jpg'),
(2, 'Bello', 'B', 'Michael', 'Rizal', 'Santa', 'Ilocos Sur', 'Philippines', '09283831883', 'hidenoritabata755@gmail.com', 'Furina', 'furina', 'profile_pics/student_Furina.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `assessment`
--

CREATE TABLE `assessment` (
  `AssessmentID` int(11) NOT NULL COMMENT 'Unique Identifier',
  `SubjectID` int(11) NOT NULL COMMENT 'References Subject table',
  `Title` varchar(100) NOT NULL COMMENT 'Name of the Assessment (e.g., Quiz 1, Midterm Exam)',
  `Type` enum('Quiz','Exam','Assignment','Project') NOT NULL COMMENT 'Type of Assessment',
  `MaxScore` int(100) NOT NULL COMMENT 'Maximum possible score',
  `DateGiven` date NOT NULL COMMENT 'Date the assessment was given'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `assessment`
--

INSERT INTO `assessment` (`AssessmentID`, `SubjectID`, `Title`, `Type`, `MaxScore`, `DateGiven`) VALUES
(5, 23, 'Quiz1', 'Exam', 100, '2025-09-29'),
(6, 25, 'Activity#4', 'Quiz', 100, '2025-09-27'),
(7, 26, 'Mid-Terms Examination', 'Exam', 50, '2025-09-27');

-- --------------------------------------------------------

--
-- Table structure for table `assessmentresult`
--

CREATE TABLE `assessmentresult` (
  `ResultID` int(11) NOT NULL,
  `StudentID` int(11) NOT NULL,
  `AssessmentID` int(11) NOT NULL,
  `Score` int(100) NOT NULL,
  `DateTaken` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `assessmentresult`
--

INSERT INTO `assessmentresult` (`ResultID`, `StudentID`, `AssessmentID`, `Score`, `DateTaken`) VALUES
(5, 4, 5, 100, '2025-09-09'),
(8, 6, 7, 1, '2025-09-27'),
(9, 4, 6, 10000, '2025-09-23');

-- --------------------------------------------------------

--
-- Table structure for table `progress`
--

CREATE TABLE `progress` (
  `ProgressID` int(11) NOT NULL COMMENT 'Unique Identifier',
  `StudentID` int(11) NOT NULL COMMENT 'References Student table',
  `SubjectID` int(11) NOT NULL COMMENT 'References Subject table',
  `Score` decimal(5,2) DEFAULT NULL COMMENT 'Grade/Score',
  `Remarks` varchar(255) DEFAULT NULL COMMENT 'Feedback/Remarks',
  `DateRecorded` date NOT NULL COMMENT 'When this progress was recorded'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `StudentID` int(11) NOT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `FirstName` varchar(50) NOT NULL,
  `MiddleName` varchar(50) DEFAULT NULL,
  `Program` varchar(100) NOT NULL,
  `YearLevel` varchar(20) DEFAULT NULL,
  `Section` varchar(20) NOT NULL,
  `Barangay` varchar(100) NOT NULL,
  `City` varchar(100) NOT NULL,
  `Province` varchar(100) NOT NULL,
  `Country` varchar(100) NOT NULL,
  `Contact_number` varchar(20) NOT NULL,
  `Username` varchar(100) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `ProfilePhoto` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`StudentID`, `LastName`, `FirstName`, `MiddleName`, `Program`, `YearLevel`, `Section`, `Barangay`, `City`, `Province`, `Country`, `Contact_number`, `Username`, `Password`, `ProfilePhoto`) VALUES
(4, 'Bello', 'Michael', 'L', 'BSCS', '2', 'A', 'A', 'F', 'S', 'RE', '09876653312', 'BELLO', 'BEL', ''),
(6, 'Torres', 'Lean', 'A', 'Bachelor of Science in Computer Science', '2', 'A', 'IDK', 'San Juan', 'Ilocos Sur', 'Philippines', '09995429546', 'Lean', 'lean', NULL),
(7, 'Rabo', 'Zedric', 'Z', 'Bachelor in Science of Nigga Science', '2', 'A', 'IDK', 'Bantay', 'Ilocos Sur', 'Philippines', '091234567890', 'Zedric', 'zed', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `student_photos`
--

CREATE TABLE `student_photos` (
  `PhotoID` int(11) NOT NULL,
  `StudentID` int(11) DEFAULT NULL,
  `PhotoPath` varchar(255) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_photos`
--

INSERT INTO `student_photos` (`PhotoID`, `StudentID`, `PhotoPath`, `Description`) VALUES
(2, 6, 'profile_pics/student_Lean.jpg', 'Profile photo'),
(3, 7, 'profile_pics/student_Zedric.jpg', 'Profile photo');

-- --------------------------------------------------------

--
-- Table structure for table `student_subject`
--

CREATE TABLE `student_subject` (
  `StudentID` int(11) NOT NULL,
  `SubjectID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_subject`
--

INSERT INTO `student_subject` (`StudentID`, `SubjectID`) VALUES
(4, 23),
(4, 25),
(6, 26);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `SubjectID` int(11) NOT NULL,
  `SubjectName` varchar(100) NOT NULL,
  `SubjectCode` varchar(100) NOT NULL,
  `Units` varchar(100) NOT NULL,
  `Credits` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`SubjectID`, `SubjectName`, `SubjectCode`, `Units`, `Credits`) VALUES
(23, 'Infoman', '101', '24', 3),
(25, 'Contemporary World', '102', '3', 1),
(26, 'Cloud Computing', '103', '3', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`Admin_ID`);

--
-- Indexes for table `assessment`
--
ALTER TABLE `assessment`
  ADD PRIMARY KEY (`AssessmentID`),
  ADD KEY `Assessment_Subject_FK` (`SubjectID`);

--
-- Indexes for table `assessmentresult`
--
ALTER TABLE `assessmentresult`
  ADD PRIMARY KEY (`ResultID`),
  ADD KEY `AssessmentResult_Student_FK` (`StudentID`),
  ADD KEY `AssessmentResult_Assessment_FK` (`AssessmentID`);

--
-- Indexes for table `progress`
--
ALTER TABLE `progress`
  ADD PRIMARY KEY (`ProgressID`),
  ADD KEY `Progress_Student_FK` (`StudentID`),
  ADD KEY `Progress_Subject_FK` (`SubjectID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`StudentID`);

--
-- Indexes for table `student_photos`
--
ALTER TABLE `student_photos`
  ADD PRIMARY KEY (`PhotoID`),
  ADD KEY `StudentID` (`StudentID`);

--
-- Indexes for table `student_subject`
--
ALTER TABLE `student_subject`
  ADD PRIMARY KEY (`StudentID`,`SubjectID`),
  ADD KEY `SubjectID` (`SubjectID`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`SubjectID`),
  ADD UNIQUE KEY `Subject_unique` (`SubjectCode`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `Admin_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `assessment`
--
ALTER TABLE `assessment`
  MODIFY `AssessmentID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique Identifier', AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `assessmentresult`
--
ALTER TABLE `assessmentresult`
  MODIFY `ResultID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `progress`
--
ALTER TABLE `progress`
  MODIFY `ProgressID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique Identifier';

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `StudentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `student_photos`
--
ALTER TABLE `student_photos`
  MODIFY `PhotoID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `SubjectID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assessment`
--
ALTER TABLE `assessment`
  ADD CONSTRAINT `Assessment_Subject_FK` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`SubjectID`) ON DELETE CASCADE;

--
-- Constraints for table `assessmentresult`
--
ALTER TABLE `assessmentresult`
  ADD CONSTRAINT `AssessmentResult_Assessment_FK` FOREIGN KEY (`AssessmentID`) REFERENCES `assessment` (`AssessmentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `AssessmentResult_Student_FK` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `progress`
--
ALTER TABLE `progress`
  ADD CONSTRAINT `Progress_Student_FK` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Progress_Subject_FK` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`SubjectID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student_photos`
--
ALTER TABLE `student_photos`
  ADD CONSTRAINT `student_photos_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student_subject`
--
ALTER TABLE `student_subject`
  ADD CONSTRAINT `student_subject_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_subject_ibfk_2` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`SubjectID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
