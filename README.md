# JobListing: A Full-Stack, Role-Based Job Portal

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![React](https://img.shields.io/badge/React-18-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)

JobListing is a modern, full-stack web application designed to connect employers and job seekers. Built with a powerful Spring Boot backend and a dynamic React frontend, it features a secure, role-based system where employers can manage their job postings and candidates can browse and apply for opportunities.

The project leverages Google OAuth2 for seamless authentication and demonstrates key principles of modern web development, including RESTful API design, secure session management, and granular, role-based access control (RBAC).

**Live Demo:** https://joblisting-frontend-bck9.onrender.com/ *(It is highly recommended to deploy your app and add the link here)*

---
## 📸 Screenshots

| Landing Page | Candidate Job Feed | Employer Dashboard |
| :---: | :---: | :---: |
| <img width="1916" height="845" alt="image" src="https://github.com/user-attachments/assets/7a239db5-3b2b-40f2-82e3-9aeac0e32f74" />
 | <img width="1901" height="910" alt="image" src="https://github.com/user-attachments/assets/1cb4b210-46e8-4da0-ad09-4be92d0510ef" />
 | <img width="1916" height="863" alt="image" src="https://github.com/user-attachments/assets/b79bea99-1db8-4c01-a783-57964ac5f5f0" />
 |<img width="1906" height="848" alt="image" src="https://github.com/user-attachments/assets/9154aa9e-03a7-4b08-8a5b-39f2a33b5f3c" />|


---
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

---
## 🛠️ Tech Stack & Architecture

This project is built using a modern, industry-standard technology stack.

* **Backend:**
    * **Java 17** & **Spring Boot 3**: For building a robust and scalable REST API.
    * **Spring Security (OAuth2 Client)**: For handling authentication and role-based authorization.
    * **Spring Data MongoDB**: For seamless data persistence and querying.
    * **Maven**: For project dependency management.

* **Frontend:**
    * **React 18**: For building a fast, interactive, and component-based user interface.
    * **React Router**: For client-side routing and navigation.
    * **Axios**: For making promise-based HTTP requests to the backend.
    * **CSS3**: For custom styling and a professional look and feel.

* **Database:**
    * **MongoDB**: A NoSQL database used for storing user profiles and job postings.

---
## 🚀 Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

* **Java JDK 17** or newer
* **Maven** 3.8 or newer
* **Node.js** 16 or newer
* **MongoDB** running locally on `mongodb://localhost:27017`

### Backend Setup

1.  **Configure Google OAuth2 Credentials**
    * Rename the `application.yml.example` file in `src/main/resources` to `application.yml`.
    * **IMPORTANT:** Add this file to your `.gitignore` to keep your secrets safe!
    * Set your `client-id` and `client-secret` obtained from the Google Cloud Console inside `application.yml`.

2.  **Run the Backend Server**
    ```shell
    # Navigate to the backend project directory
    mvn spring-boot:run
    ```
    The server will start on `https://joblisting-backend-gvl5.onrender.com`.

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
    The application will be available at `https://joblisting-frontend-bck9.onrender.com`.

---
## 📈 Future Enhancements

This project serves as a strong foundation. Future improvements could include:
* **Advanced Filtering:** Allow candidates to filter jobs by location, experience level, and job type.
* **Applicant Tracking:** Enhance the "Apply" feature to allow employers to view a list of candidates who have applied to their jobs.
* **Unit & Integration Testing:** Implement JUnit and Mockito tests on the backend to ensure code quality and reliability.
* **CI/CD Pipeline:** Set up a GitHub Actions workflow to automatically test and deploy the application.
