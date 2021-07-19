(ns rsspaper.html
  (:require
   [clojure.java.io :as io]
   [rsspaper.config :refer [config]]
   [clojure.string :as str]
   [selmer.parser :as s]))

(defn make-html
  [articles]
  ;; Render html in dist/index.html
  (let [dir-dist "dist/"
        path-theme (io/resource (str "themes/" (:theme config) "/"))
        path-dist-index (str dir-dist "index.html")

        ]
    ;; Remove old index.html
    #_(when (.exists (io/file path-dist-index)) (io/delete-file path-dist-index))
    ;; Make dir dist
    #_(.mkdir (java.io.File. dir-dist))
    ;; Make dist/index.html
    #_(with-open [wrtr (io/writer path-dist-index)]
      (.write wrtr (s/render-file (str path-theme "index.html") {:title (:title config)
                                                                                 :articles articles})))
    ;; Make statics
    ;; Iterate statics
    (doseq [file (.listFiles (io/file path-theme) )]
      (let [path (.getPath file)
            relative-path (second (str/split path  #"resources/"))
            relative-dist-path (str dir-dist relative-path)]
        ;; Remove old static
        (when (.exists (io/file relative-dist-path)) (io/delete-file relative-dist-path))
        ;; Copy new static
        (io/copy (io/file path) (io/file dir-dist))))))
