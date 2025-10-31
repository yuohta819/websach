📚 Book Web App

A modern book management and reading platform built with Java (Spring Boot) and Vue.js, offering secure blockchain login, premium content, and powerful admin control.

🚀 Features
👥 User Roles & Authentication

Login with MetaMask for secure blockchain-based authentication.

Role-based access control: Admin / Premium / Standard users.

Single-device login via token-based authentication (only one active session per account).

📖 User Features

Browse and read books online with a responsive, user-friendly UI.

Purchase Pro/Premium plans to unlock exclusive content.

View reading history and personalized book recommendations.

🛠️ Admin Dashboard

Full CRUD operations (Create, Read, Update, Delete).

Manage books, users, and subscription plans efficiently.

Monitor system statistics and manage access control.

🧭 Routes Overview
Role	Route	Description
Public	/login, /register	User authentication
User	/books, /profile, /history	Browse and read books
Premium	/premium, /exclusive	Access exclusive content
Admin	/admin/dashboard, /admin/books, /admin/users	Manage platform data
🧩 Technologies Used

Backend: Java, Spring Boot

Frontend: Vue.js, Tailwind CSS

Database: SQL Server

API: RESTful APIs

Version Control: Git / GitHub

💡 How to Use

Clone the repository:

git clone https://github.com/yuohta819/websach


Start the backend (Spring Boot) and frontend (Vue.js) servers.

Access the app via browser and log in with MetaMask.

Explore books, upgrade plans, or manage content through the Admin Dashboard.
