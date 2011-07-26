(defproject dojo-july "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dev-dependencies [[swank-clojure "1.3.0-SNAPSHOT"]
                     [lein-ring "0.4.5"]]
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [compojure "0.6.5"]
                 [hiccup "0.3.6"]]
  
  :ring {:handler dojo-july.core/app})
