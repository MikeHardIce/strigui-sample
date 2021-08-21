(ns strigui-sample.core
  (:require [strigui.core :as gui]
            [strigui-sample.widget-stacks :as st])
  (:gen-class))

(defn -main
  "Little example"
  [& args]
  (gui/window! 300 400 "Strigui")
  (gui/label! "title" "Volume" {:x 100 :y 50 :color [0xffaa11] :font-size 20 :font-style [:bold]})
  (gui/button! "left" "<" {:x 150 :y 200 :color [0x001133 :orange]
                          :font-style [:bold] :min-width 30 :can-tab? true})
  (gui/button! "right" ">" {:x 200 :y 200 :color [0x001133 :orange]
                                  :font-style [:bold] :min-width 30 :can-tab? true :selected true})
  (let [volume (atom 50)]
    (gui/create! (st/->Stack "volume" @volume {:x 70 :y 70 :max 100}))
    (gui/label! "lbl-volume" @volume {:x 150 :y 150 :color [0xffaa11] :font-size 20 :font-style [:bold] })
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