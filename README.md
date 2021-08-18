# Strigui example project

A tiny example of how to use strigui (https://github.com/MikeHardIce/strigui).

```
lein deps
lein run
```

![](resources/strigui-example.png)

```
(ns strigui-sample.core
  (:require [strigui.core :as gui]
            [strigui-sample.widget-stacks :as st])
  (:gen-class))

(defn -main
  "Little example"
  [& args]
  (gui/window! 600 600 "Strigui")
  (gui/label! "welcome" "Welcome to Strigui" {:x 190 :y 20
                                             :color [0xffaa11]
                                             :font-size 20 :font-style [:bold]})
  (gui/button! "a" "Hello World!" {:x 50 :y 50 :color [0x001133 :orange] :min-width 250 :can-tab? true})
  (gui/button! "b" "How are you?" {:x 50 :y 100 :color [0x001133 :orange]
                                  :font-size 20 :font-style [:bold] :min-width 250})
  (gui/button! "c" "Blah" {:x 50 :y 180 :color [0x001133 :orange] :min-width 150 :can-tab? true})
  (gui/button! "d" "Bye" {:x 50 :y 230 :color [0x001133 :orange] :min-width 150})
  (gui/button! "e" "t" {:x 50 :y 280 :color [0x001133 :orange] :min-width 150})
  (gui/input! "inp2" "" {:x 320 :y 180 :color [:grey :orange] :min-width 150})
  (gui/input! "inp3" "last" {:x 320 :y 230 :color [:grey :orange] :min-width 150})
  (gui/create! (st/->Stack "stacks" '(5 1 8 2 0 3 0 5 7) {:x 100 :y 400}))
  (gui/find-by-name "inp2")
  (gui/remove! "inp1")
  (gui/update! "inp3" :value "Hello")
  (gui/update! "a" :events {:mouse-clicked (fn [wdg]
                                             (gui/info "Button A pressed"))})
  (gui/update! "b" [:events :mouse-clicked] (fn [wdg]
                                              (gui/info "Button B clicked")))
  (gui/update! "inp3" [:events :key-pressed] (fn [wdg code]
                                               (println (str "code in event: " code))
                                               (when (= code :enter)
                                                 (gui/info "EEENNNTTTEERRR!!!"))))
  (gui/update! "stacks" [:events :mouse-clicked] (fn [wdg]
                                                   (gui/update! "welcome" :value "Stack clicked!"))))
```

The "stacks" widget at the bottom doesn't exist in strigui and is defined in [widget_stacks.clj](src/strigui_sample/widget_stacks.clj) as a new widget.

The new widget is registered and drawn via 
```
(gui/create (st/->Stack "stacks" '(5 1 8 2 0 3 0 5 7) {:x 100 :y 400}))
```