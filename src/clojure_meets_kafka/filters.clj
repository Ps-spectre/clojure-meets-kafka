(ns clojure-meets-kafka.filters
  (:require 
    [clojure-meets-kafka.kafka :as kafka]))

(defn add-filter [topic-name q]
  (println "OFFSET: " (kafka/get-offset-topic topic-name))
  (println "msg: " (kafka/get-messages topic-name 0))
  (println "add-filter" topic-name q))


