(defproject rsspaper "1.2.3"
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
                 ;; Date
                 [clj-time "0.15.2"]
                 ;; HTTP client
                 [clj-http "3.12.3"]
                 ;; Utils dirs
                 [me.raynes/fs "1.4.6"]
                 ;; Parse RSS/Atom feeds
                 [remus "0.2.1"]
                 [org.slf4j/slf4j-log4j12 "1.7.25"]
                 [log4j/log4j "1.2.12" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jmdk/jmxtools
                                                    com.sun.jmx/jmxri]]
                 ;; Make RSS/Atom feeds
                 ;[clj-rss "0.2.7"]
                 ]
  :plugins [;; DEV TOOLS
            ;;; Check idiomatic bug
            [lein-kibit "0.1.7"]
            ;;; Check format
            [lein-cljfmt "0.6.4"]
            [lein-shell "0.5.0"]]
  ;; ALIAS
  :aliases {"check-idiomatic" ["kibit" "src"]
            "check-format"    ["cljfmt" "check"]
            "fix-format"    ["cljfmt" "fix"]
            "native" ["shell" "native-image" "--report-unsupported-elements-at-runtime" "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}" "-H:Name=./target/${:name}"]
            }
;; LEIN
  :main ^:skip-aot rsspaper.core
  :aot  [rsspaper.core]
  :repl-options {:init-ns rsspaper.core})
