# Strigui example

A tiny example creating widgets with [strigui](https://github.com/MikeHardIce/strigui).

```
lein deps
lein run
```

![](resources/strigui-example.png)

The volumne bar is defined in [widget_stacks.clj](src/strigui_sample/widget_stacks.clj)

## Create widgets via a edn file

gui-test.edn
```Clojure
{:window [400 400 300 500 "Strigui from Edn"]
 :strigui.label/Label [["title" "Volume" {:x 100 :y 50  :color [java.awt.Color/red] :font-size 20 :font-style [:bold]}]
                       ["lbl-volume" 50 {:x 150 :y 150 :color [java.awt.Color/red] :font-size 20 :font-style [:bold]}]]
 :strigui.button/Button [["left" "<" {:x 150 :y 200 :color [java.awt.Color/red java.awt.Color/orange]
                                      :font-style [:bold] :min-width 30 :can-tab? true}]
                         ["right" ">" {:x 200 :y 200 :color [java.awt.Color/red java.awt.Color/orange]
                                       :font-style [:bold] :min-width 30 :can-tab? true}]]
 :strigui-sample.widget-stacks/Stack [["volume" 50 {:x 70 :y 70 :max 100}]]}
```
in core.clj
```Clojure
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
```

The buttons themself are responding to mouse-clicks as well as the entire program is responding to key inputs and listens for the left and right
arrow key.

Note, if you use custom widget like 
```Clojure
:strigui-sample.widget-stacks/Stack
```
in the edn file, then make sure to include the namespace when loading the file.

The "stacks" widget representing the volume doesn't exist in strigui and is defined in [widget_stacks.clj](src/strigui_sample/widget_stacks.clj) as a new widget.

If not added in the edn file, it can also be added in the code via:

```Clojure
...
(:require ...
            [strigui-sample.widget-stacks :as st])
...
(gui/swap-widgets! (fn [wdgs]
                       (gui/add wdgs (st/->Stack "stacks" '(5 1 8 2 0 3 0 5 7) {:x 100 :y 400}))))
```
