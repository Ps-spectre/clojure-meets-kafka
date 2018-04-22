# Clojure-meets-kafka

Simple web application that interacts with Kafka.

## Goal

Simple message filtering system.
Create and collect messages by filter.

* Add filter
* Delete filter
* Get all filters
* Get messages by filter

## Web API

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
