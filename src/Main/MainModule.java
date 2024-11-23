package Main;

import java.time.LocalDate;
import java.util.Scanner;
import dao.JobListingDAO;
import dao.JobListingDAOImpl;
import entity.JobListing;
import exception.DatabaseConnectionException;

public class MainModule {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JobListingDAO jobListingDAO = new JobListingDAOImpl();

        while (true) {
            System.out.println("\n=== Job Board Menu ===");
            System.out.println("1. Insert Job Listing");
            System.out.println("2. Show All Job Listings");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  
            switch (choice) {
                case 1:
                  
                    System.out.println("\nEnter Job Details:");
                    System.out.print("Company ID: ");
                    int companyID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Job Title: ");
                    String jobTitle = scanner.nextLine();
                    System.out.print("Job Description: ");
                    String jobDescription = scanner.nextLine();
                    System.out.print("Job Location: ");
                    String jobLocation = scanner.nextLine();
                    System.out.print("Salary: ");
                    double salary = scanner.nextDouble();
                    scanner.nextLine(); 
                    System.out.print("Job Type (Full-time/Part-time): ");
                    String jobType = scanner.nextLine();

                    JobListing jobListing = new JobListing();
                    jobListing.setCompanyID(companyID);
                    jobListing.setJobTitle(jobTitle);
                    jobListing.setJobDescription(jobDescription);
                    jobListing.setJobLocation(jobLocation);
                    jobListing.setSalary(salary);
                    jobListing.setJobType(jobType);
                    jobListing.setPostedDate(LocalDate.now()); // Set the posted date to the current date

                    try {
                        jobListingDAO.insertJobListing(jobListing);
                        System.out.println("Job Listing Inserted Successfully!");
                    } catch (DatabaseConnectionException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Show All Job Listings
                    try {
                        var jobListings = jobListingDAO.getAllJobListings();
                        System.out.println("\n=== Job Listings ===");
                        for (JobListing listing : jobListings) {
                            System.out.println(listing);
                        }
                    } catch (DatabaseConnectionException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
