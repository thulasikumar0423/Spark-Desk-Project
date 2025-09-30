# SparkDesk

SparkDesk is a **Spring Boot web application** that provides **role-based dashboards** for Team Members, Team Leads, Managers, and IT Help Desk.  
It offers secure login/registration, task management, progress tracking, and **interactive charts** with Chart.js.

---

## 🚀 Features
- 🔐 User authentication & role-based access with Spring Security  
- 📊 Dashboards for **Team Member, Team Lead, Manager, IT Help Desk**  
- ✅ Task & progress tracking  
- 🛠 DTO validation & exception handling  
- 🎨 Dark theme UI with Chart.js charts  
- 🗄 MySQL database integration  
- 🌐 REST APIs + Thymeleaf templates  

---


## ⚙️ Tech Stack
- **Backend:** Spring Boot (Web, Data JPA, Security)  
- **Frontend:** Thymeleaf, Chart.js, CSS (Dark Theme)  
- **Database:** MySQL  
- **Build Tool:** Maven  

---

## 🛠 Setup Instructions

1. **Clone the repository**  
   ```bash
   git clone https://github.com/thulasikumar0423/SparkDesk-Project.git
   
2.Configure the database
Update src/main/resources/application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/sparkdesk
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update


3.Build and run the project
mvn spring-boot:run
Access the application
Open: http://localhost:8080

4. 👥 Roles
ROLE_TEAM_MEMBER → View tasks & progress

ROLE_TEAM_LEAD → Manage team tasks

ROLE_MANAGER → View team-wide performance stats

ROLE_IT_HELP_DESK → Handle IT support tickets

📌 Future Enhancements
Add email notifications

Export reports as PDF

Improve charts with filters

Mobile responsive UI

