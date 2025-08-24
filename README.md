-----

# JobListing: A Full-Stack, Role-Based Job Portal

   

JobListing is a modern, full-stack web application designed to connect employers and job seekers. Built with a powerful Spring Boot backend and a dynamic React frontend, it features a secure, role-based system where employers can manage their job postings and candidates can browse and apply for opportunities.

The project leverages Google OAuth2 for seamless authentication and demonstrates key principles of modern web development, including RESTful API design, secure session management, and granular, role-based access control (RBAC).

**Live Demo:** **[https://joblisting-frontend-bck9.onrender.com/](https://joblisting-frontend-bck9.onrender.com/)**

-----

## 📸 Screenshots

| Landing Page | Candidate Job Feed |
| :---: | :---: |
|<img width="1919" height="974" alt="Screenshot 2025-08-24 193855" src="https://github.com/user-attachments/assets/47063415-94ef-4558-b626-05da0c16ec33" />
|<img width="1901" height="910" alt="Screenshot 2025-08-24 200435" src="https://github.com/user-attachments/assets/eccca1b1-5a5a-4907-aa53-81292d1e845e" />
 |
| **Employer Dashboard** | **Job Posting Form** |
|<img width="1916" height="863" alt="Screenshot 2025-08-24 200516" src="https://github.com/user-attachments/assets/10f33bed-0f16-4e9a-b9ad-9db4744277e8" />
 | <img width="1906" height="848" alt="Screenshot 2025-08-24 200533" src="https://github.com/user-attachments/assets/5d4671dc-e535-49f4-abe2-05982f8e5bbd" />|

-----

## ✨ Features

The application supports two distinct user roles with tailored experiences:

#### **👨‍💼 Employer Features**

  * **Secure Authentication:** Log in securely using a Google account.
  * **Post Ownership:** Employers can only view, edit, and delete job postings that they have created.
  * **CRUD Operations:** Full Create, Read, Update, and Delete functionality for job postings.
  * **Personalized Dashboard:** View a dedicated dashboard listing all personal job postings, sorted by the most recent.

#### **👩‍💻 Candidate Features**

  * **Secure Authentication:** Log in securely using a Google account.
  * **Comprehensive Job Feed:** View and browse all available job postings from all employers, sorted by the most recent.
  * **Dynamic Search:** Search for jobs in real-time by title, company, or skills.
  * **Simple Application Process:** Apply for jobs with a single click.

-----

## 🛠️ Tech Stack & Architecture

This project is built using a modern, industry-standard technology stack.

  * **Backend:**

      * **Java 21** & **Spring Boot 3**: For building a robust and scalable REST API.
      * **Spring Security (OAuth2 Client)**: For handling authentication and role-based authorization.
      * **Spring Data MongoDB**: For seamless data persistence and querying.
      * **Maven**: For project dependency management.

  * **Frontend:**

      * **React 18**: For building a fast, interactive, and component-based user interface.
      * **React Router**: For client-side routing and navigation.
      * **Axios**: For making promise-based HTTP requests to the backend.
      * **CSS3**: For custom styling and a professional look and feel.

  * **Database:**

      * **MongoDB Atlas**: A cloud-native NoSQL database used for storing user profiles and job postings.

  * **Deployment:**

      * **Render**: For hosting the backend (Web Service) and frontend (Static Site).
      * **Docker**: For containerizing the Spring Boot backend, ensuring a consistent and reproducible runtime environment.

-----

## 🚀 Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

  * Java JDK 21 or newer
  * Maven 3.8 or newer
  * Node.js 16 or newer
  * MongoDB running locally on `mongodb://localhost:27017`

### Backend Setup

1.  **Configure Google OAuth2 Credentials**

      * Create a file named `application.yml` in `src/main/resources`.
      * **IMPORTANT:** Ensure this file is listed in your `.gitignore` to keep your secrets safe\!
      * Set your `client-id` and `client-secret` as environment variables in your IDE (e.g., IntelliJ's Run/Debug Configurations). The `application.yml` file should reference them like this:
        ```yaml
        spring:
          security:
            oauth2:
              client:
                registration:
                  google:
                    client-id: ${GOOGLE_CLIENT_ID}
                    client-secret: ${GOOGLE_CLIENT_SECRET}
        ```

2.  **Run the Backend Server**

    ```shell
    # Navigate to the backend project directory
    mvn spring-boot:run
    ```

    The server will start on `http://localhost:8080`.

### Frontend Setup

1.  **Install NPM packages**
    ```shell
    # Navigate to the frontend project directory
    npm install
    ```
2.  **Run the Frontend Server**
    ```shell
    npm start
    ```
    The application will be available at `http://localhost:3000`.

-----

## 📈 Future Enhancements

This project serves as a strong foundation. Future improvements could include:

  * **Advanced Filtering:** Allow candidates to filter jobs by location, experience level, and job type.
  * **Applicant Tracking:** Enhance the "Apply" feature to allow employers to view a list of candidates who have applied to their jobs.
  * **Unit & Integration Testing:** Implement JUnit and Mockito tests on the backend to ensure code quality and reliability.
  * **CI/CD Pipeline:** Set up a GitHub Actions workflow to automatically test and deploy the application.
