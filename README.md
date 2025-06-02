# Tapakila - Backend Application

Tapakila is an event ticketing platform that enables users to browse, purchase, and manage tickets for various events. This repository contains the backend side of the application, built using Spring Boot.

> **Author**: TapakilaTeam - 3BEW

---
## Ui/Ux repository :
You can find the frontend side of this Tapakila application here : [https://github.com/Tsantanny/tapakila-frontend-website.git](https://github.com/Tsantanny/tapakila-frontend-website.git)

---

## OAS (API SPECIFICATION) :

API documentation of Tapakila App can be found here : [Api_Documentation](docs/api.yml)

---

## 🚀 Features

### 🎟 Ticket Purchase & Management
- Users can buy different types of tickets:
  - **Standard**
  - **Early Bird**
  - **Gold**
- Payment methods supported:
  - **Cash**
  - **Visa**
  - **Mobile Money**
- After purchasing, users receive a **QR code** representing their ticket.

### 👥 User Authentication & Dashboard
- Users must **register using their email**.
- Email confirmation with a **verification code** is required to activate the account.
- Once authenticated, users have access to a personal **dashboard** to view and manage their tickets.

### 🛠 Admin Panel
- Admins have a dedicated dashboard to:
  - View **statistics** on users and tickets.
  - Track **sold** and **remaining** tickets.
  - Monitor **monthly and yearly turnovers**.
  - Identify the most sold ticket type.
  - Manage users and event-related data.

---

## 👤 User Roles

- **User**: Can register, log in, buy tickets, and view their own ticket dashboard.
- **Admin**: Has access to the full analytics and management dashboard.

---

## 🧰 Tech Stack

- **Java** with **Spring Boot**
- **Spring Security**, **JDBC** (for technical reasons)
- **PostgreSQL** (or other relational database)
- **QR Code generation library**
- **Java mail sender**
- **RESTful APIs**

---

## 📫 Contact

For more information, contact the Tapakila Team – 3BEW.

---


