-- Create the database
CREATE DATABASE IF NOT EXISTS jobboarddb;
USE jobboarddb;

-- Create Applicants table
CREATE TABLE Applicants (
    ApplicantID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Phone VARCHAR(15),
    Resume TEXT
);

-- Create Companies table
CREATE TABLE Companies (
    CompanyID INT AUTO_INCREMENT PRIMARY KEY,
    CompanyName VARCHAR(100) NOT NULL,
    Location VARCHAR(100)
);

-- Create JobListings table
CREATE TABLE JobListings (
    JobID INT AUTO_INCREMENT PRIMARY KEY,
    CompanyID INT NOT NULL,
    JobTitle VARCHAR(100) NOT NULL,
    JobDescription TEXT,
    JobLocation VARCHAR(100),
    Salary DECIMAL(10, 2) NOT NULL,
    JobType ENUM('Full-time', 'Part-time', 'Contract') NOT NULL,
    PostedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CompanyID) REFERENCES Companies(CompanyID) ON DELETE CASCADE
);

-- Create JobApplications table
CREATE TABLE JobApplications (
    ApplicationID INT AUTO_INCREMENT PRIMARY KEY,
    JobID INT NOT NULL,
    ApplicantID INT NOT NULL,
    ApplicationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    CoverLetter TEXT,
    FOREIGN KEY (JobID) REFERENCES JobListings(JobID) ON DELETE CASCADE,
    FOREIGN KEY (ApplicantID) REFERENCES Applicants(ApplicantID) ON DELETE CASCADE
);
INSERT INTO Applicants (FirstName, LastName, Email, Phone, Resume)
VALUES 
('John', 'Doe', 'john.doe@example.com', '1234567890', 'Resume text for John Doe'),
('Jane', 'Smith', 'jane.smith@example.com', '0987654321', 'Resume text for Jane Smith');
INSERT INTO Companies (CompanyName, Location)
VALUES
('Tech Solutions Inc.', 'New York'),
('Innovative Minds LLC', 'San Francisco'),
('SmartWorks Pvt Ltd.', 'Bangalore');
INSERT INTO JobListings (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType)
VALUES
(1, 'Software Engineer', 'Develop and maintain web applications', 'New York', 75000.00, 'Full-time'),
(2, 'Data Analyst', 'Analyze large datasets to generate insights', 'San Francisco', 65000.00, 'Full-time'),
(3, 'Project Manager', 'Oversee project timelines and deliverables', 'Bangalore', 85000.00, 'Contract');

INSERT INTO JobApplications (JobID, ApplicantID, CoverLetter)
VALUES
(1, 1, 'I am excited to apply for the Software Engineer position at your company.'),
(2, 2, 'I am passionate about data analysis and would like to contribute to your team.');
