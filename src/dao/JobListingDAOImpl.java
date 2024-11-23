/*package dao;

import entity.JobListing;
import exception.DatabaseConnectionException;
import java.util.List;
import java.util.ArrayList;

public class JobListingDAOImpl implements JobListingDAO {
    private static List<JobListing> jobListings = new ArrayList<>();

    @Override
    public void insertJobListing(JobListing jobListing) throws DatabaseConnectionException {
        // Simulate inserting to a database
        jobListings.add(jobListing);
        System.out.println("Job Listing Inserted into Database.");
    }

    @Override
    public List<JobListing> getAllJobListings() throws DatabaseConnectionException {
        return jobListings;  // Return the list of job listings (this can be modified to fetch from a database)
    }
}

  */
package dao;

import entity.JobListing;
import exception.DatabaseConnectionException;
import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobListingDAOImpl implements JobListingDAO {

    @Override
    public void insertJobListing(JobListing jobListing) throws DatabaseConnectionException {
        String sql = "INSERT INTO job_listings (companyID, jobTitle, jobDescription, jobLocation, salary, jobType, postedDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, jobListing.getCompanyID());
            preparedStatement.setString(2, jobListing.getJobTitle());
            preparedStatement.setString(3, jobListing.getJobDescription());
            preparedStatement.setString(4, jobListing.getJobLocation());
            preparedStatement.setDouble(5, jobListing.getSalary());
            preparedStatement.setString(6, jobListing.getJobType());
            preparedStatement.setDate(7, java.sql.Date.valueOf(jobListing.getPostedDate()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error inserting job listing: " + e.getMessage(), e);
        }
    }

    @Override
    public List<JobListing> getAllJobListings() throws DatabaseConnectionException {
        List<JobListing> jobListings = new ArrayList<>();
        String sql = "SELECT * FROM job_listings";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                JobListing jobListing = new JobListing();
                jobListing.setJobID(resultSet.getInt("jobID"));
                jobListing.setCompanyID(resultSet.getInt("companyID"));
                jobListing.setJobTitle(resultSet.getString("jobTitle"));
                jobListing.setJobDescription(resultSet.getString("jobDescription"));
                jobListing.setJobLocation(resultSet.getString("jobLocation"));
                jobListing.setSalary(resultSet.getDouble("salary"));
                jobListing.setJobType(resultSet.getString("jobType"));
                jobListing.setPostedDate(resultSet.getDate("postedDate").toLocalDate());

                jobListings.add(jobListing);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error retrieving job listings: " + e.getMessage(), e);
        }
        return jobListings;
    }
}

