(ns strigui-sample.widget-stacks
  (:require [strigui.widget :as wdg]
            [clojure2d.core :as c2d]))

(def ^:private width-per-stack 41)

(defn draw-item-lines
  [canvas val x y]
  (loop [y-offset (- y 3)
         curr-val val]
    (when (> curr-val 0)
      (c2d/with-canvas-> canvas
        (c2d/set-color :green)
        (c2d/set-stroke 2)
        (c2d/line x y-offset (+ x 30) y-offset))
      (recur (- y-offset 3) (- curr-val 1)))))

(defn draw-stack
  [canvas val x y h]
  (let [height (+ h 2)]
    (draw-item-lines canvas val (+ x 3) (+ y height))))

(defn draw-stacks
  [canvas stack-vals x y max-height]
  (loop [x-offset x
         cur-index 0]
    (when (< cur-index (count stack-vals))
      (draw-stack canvas (nth stack-vals cur-index) x-offset y max-height)
      (recur (+ x-offset 45) (inc cur-index)))))

(defrecord Stack [name value args]
  wdg/Widget
  (coord [this canvas] [(:x (:args this))
                        (:y (:args this))
                        (+  width-per-stack 35)
                        (* (-> this :args :max) 3)])
  (value [this] (:value this))
  (args [this] (:args this))
  (widget-name [this] (:name this))
  (draw [this canvas]
    (let [[x y _ _] (wdg/coord this canvas)]
      (draw-stacks canvas (wdg/value this) x y (-> this :args :max (* 3)))
      this)))