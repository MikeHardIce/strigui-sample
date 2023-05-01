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

(defn focus-widget
  [wdgs name value]
  (assoc-in wdgs [name :props :focused?] value))

(defn -main
  "Little example for strigui alpha32"
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
 [_ widgets window-name char code _]
   (cond
     (= code 37) (let [volume (dec (:value (get widgets "lbl-volume")))
                       widgets (-> widgets
                                   (focus-widget "left" true)
                                   (focus-widget "right" false))]
                   (if (>= volume 0)
                     (update-volume widgets volume)
                     widgets))
     (= code 39) (let [volume (inc (:value (get widgets "lbl-volume")))
                       widgets (-> widgets
                                   (focus-widget "left" false)
                                   (focus-widget "right" true))]
                   (if (<= volume 100)
                     (update-volume widgets volume)
                     widgets))
     :else (-> widgets
               (focus-widget "left" false)
               (focus-widget "right" false))))