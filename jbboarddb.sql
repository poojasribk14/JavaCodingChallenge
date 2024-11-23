-- Switch to your database
Create database jbboarddb;
USE jbboarddb;

-- Create job_listings table if it doesn't exist
CREATE TABLE IF NOT EXISTS job_listings (
    jobID INT AUTO_INCREMENT PRIMARY KEY,
    companyID INT,
    jobTitle VARCHAR(255),
    jobDescription TEXT,
    jobLocation VARCHAR(255),
    salary DOUBLE,
    jobType VARCHAR(50),
    postedDate DATE
);
select * from job_listings;

 
