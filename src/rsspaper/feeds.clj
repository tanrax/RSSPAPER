(ns rsspaper.feeds
  (:require
   [rsspaper.config :refer [config]]
   [clj-http.client :as client]
   [clj-time.core :as t]
   [clj-time.coerce :as c]
   [clj-time.format :as f]
   [remus :refer [parse-url]]))

(def date-custom-formatter (f/formatter "dd MM yyyy"))

(defn datetimes-to-unixtime
  [articles]
  (map (fn [article]
         (assoc article :published-date (c/to-long (:published-date article)))) articles))

(defn filter-edition
  [articles]
  (let [daily (c/to-long (t/minus (t/now) (t/days 1)))
        weekly (c/to-long (t/minus (t/now) (t/weeks 1)))]
    (case (:edition config)
      "daily" (filter (fn [article] (and (not (nil? (:published-date article))) (>= (:published-date article) daily))) articles)
      "weekly" (filter (fn [article] (and (not (nil? (:published-date article))) (>= (:published-date article) weekly))) articles)
      articles)))

(defn add-datetimes-formatter
  [articles]
  (map (fn [article]
         (assoc article :published-date-formatter (f/unparse date-custom-formatter (c/from-long (:published-date article))))) articles))

(defn zip-feeds-in-articles
  [feeds]
  ;; Flat all articles
  (reduce (fn [articles feed]
            ;; Add in every article, all information from feed
            (concat articles (map (fn [article] (assoc article :feed (:feed (update-in feed [:feed] dissoc :entries)))) (get-in feed [:feed :entries])))) [] feeds))

(defn add-cover-article
  [articles]
  ;; Add cover to article search first image in description
  ;; Iterate every blog
  (map (fn [article]
         (let [url-article (get-in article [:feed :link])
               html (:body (client/get url-article {:insecure? true}))
               first-content (second (re-find #"<meta.*content=\"([^\">]+)\".*property=\"og:image(?::url)?\".*>" html))
               second-content (second (re-find #"<meta.*property=\"og:image(?::url)?\".*content=\"([^\">]+)\".*>" html))
               first-image (second (re-find #"<img[^>]+src=\"([^\">]+)\"" html))
               images [first-content second-content first-image]
               final-image (first (filter (fn [item] (not (nil? item))) images))]
           (assoc article :cover final-image))) articles))

(defn order-published
  [articles]
  ;; Order articles
  (reverse (sort-by :published-date articles)))

(defn get-articles
  []
  ;; Get all feeds from config -> feeds
  (->
   (reduce
    (fn [feeds feed-url]
       ; Read feed
      (let [feed (parse-url feed-url {:insecure? true :throw-exceptions false})]
         ; Check is not null
        (if-not (nil? feed)
           ; Add feed
          (conj feeds feed)
           ; Alert fail
          (prn (str "Error with '" feed-url) "'"))))
    [] (:feeds config))
   zip-feeds-in-articles
   datetimes-to-unixtime
   filter-edition
   order-published
   add-cover-article
   add-datetimes-formatter))