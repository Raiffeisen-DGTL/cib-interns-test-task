# Raiffeisen CIB Intern Test Task

### Complete

SpringBoot Application

### In progress

Flyway Migrations

DB in Docker

Deploy on Heroku

## Specification

| URL                | Request | Data                        | HTTP Codes             |
| ------------------ | ------- | --------------------------- | ---------------------- |
| /api/socks/income  | POST    | JSON                        | 200 - OK               |
|                    |         | color : String              | 400 - Invalid Data     |
|                    |         | cottonParts : Integer       | 500 - Internal Error   |
|                    |         | (0 <= cottonParts <= 100)   |                        |
|                    |         | quantity : Integer          |                        |
|                    |         | (0 < quantity)              |                        |
|                    |         |                             |                        |
| /api/socks/outcome | POST    | JSON                        | 200 - OK               |
|                    |         | color : String              | 400 - Invalid Data     |
|                    |         | cottonParts : Integer       | 400 - Socks not found  |
|                    |         | (0 <= cottonParts <= 100)   | 400 - Not enough socks |
|                    |         | quantity : Integer          | 500 - Internal Error   |
|                    |         | (0 < quantity)              |                        |
|                    |         |                             |                        |
| /api/socks/        | GET     | URL                         | 200 - OK               |
|                    |         | color : String              | 400 - Invalid Data     |
|                    |         | Operation : String          | 500 - Internal Error   |
|                    |         | (moreThan, lessThan, equal) |                        |
|                    |         | cottonParts : Integer       |                        |
|                    |         | (0 <= cottonParts <= 100)   |                        |

## Database Scheme

Database: PostgreSQL 13

Number of tables: 1

Table 1: socks_warehouse

| Column      | Type        | Atributes                |
| ----------- | ----------- | ------------------------ |
| id          | bigint      | PK, Unique, Not nullable |
| color       | varchar(30) | Not nullable             |
| cotton_part | integer     | Not nullable             |
| quantity    | integer     | Not nullable             |
