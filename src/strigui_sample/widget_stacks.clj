(ns strigui-sample.widget-stacks
  (:require [strigui.widget :as wdg]
            [capra.core :as c])
  (:import [java.awt Color]))

(def ^:private width-per-stack 41)

(defn draw-item-lines
  [window val x y]
  (loop [y-offset (- y 4)
         curr-val val]
    (when (> curr-val 0)
      (c/draw-> window
        (c/rect x y-offset 30 3 Color/green 2))
      (recur (- y-offset 4) (- curr-val 1)))))

(defn draw-stack
  [canvas val x y h]
  (let [height (+ h 2)]
    (draw-item-lines canvas val (+ x 3) (+ y height))))

(defrecord Stack [name value props]
  wdg/Widget
  (coord [this _] 
    [(:x (:props this))
                        (:y (:props this))
                        (+  width-per-stack 35)
                        (* (-> this :props :max) 4)])
  (defaults [this] this)
  (before-drawing [this] this)
  (draw [this window]
    (let [[x y _ h] (wdg/coord this window)]
      (draw-stack window (:value this) x y h)
      this))
  (after-drawing [this] this))