# REST API for Airline Reservations

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green) ![Spring Security](https://img.shields.io/badge/Spring%20Security-6-green) [![LinkedIn](https://img.shields.io/badge/Connect%20on-LinkedIn-blue)](https://www.linkedin.com/in/gabrieudev) ![GPL License](https://img.shields.io/badge/License-GPL-blue)

Welcome to my **REST API for Airline Reservations** project. 

Please select your preferred language:

- [English](README.md)
- [Português (Brasil)](README.pt-br.md)

The (fictional) service, named Amarelo, will use this project for its backend.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Contributions](#contributions)
- [Contact](#contact)

## Introduction

This project aims to provide a REST API capable of registering users and handling airline reservations. Additionally, the project implements JWT authentication, role-based authorization for users, and encryption of payment information, following the best and most current industry practices to ensure data integrity.

## Features

- Email sending for user registration confirmation.
- Pagination-enabled searches.
- Documentation with Swagger-enabled endpoints.
- User login with JWT authentication.
- Role-based authorization for controlling access to different API endpoints.
- Passwords encrypted using industry best practices.
- Integration with MySQL database.

## Technologies

- ![Java](https://img.shields.io/badge/Java-17-orange): Programming language.
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green): Framework for building applications.
- ![Spring Security](https://img.shields.io/badge/Spring%20Security-6-green): Security framework for Spring applications.
- ![MySQL](https://img.shields.io/badge/MySQL-Database-blue): Relational database.

## Configuration

- Before starting the application, update the `application.properties` file with all necessary information.

## Getting Started

Follow these steps to run the project on your machine:

1. Clone the repository: `git clone https://github.com/gabrieudev/amarelo.git`
2. Navigate to the project directory: `cd <path>`
3. Navigate to the docker directory: `cd docker`
4. Run the docker-compose file to create and start the MySQL container: `docker compose up`
5. Return to the initial directory: `cd <path>`
6. Build the project: `./mvnw clean install` (for Windows: `mvnw.cmd clean install`)
7. Run the application: `./mvnw spring-boot:run` (for Windows: `mvnw.cmd spring-boot:run`)

## Usage

1. Upon starting the project, an administrator user with predefined roles is automatically inserted into the database using `AdminDataLoader.java`. You can modify their information either there or in `application.properties`.
2. Use an administrator role user to access protected endpoints.

## Endpoints

Airport:

- `ADMIN Role` `POST /airports`: Save an airport.
- `BASIC Role` `GET /airports/{id}`: Get an airport by id.
- `BASIC Role` `GET /airports`: Get all airports.
- `ADMIN Role` `PUT /airports/{id}`: Update an airport.
- `ADMIN Role` `DELETE /airports/{id}`: Delete an airport.

Flight:

- `ADMIN Role` `POST /flights`: Save a flight.
- `BASIC Role` `GET /flights/{id}`: Get a flight by id.
- `BASIC Role` `GET /flights`: Get all flights.
- `ADMIN Role` `PUT /flights/{id}`: Update a flight.
- `ADMIN Role` `DELETE /flights/{id}`: Delete a flight.

Seat:

- `ADMIN Role` `POST /seats`: Save a seat.
- `BASIC Role` `GET /seats/{id}`: Get a seat by id.
- `BASIC Role` `GET /seats`: Get all seats.
- `ADMIN Role` `PUT /seats/{id}`: Update a seat.
- `ADMIN Role` `DELETE /seats/{id}`: Delete a seat.
- `BASIC Role` `GET /seats/available/{flightId}`: Get all available seats for a flight.
- `BASIC Role` `GET /seats/available/{flightId}/{min}/{max}`: Get available seats for a flight within a price range.

Reservation:

- `BASIC Role` `POST /reservations`: Save a reservation.
- `BASIC Role` `GET /reservations/{id}`: Get a reservation by id.
- `ADMIN Role` `GET /reservations`: Get all reservations.
- `BASIC Role` `PUT /reservations/{id}`: Update a reservation.
- `BASIC Role` `DELETE /reservations/{id}`: Delete a reservation.
- `BASIC Role` `GET /reservations/by-user/{userId}`: Get all reservations of a user.

Payment:

- `BASIC Role` `GET /payments/{id}`: Get a payment by id.
- `ADMIN Role` `GET /payments`: Get all payments.
- `BASIC Role` `PUT /payments/{id}`: Update a payment.
- `ADMIN Role` `DELETE /payments/{id}`: Delete a payment.

User:

- `POST /auth/register`: Registers a user and sends a confirmation link to their email.
- `GET /users/confirm`: Email verification.
- `POST /auth/login`: Logs in and receives a JWT.
- `ADMIN Role` `GET /users`: Retrieves all users.
- `ADMIN Role` `DELETE /users/{userId}`: Deletes a user.
- `BASIC Role` `GET /users/{userId}`: Retrieves a user by ID.
- `BASIC Role` `POST /users/change-password`: Changes a user's password.

Access the full documentation at the `/swagger-ui.html` endpoint.

## Contributions

Contributions are welcome! If you would like to contribute, fork the repository and create a pull request.

## Contact

If you have any suggestions or questions, feel free to contact me on [LinkedIn](https://www.linkedin.com/in/gabrieudev).

---

**License:** This project is licensed under the terms of the [GNU General Public License (GPL)](LICENSE).