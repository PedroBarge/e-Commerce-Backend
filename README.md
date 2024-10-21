---

### About This Project

This project was developed as a learning exercise to understand how to save image URLs in databases. It uses Java and Spring Boot, utilizing the `MultipartFile` class to handle file uploads.

### How It Works

In this project, images are sent to the backend, where they are processed and moved to an "uploads" folder. Each file is assigned a unique UUID, which is combined with the original file name to create a new, unique identifier. This new name is then saved in the database.

--- 
