package dao;
import entity.JobListing;
import exception.DatabaseConnectionException; 
import java.util.List;

public interface JobListingDAO {
    void insertJobListing(JobListing jobListing) throws DatabaseConnectionException;
    List<JobListing> getAllJobListings() throws DatabaseConnectionException;
}
