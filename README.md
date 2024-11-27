# HealthCare+ (Full Stack)  

## Overview  
**HealthCare+** is an online appointment booking system designed to streamline the process of scheduling and managing appointments in the healthcare sector. The system offers distinct user and admin panels with tailored functionalities, ensuring efficiency and ease of use.  

---

## Features  

### **User Panel**  
- **Login and Sign-up:**  
  - Secure authentication using Angular services and Spring Boot.  
  - Credentials are verified by the backend before being stored.  
- **Forgot Password:**  
  - Retrieve password through email-based recovery.  
- **Appointment Management:**  
  - Book appointments by selecting doctors and available slots.  
  - Cancel appointments.  
  - View appointment history with real-time status tracking (e.g., completed, canceled).  

### **Admin Panel**  
- **Doctor Management:**  
  - Add, remove, or update doctor profiles and availability.  
- **Appointment Oversight:**  
  - Manage completed appointments.  
  - Remove appointments canceled due to unforeseen reasons.  
- **System Maintenance:**  
  - Update doctor schedules and oversee system operations.  

### **Email Notifications**  
Automated email notifications for:  
- Appointment booking confirmations.  
- Appointment cancellations.  
- Status updates (completed or canceled).  
- Password recovery assistance.  

---

## Technology Stack  

### **Frontend**  
- Angular (HTML, CSS, TypeScript)  
- Angular Services for temporary credential storage and API interaction  

### **Backend**  
- Spring Boot (RESTful APIs)  
- Spring Security for authentication  
- Email services integrated using JavaMail  

### **Database**  
- MySQL (or any relational database) for data storage  

### **Other Tools and Libraries**  
- Bootstrap/Angular Material for responsive UI design  
- Postman for API testing  

---
