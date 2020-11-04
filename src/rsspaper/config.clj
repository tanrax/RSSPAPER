(ns rsspaper.config
    (:require
     [clj-yaml.core :as yaml]))

(def config (yaml/parse-string (slurp "config.yaml")))
