;;;; Views public web
(ns rsspaper.views.public
  (:require
   [tadam.templates :refer [render-HTML render-JSON render-404]]
   ))

(defn index
  ;; View HTML
  [req]
    (render-HTML req "public/welcome.html" {}))

(defn api
  ;; View JSON
  [req]
    (render-JSON req {:result true}))

(defn page-404
  ;; View page 404
  [req]
  (render-404 req "public/404.html" {}))
