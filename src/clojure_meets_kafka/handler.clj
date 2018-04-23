(ns clojure-meets-kafka.handler
  (:require [clojure-meets-kafka.filters :as filters]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body]]
            [ring.util.response :refer [response]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defonce state (atom {:filters {}
                      :lastId 0}))

(defn delete-filter [r] 
  (if (filters/delete-filter (get-in r [:body :id] -1) state)
    "success: filter was deleted!"
    "failure: can't find filter"))

(defn add-filter [r]
  (let [t (get-in r [:body :topic])
        q (get-in r [:body :q])]
    (filters/add-filter t q state)
    "success: filter was added!"))

(defn get-filters [r]
  (let [s (clojure.string/join "\n" (into [] (get @state :filters)))]
    (str "all filters: " s)))

(defn get-messages [r]
  (str "all message by filter: " 
       (filters/get-messages (get-in r [:body :id] -1) state)))

(defroutes app-routes
  (GET "/" [] "Web API sample. Get data from Kafka.")
  (GET "/filter" [id] get-filters)
  (DELETE "/filter" [r] delete-filter)
  (POST "/filter" [r] add-filter)
  (POST "/msg" [r] get-messages)
  (route/not-found "Not Found"))

(def app
  (wrap-json-body
    app-routes {:keywords? true}
    (assoc-in site-defaults [:security :anti-forgery] false)))

