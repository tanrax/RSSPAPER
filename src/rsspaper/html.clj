(ns rsspaper.html
  (:require
    [clojure.java.io :as io]
    [rsspaper.config :refer [config]]
    [clojure.string :as str]
    [selmer.parser :as s]
    [me.raynes.fs :as fs]
    [me.raynes.fs.compression :as fsc]))

(defn copy-uri-to-file [uri file]
  (with-open [in (io/input-stream uri)
              out (io/output-stream file)]
    (io/copy in out)))

(defn make-html
  [articles]
  ;; Render html in dist/index.html
  (let [dir-dist "dist/"
        file-index "index.html"
        path-theme (io/resource (str "themes/" (:theme config) "/"))
        path-dist-index (str dir-dist "index.html")
        zip-static "static.zip"
        tmp-static "/tmp/rsspaper.zip"]
    ;; Remove old index.html
    (when (.exists (io/file path-dist-index)) (io/delete-file path-dist-index))
    ;; Make dir dist
    (fs/mkdir dir-dist)
    ;; Make dist/index.html
    (with-open [writer (io/writer path-dist-index)]
      (.write writer (s/render-file (str path-theme file-index) {:title (:title config)})))
    ;; Make statics
    (copy-uri-to-file (str path-theme zip-static) tmp-static)
    (fsc/unzip tmp-static dir-dist)))