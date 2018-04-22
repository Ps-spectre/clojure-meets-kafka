(ns clojure-meets-kafka.filters
  (:require 
    [clojure-meets-kafka.kafka :as kafka]))

(defn add-filter [topic-name q]
  (println "OFFSET: " (kafka/get-offset-topic topic-name))
  (println "add-filter" topic-name q))

