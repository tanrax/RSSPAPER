(ns rsspaper.feeds
  (:require
   [rsspaper.config :refer [config]]
   [clj-time.coerce :as c]
   [clj-time.format :as f]
   [remus :refer [parse-url]]))

(def date-custom-formatter (f/formatter "dd MM yyyy"))

(defn datetimes-to-unixtime
  [articles]
  (map (fn [article]
         (assoc article :published-date (c/to-long (:published-date article)))) articles))


(defn add-datetimes-formatter
  [articles]
  (map (fn [article]
         (assoc article :published-date-formatter (f/unparse date-custom-formatter (c/from-long (:published-date article))))) articles))


(defn zip-feeds-in-articles
  [feeds]
  ;; Flat all articles
  (reduce (fn [articles feed]
            ;; Add in every article, all information from feed
             (concat articles (map (fn [article] (assoc article :feed (:feed (update-in feed [:feed] dissoc :entries)) )) (get-in feed [:feed :entries])))) [] feeds))


(defn add-cover-article
  [articles]
  ;; Add cover to article search first image in description
  ;; Iterate every blog
  (map (fn [article]
         (assoc article :cover (second (re-find #"<img[^>]+src=\"([^\">]+)\"" (str  (get-in article [:description :value])))))
         ) articles))


(defn get-articles
  []
  ;; Get all feeds from config -> feeds
  (reverse (sort-by :published-date (->
     (reduce
       (fn [feeds feed-url]
         (conj feeds
               (parse-url feed-url {:insecure? true :throw-exceptions false}))
         ) [] (:feeds config))
     zip-feeds-in-articles
     add-cover-article
     datetimes-to-unixtime
     add-datetimes-formatter))))