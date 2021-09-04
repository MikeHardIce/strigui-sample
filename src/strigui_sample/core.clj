(ns strigui-sample.core
  (:require [strigui.core :as gui]
            [strigui-sample.widget-stacks])
  (:gen-class))

(defn -main
  "Little example"
  [& args]
  (gui/from-file "gui-test.edn")
  (let [volume (atom 50)]
    (gui/update! "left" :events {:mouse-clicked (fn [wdg]
                                                  (when (> @volume 0)
                                                    (swap! volume dec)
                                                    (gui/update! "volume" :value @volume)
                                                    (gui/update! "lbl-volume" :value @volume)))})
    (gui/update! "right" [:events :mouse-clicked] (fn [wdg]
                                                    (when (< @volume 100)
                                                      (swap! volume inc)
                                                      (gui/update! "volume" :value @volume)
                                                      (gui/update! "lbl-volume" :value @volume))))))