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

This will setup the Postgres database and insert the sample data from the CSV. Afterwards, simply run

```bash
make app
```