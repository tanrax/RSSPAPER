(ns rsspaper.urls
  (:require
   [compojure.core :refer [defroutes GET]]
   [compojure.route :as route]
   [rsspaper.views.public :as view-public]))

(defroutes public
  ;; Urls public pages
  (GET "/" [] view-public/index)
  (GET "/api" [] view-public/api))


(defroutes resources-routes
  ;; Resources (statics)
  (route/resources "/")
  (route/not-found view-public/page-404))

(def all-routes
  ;; Wrap routers. "resources-routes" should always be the last.
  (compojure.core/routes public resources-routes))
