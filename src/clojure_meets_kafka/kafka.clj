(ns clojure-meets-kafka.kafka
  (:require
    [clojure-meets-kafka.config :as config]
    [franzy.clients.consumer.client :as consumer]
    [franzy.clients.consumer.protocols :refer :all]
    [franzy.clients.consumer.defaults :as cd]
    [franzy.serialization.serializers :as serializers]
    [franzy.serialization.deserializers :as deserializers]))

(defn get-offset-topic [topic-name]
  (let [cc {:bootstrap.servers config/kafka-brokers
            :auto.offset.reset :none}
        key-deserializer (deserializers/string-deserializer)
        value-deserializer (deserializers/string-deserializer)
        options (cd/make-default-consumer-options)
        topic topic-name
        topic-partitions [{:topic topic :partition 0}]]
    (with-open [c (consumer/make-consumer cc key-deserializer value-deserializer options)]
      (assign-partitions! c topic-partitions)
      (seek-to-end-offset! c topic-partitions)
      (next-offset c (get topic-partitions 0)))))

(defn get-messages [topic-name offset]
  (let [cc {:bootstrap.servers config/kafka-brokers
            :auto.offset.reset :none}
        key-deserializer (deserializers/string-deserializer)
        value-deserializer (deserializers/string-deserializer)
        options (cd/make-default-consumer-options)
        topic topic-name
        topic-partitions [{:topic topic :partition 0}]]
    (with-open [c (consumer/make-consumer cc key-deserializer value-deserializer options)]
      (assign-partitions! c topic-partitions)
      (seek-to-offset! c (get topic-partitions 0) offset)
      (let [ cr (poll! c)]
        (->> (into [] cr) (map #(get % :value)) )))))