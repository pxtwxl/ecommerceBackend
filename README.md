# SmartCart Backend

This is the backend for the SmartCart e-commerce platform, built with Spring Boot. It provides a secure RESTful API for user authentication, product management, order processing, and payment integration. The backend is designed to work seamlessly with the SmartCart frontend and supports deployment on cloud platforms like Render.

**Key Features**
- User Authentication & Authorization: Secure registration, login, and JWT-based authentication.
- Product Management: CRUD operations for products, category filtering, and search functionality.
- Order Management: Place orders, view order history, and manage user-specific orders.
- Stripe Payment Integration: Secure payment processing using Stripe Checkout.
- Image Handling: Upload and serve product images.
- Role-Based Access: Separate endpoints and permissions for buyers and sellers.
- PostgreSQL Database: Robust data storage with JPA/Hibernate.
- Environment Variable Support: All secrets and configuration are managed via environment variables for security.

**Tech Stack**
- Java 21
- Spring Boot
- Spring Security (JWT)
- Stripe Java SDK
- PostgreSQL
- Maven

**Getting Started**
1. Clone the repository.
2. Set up environment variables for database, Stripe, and frontend URL.
3. Build and run the application:
   ```bash
   ./mvnw clean package
   java -jar target/<your-jar-file>.jar
   ```
   Or use Docker for containerized deployment.

**Environment Variables**
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `FRONTEND_APP_URL`
- `STRIPE_SECRET_KEY`

**API Documentation**
- RESTful endpoints for authentication, products, orders, and payments.
- See the code and comments for detailed endpoint information.

**Deployment**
- Ready for deployment on Render, Heroku, or any cloud platform supporting Java and Docker.

---
