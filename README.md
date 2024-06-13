# Amarelo Air Reservations REST API

![Java](https://img.shields.io/badge/Java-21-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green) ![Spring Security](https://img.shields.io/badge/Spring%20Security-6-green) [![LinkedIn](https://img.shields.io/badge/Connect%20on-LinkedIn-blue)](https://www.linkedin.com/in/gabrieudev) ![GPL License](https://img.shields.io/badge/License-GPL-blue)

Welcome to my **Amarelo Air Reservations REST API** project. 

Please select your preferred language:

- [English](README.md)
- [Português (Brasil)](README.pt-br.md)

This (fictitious) service will use this project for the back-end.

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

This project was created with the aim of providing a robust REST API to be consumed by a front-end. Additionally, the project implements JWT authentication, role-based authorization for users, and encryption of payment information, utilizing the best and most up-to-date market practices to ensure the integrity of sensitive data.

## Features

- Email confirmation for user registration.
- Paginated search functionality.
- Endpoint documentation using Swagger.
- User login with JWT authentication.
- Role-based authorization to control access to different API endpoints.
- Encrypted passwords following industry best practices.
- Integration with PostgreSQL database.

## Technologies

- ![Java](https://img.shields.io/badge/Java-21-orange): Programming language.
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green): Framework used for building applications.
- ![Spring Security](https://img.shields.io/badge/Spring%20Security-6-green): Framework for securing Spring applications.
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue): Relational database.

## Getting Started

Follow these steps to run the project on your machine:

1. Clone the repository: `git clone https://github.com/gabrieudev/amarelo.git`
2. Navigate to the project directory: `cd <path>`
3. Update the general settings in `application.properties`.
4. Build the project: `./mvnw clean install` (for Windows: `mvnw.cmd clean install`)
5. Run the application: `./mvnw spring-boot:run` (for Windows: `mvnw.cmd spring-boot:run`)

## Configuration

- Update the `application.properties` file with all necessary information.

## Usage

1. Upon starting the project, an administrator user is automatically inserted into the database in `AdminDataLoader.java`. Their information can be changed either there or in `application.properties`.
2. Use an administrator user to access protected endpoints.

## Endpoints

Airport:

- `ADMIN Role` `POST /airports`: Saves an airport.
- `BASIC Role` `GET /airports/{id}`: Gets an airport by ID.
- `BASIC Role` `GET /airports`: Gets all airports.
- `ADMIN Role` `PUT /airports/{id}`: Updates an airport.
- `ADMIN Role` `DELETE /airports/{id}`: Deletes an airport.

Flight:

- `ADMIN Role` `POST /flights`: Saves a flight.
- `BASIC Role` `GET /flights/{id}`: Gets a flight by ID.
- `BASIC Role` `GET /flights`: Gets all flights.
- `ADMIN Role` `PUT /flights/{id}`: Updates a flight.
- `ADMIN Role` `DELETE /flights/{id}`: Deletes a flight.

Seat:

- `ADMIN Role` `POST /seats`: Saves a seat.
- `BASIC Role` `GET /seats/{id}`: Gets a seat by ID.
- `BASIC Role` `GET /seats`: Gets all seats.
- `ADMIN Role` `PUT /seats/{id}`: Updates a seat.
- `ADMIN Role` `DELETE /seats/{id}`: Deletes a seat.
- `BASIC Role` `GET /seats/available/{flightId}`: Gets all available seats for a flight.
- `BASIC Role` `GET /seats/available/{flightId}/{min}/{max}`: Gets all available seats for a flight within a price range.

Reservation:

- `BASIC Role` `POST /reservations`: Saves a reservation.
- `BASIC Role` `GET /reservations/{id}`: Gets a reservation by ID.
- `ADMIN Role` `GET /reservations`: Gets all reservations.
- `BASIC Role` `PUT /reservations/{id}`: Updates a reservation.
- `BASIC Role` `DELETE /reservations/{id}`: Deletes a reservation.
- `BASIC Role` `GET /reservations/by-user/{userId}`: Gets all reservations for a user.

Payment:

- `BASIC Role` `GET /payments/{id}`: Gets a payment by ID.
- `ADMIN Role` `GET /payments`: Gets all payments.
- `BASIC Role` `PUT /payments/{id}`: Updates a payment.
- `ADMIN Role` `DELETE /payments/{id}`: Deletes a payment.

User:

- `POST /users/register`: Registers a user.
- `POST /login`: Logs in and receives a JWT.
- `ADMIN Role` `GET /users`: Gets all users.
- `BASIC Role` `PUT /users/update-password`: Updates a user's password.
- `POST /users/check/{userId}/{verificationId}`: Verifies if the verification code sent to the email is correct.

Access the complete documentation at the `/swagger-ui.html` endpoint.

## Contributions

Contributions are very welcome! If you want to contribute, fork the repository and create a pull request.

## Contact

If you have any suggestions or questions, contact me on [LinkedIn](https://www.linkedin.com/in/gabrieudev)

---

**License:** This project is licensed under the terms of the [GNU General Public License (GPL)](LICENSE).