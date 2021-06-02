(defproject rsspaper "1.0.0"
  :description "RSSpaper"
  :url "https://github.com/tanrax/RSSpaper"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [;; Clojure
                 [org.clojure/clojure "1.10.3"]
                 ;; Templates
                 [selmer "1.12.12"]
                 ;; Yaml
                 [clj-yaml "0.4.0"]
                 ;; JSON encoding
                 [cheshire "5.9.0"]
                 ;; Parse RSS/Atom feeds
                 [remus "0.2.1"]
                 ;; Make RSS/Atom feeds
                 [clj-rss "0.2.6"]]
  :plugins [;; DEV TOOLS
            ;;; Check idiomatic bug
            [lein-kibit "0.1.7"]
            ;;; Check format
            [lein-cljfmt "0.6.4"]
            ;;; Generate documentation
            [lein-codox "0.10.7"]]
  ;; ALIAS
  :aliases {"check-idiomatic" ["kibit" "src"]
            "check-format"    ["cljfmt" "check"]}
  ;; LEIN
  :main ^:skip-aot rsspaper.core
  :aot  [rsspaper.core]
  :repl-options {:init-ns rsspaper.core})
