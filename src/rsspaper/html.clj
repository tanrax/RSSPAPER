(ns rsspaper.html
  (:require
   [clojure.java.io :as io]
   [rsspaper.config :refer [config]]
   [rsspaper.feeds :refer [get-articles]]
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
  (let [dir-dist        "dist/"
        path-theme      (io/resource (str "themes/" (:theme config) "/"))
        file-index      "index.html"
        path-dist-index (str dir-dist "index.html")
        tar-static      "static.tar"
        tmp-static      (str (fs/tmpdir) "/rsspaper.tar")]
    ;; Remove old index.html
    (when (.exists (io/file path-dist-index)) (io/delete-file path-dist-index))
    ;; Make dir dist
    (fs/mkdir dir-dist)
    ;; Make dist/index.html
    (selmer.parser/set-resource-path! path-theme)
    (with-open [writer (io/writer path-dist-index)]
      (.write writer (s/render-file file-index {:title    (:title config)
                                                :articles (get-articles)})))
    ;; Make static
    (copy-uri-to-file (str path-theme tar-static) tmp-static)
    (fsc/untar tmp-static dir-dist)))
