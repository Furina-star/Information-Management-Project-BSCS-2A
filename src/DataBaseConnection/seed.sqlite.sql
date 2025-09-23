PRAGMA foreign_keys = ON;
BEGIN;

INSERT INTO subject (SubjectID, SubjectName, SubjectCode, Units, Credits) VALUES
(23, 'Infoman', '101', '24', 3),
(25, 'Contemporary World', '102', '3', 1),
(26, 'Cloud Computing', '103', '3', 1);

INSERT INTO student (StudentID, LastName, FirstName, MiddleName, Program, YearLevel, Section, Barangay, City, Province, Country, Contact_number, Username, Password, ProfilePhoto) VALUES
(4, 'Bello', 'Michael', 'L', 'BSCS', '2', 'A', 'A', 'F', 'S', 'RE', '09876653312', 'BELLO', 'BEL', ''),
(6, 'Torres', 'Lean', 'A', 'Bachelor of Science in Computer Science', '2', 'A', 'IDK', 'San Juan', 'Ilocos Sur', 'Philippines', '09995429546', 'Lean', 'lean', NULL),
(7, 'Rabo', 'Zedric', 'Z', 'Bachelor in Science of Nigga Science', '2', 'A', 'IDK', 'Bantay', 'Ilocos Sur', 'Philippines', '091234567890', 'Zedric', 'zed', NULL);

INSERT INTO admin (Admin_ID, Admin_LName, Admin_MName, Admin_FName, Barangay, City, Province, Country, Admin_Contact_Number, Admin_Email, Username, Admin_Password, Admin_Profile_Picture) VALUES
(1, 'Dom', 'Au', 'Lau', 'Nag', 'Ga', 'LU', 'lu', '09876543321', '@gmail.com', 'ngf', 'ns', 'profile_pics/student_ngf.jpg'),
(2, 'Bello', 'B', 'Michael', 'Rizal', 'Santa', 'Ilocos Sur', 'Philippines', '09283831883', 'hidenoritabata755@gmail.com', 'Furina', 'furina', 'profile_pics/student_Furina.jpg');

INSERT INTO student_subject (StudentID, SubjectID) VALUES
(4, 23),
(4, 25),
(6, 26);

INSERT INTO assessment (AssessmentID, SubjectID, Title, Type, MaxScore, DateGiven) VALUES
(5, 23, 'Quiz1', 'Exam', 100, '2025-09-29'),
(6, 25, 'Activity#4', 'Quiz', 100, '2025-09-27'),
(7, 26, 'Mid-Terms Examination', 'Exam', 50, '2025-09-27');

INSERT INTO assessmentresult (ResultID, StudentID, AssessmentID, Score, DateTaken) VALUES
(5, 4, 5, 100, '2025-09-09'),
(8, 6, 7, 1, '2025-09-27'),
(9, 4, 6, 10000, '2025-09-23');

INSERT INTO student_photos (PhotoID, StudentID, PhotoPath, Description) VALUES
(2, 6, 'profile_pics/student_Lean.jpg', 'Profile photo'),
(3, 7, 'profile_pics/student_Zedric.jpg', 'Profile photo');

COMMIT;
