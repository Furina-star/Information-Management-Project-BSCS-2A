PRAGMA foreign_keys = ON;
BEGIN;

CREATE TABLE IF NOT EXISTS admin (
  Admin_ID               INTEGER PRIMARY KEY AUTOINCREMENT,
  Admin_LName            TEXT NOT NULL,
  Admin_MName            TEXT NOT NULL,
  Admin_FName            TEXT NOT NULL,
  Barangay               TEXT NOT NULL,
  City                   TEXT NOT NULL,
  Province               TEXT NOT NULL,
  Country                TEXT NOT NULL,
  Admin_Contact_Number   TEXT NOT NULL,
  Admin_Email            TEXT NOT NULL,
  Username               TEXT NOT NULL,
  Admin_Password         TEXT NOT NULL,
  Admin_Profile_Picture  TEXT
);

CREATE TABLE IF NOT EXISTS subject (
  SubjectID    INTEGER PRIMARY KEY AUTOINCREMENT,
  SubjectName  TEXT NOT NULL,
  SubjectCode  TEXT NOT NULL,
  Units        TEXT NOT NULL,
  Credits      INTEGER,
  UNIQUE (SubjectCode)
);

CREATE TABLE IF NOT EXISTS student (
  StudentID       INTEGER PRIMARY KEY AUTOINCREMENT,
  LastName        TEXT,
  FirstName       TEXT NOT NULL,
  MiddleName      TEXT,
  Program         TEXT NOT NULL,
  YearLevel       TEXT,
  Section         TEXT NOT NULL,
  Barangay        TEXT NOT NULL,
  City            TEXT NOT NULL,
  Province        TEXT NOT NULL,
  Country         TEXT NOT NULL,
  Contact_number  TEXT NOT NULL,
  Username        TEXT NOT NULL,
  Password        TEXT NOT NULL,
  ProfilePhoto    TEXT
);

CREATE TABLE IF NOT EXISTS assessment (
  AssessmentID  INTEGER PRIMARY KEY AUTOINCREMENT,
  SubjectID     INTEGER NOT NULL,
  Title         TEXT NOT NULL,
  Type          TEXT NOT NULL CHECK (Type IN ('Quiz','Exam','Assignment','Project')),
  MaxScore      INTEGER NOT NULL,
  DateGiven     TEXT NOT NULL,
  FOREIGN KEY (SubjectID) REFERENCES subject(SubjectID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS assessmentresult (
  ResultID      INTEGER PRIMARY KEY AUTOINCREMENT,
  StudentID     INTEGER NOT NULL,
  AssessmentID  INTEGER NOT NULL,
  Score         INTEGER NOT NULL,
  DateTaken     TEXT NOT NULL,
  FOREIGN KEY (StudentID) REFERENCES student(StudentID) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (AssessmentID) REFERENCES assessment(AssessmentID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS progress (
  ProgressID    INTEGER PRIMARY KEY AUTOINCREMENT,
  StudentID     INTEGER NOT NULL,
  SubjectID     INTEGER NOT NULL,
  Score         NUMERIC,
  Remarks       TEXT,
  DateRecorded  TEXT NOT NULL,
  FOREIGN KEY (StudentID) REFERENCES student(StudentID) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (SubjectID) REFERENCES subject(SubjectID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS student_photos (
  PhotoID     INTEGER PRIMARY KEY AUTOINCREMENT,
  StudentID   INTEGER,
  PhotoPath   TEXT,
  Description TEXT,
  FOREIGN KEY (StudentID) REFERENCES student(StudentID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS student_subject (
  StudentID  INTEGER NOT NULL,
  SubjectID  INTEGER NOT NULL,
  PRIMARY KEY (StudentID, SubjectID),
  FOREIGN KEY (StudentID) REFERENCES student(StudentID) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (SubjectID) REFERENCES subject(SubjectID)
);

CREATE INDEX IF NOT EXISTS idx_assessment_subject           ON assessment(SubjectID);
CREATE INDEX IF NOT EXISTS idx_assessmentresult_student     ON assessmentresult(StudentID);
CREATE INDEX IF NOT EXISTS idx_assessmentresult_assessment  ON assessmentresult(AssessmentID);
CREATE INDEX IF NOT EXISTS idx_progress_student             ON progress(StudentID);
CREATE INDEX IF NOT EXISTS idx_progress_subject             ON progress(SubjectID);
CREATE INDEX IF NOT EXISTS idx_student_photos_student       ON student_photos(StudentID);
CREATE INDEX IF NOT EXISTS idx_student_subject_subject      ON student_subject(SubjectID);

COMMIT;
