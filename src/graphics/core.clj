(ns graphics.core
  (:require [quil.core :as q]
            [quil.middleware :as m])

  ;(:require [quil.core :refer :all :as q]
  ;          [quil.helpers.seqs :refer [range-incl]]
  ;          [quil.helpers.drawing :refer [line-join-points]])

  )

(defn get-alpha [x]
  (+ 0.5001 (/ (q/sin (/ x 255)) 2)))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:color 0
   :angle 0
   :dot [0 0]
   :alpha 0})

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  (let [[x y] (:dot state)
        xx (+ x (- (rand-int 11) 5))
        yy (+ y (- (rand-int 11) 5))]
    ;; (println (:color state))
    ;; (println (get-alpha (:alpha state)))
    {:color (mod (+ (:color state) 0.7) 255)
     :angle (+ (:angle state) 0.1)
     :dot [(max (min xx (/ (q/width) 2)) 0)
           (max (min yy (/ (q/width) 2)) 0)]
     :alpha (inc (:alpha state))}))

(defn draw-state [state]
  ; Clear the sketch by filling it with light-grey color.
  ;; (q/background 240)
  ; Set circle color.
  ;; (q/fill (:color state) 255  255 (* 255 (get-alpha (:alpha state)))) ;; brights!
  ;(q/fill (:color state) (/ 255 3)  (/ 255 3) (* 255 (get-alpha (:alpha
  ;                                                                state)))) ;; muted  greens and reds

  (q/fill (:color state) 255  255 (* 255 (get-alpha (:alpha state))))


  ; Calculate x and y coordinates of the circle.
  (let [angle (:angle state)
        x (* 15 (q/cos angle))
        y (* 15 (q/sin angle))
        [xx yy]  (:dot state)]

    ;; (q/camera (get-alpha (:alpha state)) (get-alpha (:alpha state)) (get-alpha (:alpha state)) (get-alpha (:alpha state)) 0 0 0 0 -1)
    ;(q/no-stroke)
    ;(q/with-translation [(/ (q/width) 2)
    ;                     (/ (q/height) 2)]
    ;                    (q/ellipse xx yy 5 5))
    ;
    ;(q/fill (:color state) (/ 255 2 ) 255 (* 255 (get-alpha (:alpha state))))
    ;(q/with-translation [(/ (q/width) 2)
    ;                     (/ (q/height) 2)]
    ;                    (q/ellipse (- xx)  yy 5 5))
    ;
    ;(q/fill (:color state)  255 (/ 255 2 ) (* 255 (get-alpha (:alpha state))))
    ;(q/with-translation [(/ (q/width) 2)
    ;                     (/ (q/height) 2)]
    ;                    (q/ellipse xx  (- yy) 5 5))
    ;
    ;(q/fill (:color state)  0 0 (* 255 (get-alpha (:alpha state))))
    ;(q/with-translation [(/ (q/width) 2)
    ;                     (/ (q/height) 2)]
    ;                    (q/ellipse (- xx)  (- yy) 5 5))

    ;;;;;;;;;;;;;;;;
    ;; (q/camera 200 200 200 0 0 0 0 0 -1)

    ;; (q/camera (* 255 (get-alpha (:alpha state))) (/ (q/width) 2) 200 0 0 0 0 1 0)
    ; (q/box 100 )
    (q/with-translation [(+ xx (/ (q/width) 2))
                         (+ yy (/ (q/height) 2))
                         0]
                        (q/box 10 ))
    (q/with-translation [(+ xx (/ (q/width) 2))
                         (+ (- yy) (/ (q/height) 2))
                         0]
                        (q/box 10 ))
    (q/with-translation [(+ (- xx) (/ (q/width) 2))
                         (+ yy (/ (q/height) 2))
                         0]
                        (q/box 10 ))
    (q/with-translation [(+ (- xx) (/ (q/width) 2))
                         (+ (- yy) (/ (q/height) 2))
                         0]
                        (q/box 10 ))



    ;(q/with-translation [(/ (q/width) 2)
    ;                     (/ (q/height) 2)]
    ;                    (q/box (- xx)  yy 50 ))
    ;
    ;(q/with-translation [(/ (q/width) 2)
    ;                     (/ (q/height) 2)]
    ;                    (q/box xx  (- yy) 50 ))
    ;
    ;(q/with-translation [(/ (q/width) 2)
    ;                     (/ (q/height) 2)]
    ;                    (q/box (- xx)  (- yy) 50))

    ;; (q/camera (* 255 (get-alpha (:alpha state))) (* 255 (get-alpha (:alpha state)))(* 255 (get-alpha (:alpha state))) 0 0 0 0 0 -1)
    ;(q/with-translation [(/ (q/width) 2)
    ;                     (/ (q/height) 2)]
    ;
    ;                    (q/camera (* 255 (get-alpha (:alpha state))) (* 255 (get-alpha (:alpha state)))(* 255 (get-alpha (:alpha state))) 0 0 0 0 0 -1)
    ;                    (q/directional-light 255 150 150 -1 -0.76 -0.5)
    ;                    (q/box 50)
    ;                    (q/no-lights)
    ;                    ;;(q/translate 0 50 0)
    ;                    ;;(q/sphere 20)
    ;                    )

    ;(doseq [[x y] (:dot state)]
    ;  (q/with-translation [(/ (q/width) 2)
    ;                       (/ (q/height) 2)]
    ;                      ; Draw the circle.
    ;                      (q/ellipse x y 10 10)))
    )
  )

(def exter-monitor-size [1913 1030])
(q/defsketch graphics
  :title "dots"
  :size [700 700]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  :renderer :p3d
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])



