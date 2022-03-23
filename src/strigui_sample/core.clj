(ns strigui-sample.core
  (:require [strigui.core :as gui]
            [strigui.widget :as wdg]
            [strigui-sample.widget-stacks])
  (:gen-class))

(defn update-volume 
  [wdgs volume]
  (-> wdgs
      (assoc-in ["volume" :value] volume)
      (assoc-in ["lbl-volume" :value] volume)))

(defn -main
  "Little example"
  [& args]
  (gui/from-file! "gui-test.edn")
  (gui/swap-widgets! (fn [wdgs]
                       (-> wdgs 
                           (gui/attach-event "left" :mouse-clicked (fn [widgets _]
                                                                     (let [volume (dec (:value (get widgets "lbl-volume")))]
                                                                       (if (>= volume 0)
                                                                         (update-volume widgets volume)
                                                                         widgets))))
                           (gui/attach-event "right" :mouse-clicked (fn [widgets _]
                                                                      (let [volume (inc (:value (get widgets "lbl-volume")))]
                                                                        (if (<= volume 100)
                                                                          (update-volume widgets volume)
                                                                          widgets))))))))

(defmethod wdg/widget-global-event :key-pressed
 [_ widgets char code]
   (cond 
     (= code 37) (let [volume (dec (:value (get widgets "lbl-volume")))]
                   (if (>= volume 0)
                     (update-volume widgets volume)
                     widgets))
     (= code 39) (let [volume (inc (:value (get widgets "lbl-volume")))]
                   (if (<= volume 100)
                     (update-volume widgets volume)
                     widgets))
     :else widgets))