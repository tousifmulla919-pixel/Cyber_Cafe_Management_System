# Cyber_Cafe_Management_System
The Cybercafe Management System automates the billing and user management process of a cybercafe. It eliminates manual paperwork by digitally tracking customer login/logout times and generating bills automatically.

Each customer is assigned a **unique username**. When they visit, the employee logs their session — and the bill is calculated based on time used. Customers with a **membership** get discounted rates for a fixed number of hours. 

### Scope of the System
➢ This designated system aims to minimize the manual work done. The entire process of paper form filing, submission and report generation is done online. 
➢ Data processing and representation is very fast in proposed system as compared to 
existing system. 
➢ Ensure data integrity and security. 
➢ Less manpower. 
➢ User-friendly system that would be easy to operate. 
➢ No more paper work required. 
➢ No more repetition of work or data.

### Advantages of System 
➢ Administrators maintains/modify/create and also able to delete the user. 
➢ Faster technique to generate bill. 
➢ Paper works not required. 
➢ All monitoring is done by admin. 
➢ Only admins have all the rights.

---

## ❗ Problem with the Old System

- All registrations were done manually in a register book
- Data was insecure and prone to misuse
- Data inconsistency due to manual errors
- Time-consuming paperwork
- Processing delays

---

## ✅ Features

- Admin login with secure authentication
- **Employee Management** — Create, Update, Delete, View employees
- **Customer Management** — Create, Update, Delete, View customers
- **Membership Support** — Discounted billing for members with remaining hours tracking
- **Bill Management** — Generate new bills and view all past bills
- Automatic total time and bill amount calculation
- No paperwork required — fully digital

---

## 🖥️ Tech Stack

| Component | Technology |
|-----------|------------|
| Frontend | Java (Swing) |
| Backend | Java |
| Database | PostgreSQL |
| OS | Linux |

---

## 🗄️ Database Tables

- **Admin** — stores admin/employee credentials and details
- **Customer** — stores customer info with unique username and optional membership
- **Cbill** — stores billing records (login time, logout time, total time, bill amount)
- **Membership** — stores membership plans with hours and rates
- **Timerec** — stores login/logout time records

---

## 📸 Screenshots

### Login Window
![Login Window](https://github.com/tousifmulla919-pixel/Cyber_Cafe_Management_System/blob/main/z7obp6td.png?raw=true)

### Admin Dashboard
![Admin Window](https://github.com/tousifmulla919-pixel/Cyber_Cafe_Management_System/blob/main/02_admin_dashboard.png?raw=true)

### View Employees
![View Employees](https://github.com/tousifmulla919-pixel/Cyber_Cafe_Management_System/blob/main/08_view_employees.png?raw=true)

### Create Customer
![Create Customer]()

### Bill Management – New Bill
![New Bill]()

### View Bills
![View Bills]()

---

## 🚀 How to Run

1. Make sure **Java** and **PostgreSQL** are installed on your system
2. Set up the PostgreSQL database and create the required tables
3. Update the DB connection settings in the source code
4. Compile and run `LoginWindow.java`
5. Login with admin credentials to access the dashboard

---
