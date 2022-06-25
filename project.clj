(defproject strigui-sample "0.1.0-SNAPSHOT"
  :description "A little example how to use strigui"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [strigui "0.0.1-alpha30"]
                 [com.github.mikehardice/capra "0.0.3"]]
  :main ^:skip-aot strigui-sample.core
  ;;:resource-paths ["resources/strigui-0.0.1-alpha29.jar"]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})