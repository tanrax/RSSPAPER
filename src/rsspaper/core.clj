(ns rsspaper.core
    (:require
     [rsspaper.config :refer [config]]
     [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
     [ring.middleware.reload :refer [wrap-reload]]
     [ring.middleware.cors :refer [wrap-cors]]
     [rsspaper.urls :refer [all-routes]]
     [ring.adapter.jetty :refer [run-jetty]]) (:gen-class))

(def wrapped-handler
  ;; Handler middlewares
  (-> all-routes
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
      (wrap-cors
        :access-control-allow-origin [(re-pattern (if (config :debug) ".*" (config :domain)))]
      	:access-control-allow-methods [:get])
      (#(if (config :debug) (wrap-reload %) %))))

(defn -main [& args]
  ;; Main
  ;; Welcome
  (prn (str "Open " (config :domain) ":" (config :port)))
  ;; Run web server
  (run-jetty wrapped-handler {:port (config :port)}))
