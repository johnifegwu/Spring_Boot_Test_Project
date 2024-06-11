# ORYX CODING CHALLENGE

## Welcome!

We are excited to have you participate in this coding challenge. This task is designed to evaluate your skills in handling real-world data integration scenarios as well as your understanding of key software design principles. The quality of your code is important, so please ensure your work is clean and follows best practices.

## Scope of the Challenge

This challenge consists of one main programming task and four smaller tasks aimed at testing your understanding of the SOLID principles and code quality.

## Background

In our certificate management system, due to a coding error, we have ended up with various certificates stored in different serial number formats. These inconsistencies need to be resolved to ensure data integrity. Additionally, the application might have incorrectly marked some certificates with the wrong status (e.g., marking a certificate as revoked when it is not). The CSV files provide accurate data that we need to import to correct these discrepancies. You will also prepare the groundwork for future enhancements to support other file formats like XLS and XML, focusing on how you would structure the code to accommodate these formats without needing to implement the actual import functionality.

## Main Task

1. **Implement CSV File Loading Functionality**
    - Create an endpoint for uploading CSV files through a web form.
    - Parse the uploaded CSV files and validate their format.
    - Map the parsed data to the corresponding database entities.

2. **Insert or Update Records Based on Serial Numbers and Statuses**
    - Handle different serial number formats: The database contains serial numbers in varying formats, making it difficult to identify duplicates and update existing records accurately. The CSV files provide serial numbers in a consistent format with both hexadecimal and decimal representations. Implement a system that can handle these inconsistencies and ensure the integrity of the data.
        #### Examples
        ##### Database Serial Numbers (Current State)
        | ID  | Serial Number           |
        |-----|-------------------------|
        | 1   | 0abbacf1234             |
        | 2   | bccbdf1234              |
        | 3   | 819855292164            |
        ##### CSV Serial Numbers (Consistent Format)
        | Hex Serial Number | Decimal Serial Number  |
        |-------------------|------------------------|
        | abbacf1234        | 737573540404           |
        | bccbdf1234        | 810874245684           |
        | BEE32EF704        | 819855292164           |
    - Map status names: Correct any discrepancies in status data based on the CSV, which is considered the source of truth.
    - Insert new records or update existing ones based on the serial numbers and statuses.

3. **Prepare Foundation for Future Import Formats**
    - Set up the foundation for importing certificates from other formats such as XLS or XML.
    - Include necessary placeholders and comments indicating where and how the import logic should be implemented.
    - Focus on structuring the code to accommodate these formats without implementing the actual import functionality.

4. **Verification and Testing**
    - Prepare a set of tests to ensure the correctness of the implemented functionalities.
    - Write unit tests for individual components and functions.
    - Conduct integration tests to verify the interaction between different components.
    - Handle edge cases, such as malformed CSV files or unknown serial number formats.

### Example Test Data

To assist with testing, an example dataset is provided in the `test/resources` directory. There is also a foundational test setup in place to help you get started with writing and running your tests. The basics of CSV parsing are already implemented.

## 4 Small Code Quality Tasks

In addition to the main task, there are 4 smaller tasks to test your knowledge and uderstanding of SOLID principles.

### codinpad/codequality/order/OrderProcessor.java
- The Java class `OrderProcessor` below violates one of the SOLID principles. Identify which one and refactor the code accordingly.

### codinpad/codequality/usermanagement
- Review the classes in the `usermanagement` package. Refactor them to adhere to best practices.

### codinpad/codequality/openclosed/InvoicePersistence.java
- Review the `InvoicePersistence` class:
  1. Identify any issues related to the Open/Closed Principle.
  2. Propose a refactor of the class design to adhere to the Open/Closed Principle, allowing for future extensions like saving to different types of storage without modifying existing code.
  3. Describe or implement your solution.

### codinpad/codequality/reports
- Review the `SalesReport` and `RevenueReport` classes. 

    What is wrong in these 2 Java classes?
    Your task is to answer the question and explain how would you refactor the code to improve the code while maintaining its functionality.

## Submission Instructions

Please perform all your work on a separate branch and create a pull request when you have completed the task. Our recruitment team will be notified that you finished your task once you create the pull request.

Good luck with your coding challenge! We look forward to reviewing your submission.
