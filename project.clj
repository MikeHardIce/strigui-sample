(defproject strigui-sample "0.1.0-SNAPSHOT"
  :description "A little example how to use strigui"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [strigui "0.0.1-alpha12"]
                 [clojure2d "1.4.2"]]
  :main ^:skip-aot strigui-sample.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
