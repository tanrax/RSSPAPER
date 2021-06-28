(ns rsspaper.html
  (:require
   [clojure.java.io :as io]
   [rsspaper.config :refer [config]]
   [selmer.parser :as s]))

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
                                                                           :data data
                                                                           })))))