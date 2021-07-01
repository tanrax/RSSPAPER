(ns rsspaper.feeds
  (:require
   [rsspaper.config :refer [config]]
   [clj-time.coerce :as c]
   [clj-time.format :as f]
   [remus :refer [parse-url]]))

(def date-custom-formatter (f/formatter "dd MM yyyy"))

(defn datetimes-to-unixtime
  [data]
  (map (fn [blog]
         (assoc-in blog [:feed :entries]
                   (map (fn [article]
                            (assoc article :published-date (c/to-long (:published-date article))))
                        (get-in blog [:feed :entries])))) data))


(defn add-datetimes-formatter
  [data]
  (map (fn [blog]
         (assoc-in blog [:feed :entries]
                   (map (fn [article]
                          (assoc article :published-date-formatter (f/unparse date-custom-formatter (c/from-long (:published-date article)))))
                   (get-in blog [:feed :entries])))) data))


(defn get-feeds
  []
  ;; Get all feeds from config -> feeds
  (->
     (reduce
       (fn [feeds feed-url]
         (conj feeds
               (parse-url feed-url {:insecure? true :throw-exceptions false}))
         ) [] (:feeds config))
     datetimes-to-unixtime
     add-datetimes-formatter))

