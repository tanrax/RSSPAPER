(ns rsspaper.feeds
  (:require
   [rsspaper.config :refer [config]]
   [remus :refer [parse-url]]))

(defn get-feeds
  []
  ;; Get all feeds from config -> feeds
  (reduce (fn [feeds feed-url] (conj feeds (parse-url feed-url {:insecure? true :throw-exceptions false}))) [] (:feeds config)))