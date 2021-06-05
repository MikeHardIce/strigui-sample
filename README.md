# Strigui example project

A tiny example of how to use strigui.

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
  (gui/label "welcome" "Welcome to Strigui" {:x 190 :y 20
                                             :color [0xffaa11]
                                             :font-size 20 :font-style [:bold]})
  (gui/button "a" "Hello World!" {:x 50 :y 50 :color [:0xff1111 :green]})
  (gui/button "b" "How are you?" {:x 50 :y 100 :color [0x223344 :blue]
                                  :font-size 20 :font-style [:bold]})
  (gui/button "c" "Blah" {:x 50 :y 150 :color [:blue :yellow] :min-width 100})
  (gui/button "d" "Bye" {:x 50 :y 200 :color [:yellow :green] :min-width 100})
  (gui/button "e" "t" {:x 50 :y 250 :color [:green :red]})
  (gui/input "inp2" "" {:x 350 :y 100 :color [:white :red] :min-width 100})
  (gui/input "inp3" "last" {:x 350 :y 150 :color [:white :red] :min-width 100})
  (gui/create (st/->Stack "stacks" '(5 1 8 2 0 3 0 5 7) {:x 100 :y 400}))
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