(ns rsspaper.html
  (:require
   [clojure.java.io :as io]
   [rsspaper.config :refer [config]]
   [selmer.parser :as s]))

(defn add-cover-article
  [data]
  ;; Add cover to article search first image in description
  ;; Iterate every blog
  (map (fn [blog]
         ;; Replace entries
         (assoc-in blog [:feed :entries]
                   ;; New entries with add cover
                   (map (fn [article]
                          (assoc article :cover (second (re-find #"<img[^>]+src=\"([^\">]+)\"" (str  (get-in article [:description :value]))))))
                        (get-in blog [:feed :entries])) )
         ) data))

(defn make-html
  [data]
  ;; Render html in dist/index.html
  (let [dir "dist"
        path (str dir "/index.html")]
    ;; Remove old index.html
    (when (.exists (io/file path)) (io/delete-file path))
    ;; Make dir dist
    (.mkdir (java.io.File. dir))
    ;; Make dist/index.html
    (with-open [wrtr (io/writer path)]
      (.write wrtr (s/render-file (str "themes/" (:theme config) ".html") {
                                                                           :title (:title config)
                                                                           :data (add-cover-article data)
                                                                           })))))