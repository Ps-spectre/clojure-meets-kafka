(defproject clojure-meets-kafka "0.1.0-SNAPSHOT"
  :description "Simple Kafka filtering system."
  :url "https://github.com/Ps-spectre/clojure-meets-kafka"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [org.clojure/core.async "0.2.374"]
                 [com.taoensso/timbre "4.3.1"]
                 [clj-kafka.franzy/core "2.0.7"]
                 [clj-kafka.franzy/embedded "2.0.7"]
                 [clj-kafka.franzy/nippy "2.0.7"]
                 [org.slf4j/slf4j-api "1.7.19"]
                 [org.slf4j/slf4j-nop "1.7.19"]
                 [log4j/log4j "1.2.17"]]

  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler clojure-meets-kafka.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
