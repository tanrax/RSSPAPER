(ns rsspaper.core
  (:require
   [rsspaper.feeds :refer [get-feeds]]
   [rsspaper.html :refer [make-html]]) (:gen-class))

(defn -main [& args]
  ;; Main
  (prn "Reading feeds... Please be patient.")
  (make-html (get-feeds))
  (prn "Generated \uD83D\uDCF0 in 'dist/index.html'!"))