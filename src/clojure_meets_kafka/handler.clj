(ns clojure-meets-kafka.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body]]
            [ring.util.response :refer [response]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn delete-filter [r] 
  (println "\n==> DELETE ID: " (get-in r [:body :id]))
  "ok")

(defn add-filter [r]
  (let [topic (get-in r [:body :topic])
        q (get-in r [:body :q])]
    (println "\n==> ADD filter: " topic ", q: " q)
    "ok"))

(defn add-message [r]
  "ok")

(defn get-filters [r]
  "ok")

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/filter" [id] get-filters)
  (DELETE "/filter" [r] delete-filter)
  (POST "/filter" [r] add-filter)
  (POST "/msg" [r] add-message)
  (route/not-found "Not Found"))

(def app
  (wrap-json-body
    app-routes {:keywords? true}
    (assoc-in site-defaults [:security :anti-forgery] false)))

