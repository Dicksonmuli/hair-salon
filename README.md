# Spark Hair-salon
Version 0.0

## Description
Application to manage a hair salon with stylists and clients using Java, PostgreSQL, and Spark with JUnit tests and RESTful routing.

## Details
### User Stories:
* As a salon employee, I need to be able to see a list of all our stylists.
* As an employee, I need to be able to select a stylist, see their details, and see a list of all clients that belong to that stylist.
* As an employee, I need to add new stylists to our system when they are hired.
As an employee, I need to be able to add new clients to a specific stylist.
* As an employee, I need to be able to update a stylist's details.
As an employee, I need to be able to update a client's details.
* As an employee, I need to be able to delete a stylist if they're no longer employed here.
As an employee, I need to be able to delete a client if they no longer visit our salon.

## Setup and Installation
* Clone directory
* Setup database in PSQL:
* * CREATE DATABASE hair_salon;
* * \c hair_salon
* * CREATE TABLE clients (id serial PRIMARY KEY, stylistid int, firstname varchar, lastname varchar, phonenumber varchar, address varchar, city varchar, state varchar, zip int, email varchar, age int, notes varchar);
* * CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);
* * CREATE TABLE appointments (id serial PRIMARY KEY, time varchar, procedureid int, clientid int);
* * CREATE TABLE procedures (id serial PRIMARY KEY, description varchar, price float);
* * CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;

### Author
Dickson
[email](dicksom6@gmail.com) dicksonm6@gmail.com

## Links
* Link to [github  repository](https://github.com/Dicksonmuli/hair-salon)
* Link to [deployed website](https://java-hairsalon.herokuapp.com/)

## Technologies Used
* Java,
* JUnit,
* Spark,
* PostgreSQL,
* Gradle
* Velocity Template Engine

## Legal


Copyright (c) 2017 Copyright Dickson Muli All Rights Reserved.
