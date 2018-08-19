(defproject fake-weather "0.1.0-SNAPSHOT"
  :description "fake weather api"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.12.4"]]
  :source-paths ["src" "scratch"]
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.logging "0.4.1"]
                 [clj-time "0.14.2"]
                 [commons-codec "1.10"]
                 [metosin/compojure-api "2.0.0-alpha18"]
                 [metosin/jsonista "0.1.1"]
                 [funcool/promesa "1.9.0"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-json "0.5.0-beta1"]
                 [ring/ring-jetty-adapter "1.6.3"]]
  :ring {:handler fake-weather.handler/app
         :async? true})
