(ns graphics.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:color 0
   :angle 0
   :dot [0 0]})

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  (let [[x y] (:dot state)
        xx (+ x (- (rand-int 11) 5))
        yy (+ y (- (rand-int 11) 5))]
    ;;(println x y)
    {:color (mod (+ (:color state) 0.7) 255)
     :angle (+ (:angle state) 0.1)
     :dot [(max (min xx (/ (q/width) 2)) 0)
           (max (min yy (/ (q/width) 2)) 0)
           ]}))

(defn draw-state [state]
  ; Clear the sketch by filling it with light-grey color.
  ;; (q/background 240)
  ; Set circle color.
  (q/fill (:color state) 255 255)
  ; Calculate x and y coordinates of the circle.
  (let [angle (:angle state)
        x (* 15 (q/cos angle))
        y (* 15 (q/sin angle))
        [xx yy]  (:dot state)]
    ; Move origin point to the center of the sketch.
    ;(q/with-translation [(/ (q/width) 2)
    ;                     (/ (q/height) 2)]
    ;  ; Draw the circle.
    ;  (q/ellipse x y 100 100))


    ;(q/with-translation [(/ (q/width) 2)
    ;                     (/ (q/height) 2)]
    ;                    ; Draw the circle.
    ;                    (q/ellipse x y 10 10))

    (q/no-stroke)
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
                        ; Draw the circle.
                        (q/ellipse xx yy 10 10))


    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
                        (q/ellipse (- xx)  yy 10 10))

    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
                        (q/ellipse xx  (- yy) 10 10))

    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
                        (q/ellipse (- xx)  (- yy) 10 10))
    ;(doseq [[x y] (:dot state)]
    ;  (q/with-translation [(/ (q/width) 2)
    ;                       (/ (q/height) 2)]
    ;                      ; Draw the circle.
    ;                      (q/ellipse x y 10 10)))
    )
  )


(q/defsketch graphics
  :title "You spin my circle right round"
  :size [500 500]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])
