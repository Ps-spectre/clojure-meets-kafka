(ns clojure-meets-kafka.filters
  (:require 
    [clojure-meets-kafka.kafka :as kafka]))

(defn create-id [state]
  ;; get and increment id from global state
  (let [id (inc (get-in @state [:lastId]))]
    (swap! state assoc-in [:lastId] id)
    id))

(defn create-filter [id offset topic q]
  ;; create filter object
  (hash-map :id id :topic topic :q q :offset offset))

(defn add-filter [topic-name q state]
  ;; create and add filter to global state
  (let [id (create-id state)
        offset (kafka/get-offset-topic topic-name)
        filter (create-filter id offset topic-name q)]
    (swap! state assoc-in [ :filters id ] filter)))

(defn get-filter [id state]
  ;; get filter by id from global state
  (get-in @state [:filters id]))

(defn delete-filter [id state] 
  ;; delete filter from global state
  (let [f (get-filter id state)]
    (when (not (nil? f)) 
      (swap! state update-in [ :filters ] dissoc id))
    f))


