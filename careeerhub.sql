CREATE DATABASE careerhub;
USE careerhub;

CREATE TABLE Companies (
    CompanyID INT PRIMARY KEY AUTO_INCREMENT,
    CompanyName VARCHAR(100) NOT NULL,
    Location VARCHAR(100)
);

CREATE TABLE Jobs (
    JobID INT PRIMARY KEY AUTO_INCREMENT,
    CompanyID INT,
    JobTitle VARCHAR(100) NOT NULL,
    JobDescription TEXT,
    JobLocation VARCHAR(100),
    Salary DECIMAL(10,2),
    JobType VARCHAR(50),
    PostedDate DATE,
    FOREIGN KEY (CompanyID) REFERENCES Companies(CompanyID)
);

CREATE TABLE Applicants (
    ApplicantID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Phone VARCHAR(20),
    Resume TEXT
);

CREATE TABLE Applications (
    ApplicationID INT PRIMARY KEY AUTO_INCREMENT,
    JobID INT,
    ApplicantID INT,
    ApplicationDate DATETIME,
    CoverLetter TEXT,
    FOREIGN KEY (JobID) REFERENCES Jobs(JobID),
    FOREIGN KEY (ApplicantID) REFERENCES Applicants(ApplicantID)
);

-- Insert companies
INSERT INTO Companies (CompanyName, Location) VALUES
('TechCorp', 'Bangalore'),
('Innovate Solutions', 'Chennai'),
('GreenTech Industries', 'Mumbai');

-- Insert jobs
INSERT INTO Jobs (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES
(1, 'Software Engineer', 'Develop and maintain software applications', 'Bangalore', 100000.00, 'Full-time', '2024-11-15'),
(2, 'Data Analyst', 'Analyze and interpret complex data', 'Chennai', 90000.00, 'Full-time', '2024-11-10'),
(3, 'Product Manager', 'Oversee product development and strategy', 'Mumbai', 120000.00, 'Full-time', '2024-11-12');

-- Insert applicants
INSERT INTO Applicants (FirstName, LastName, Email, Phone, Resume) VALUES
('John', 'Doe', 'john.doe@example.com', '9876543210', 'Resume text for John Doe'),
('Jane', 'Smith', 'jane.smith@example.com', '9876543211', 'Resume text for Jane Smith'),
('Michael', 'Johnson', 'michael.johnson@example.com', '9876543212', 'Resume text for Michael Johnson');

-- Insert applications
INSERT INTO Applications (JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES
(1, 1, '2024-11-16 10:00:00', 'Cover letter for John Doe for Software Engineer position'),
(2, 2, '2024-11-17 11:00:00', 'Cover letter for Jane Smith for Data Analyst position'),
(3, 3, '2024-11-18 12:00:00', 'Cover letter for Michael Johnson for Product Manager position');
