# Clojure-meets-kafka

Simple web application that interacts with Kafka.
For simplicity topic in Kafka uses 1 partition.

## Goal

Simple message filtering system.
Create and collect messages by filter.

* Add filter
* Delete filter
* Get all filters
* Get messages by filter

## Web API

:anti-forgery **disabled**. Set to true to add CSRF protection via the ring-anti-forgery library.


* **POST** /filter {"topic": "books", "q": "sicp"} Content-Type	application/json
(add new filter to system)
* **GET** /filter
(get all filters)
* **POST** /msg {"id": 1} Content-Type application/json
(get messages by filter) (TODO: improve)
* **DELETE** /filter {"id": 1} Content-Type	application/json
(delete filter by id)

There is no Web client to test this API.

You should use some debug tools, e.g. [Yet Another REST Client](https://github.com/paulhitz/yet-another-rest-client) chrome extension.

(Don't forget add header: Content-Type application/json when do post / delete.)

### Logic scheme

![scheme image](https://i.imgur.com/7xcj6HA.png?1)

## Prerequisites

You will need [Leiningen](https://leiningen.org/) 2.0.0 or above installed.
Apache [Kafka](https://kafka.apache.org/) 1.1.0
Clojure 1.8+

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a Kafka with zookeeper, run:

    bin/zookeeper-server-start.sh config/zookeeper.properties
    bin/kafka-server-start.sh config/server.properties

To start a web server for the application, run:

    lein ring server
    lein ring server-headless

## License

Eclipse Public License - v 2.0
Copyright © 2018 Andrew Kishchenko
