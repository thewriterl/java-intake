# Demo Ted Talk

This is a Java demo By Luiz Fernando França

## Requirements
***
* [GNU Make](https://www.gnu.org/software/make/): Version 4.3
* [Maven](https://maven.apache.org/): Version 3.8.3
* [Docker](https://www.docker.com/): Version 20.10.17 and docker-compose
* Java: 11+

## Installation

Simply, 

```bash
make install
```

## How to Run

* After running the installation step, simply use 

```bash
make up
```

This will set up the Postgres database and insert the sample data from the CSV. 

Afterwards, simply run this in a separate terminal instance. 

```bash
make application
```

## How to Run Tests

Everytime you run the application or the tests, you have to previously set up the database by running

```bash
make up
```

Then you can run 

```bash
make verify
```

To run the tests


Alternatively, you can run the application on you IDE of choice, just don't forget to run 
```bash
make up
```
to set up the database, otherwise it will fail. 

* There's a postman collection included at the root of the project containing all requests, it's the Io.postman_collection.json file 

