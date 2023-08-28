(defproject strigui-sample "0.1.0-SNAPSHOT"
  :description "A little example how to use strigui"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 ;;[strigui "0.0.1-alpha32"]
                 [com.github.mikehardice/capra "0.0.9"]]
  :main ^:skip-aot strigui-sample.core
  :resource-paths ["resources/strigui-0.0.1-alpha32.jar"]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
