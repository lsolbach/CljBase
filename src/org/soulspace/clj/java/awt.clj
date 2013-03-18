(ns org.soulspace.clj.java.awt
  (:use [org.soulspace.clj.java beans])
  (:import [java.awt BasicStroke Color Dimension Event Font GradientPaint TexturePaint]))

; AWT

; Dimension
(defn dimension
  [width height]
  (Dimension. width height))

; Color
(def color-by-name {:black Color/BLACK
                    :blue Color/BLUE
                    :cyan Color/CYAN
                    :dark-gray Color/DARK_GRAY
                    :gray Color/GRAY
                    :green Color/GREEN
                    :light-gray Color/LIGHT_GRAY
                    :magenta Color/MAGENTA
                    :orange Color/ORANGE
                    :pink Color/PINK
                    :red Color/RED
                    :white Color/WHITE
                    :yellow Color/YELLOW})

(defn color
  ([value]
    (Color. value))
  ([r g b]
    (Color. r g b))
  ([r g b a]
    (Color. r g b a)))

; Paint
(defn gradient-paint
  ([p1 color1 p2 color2]
  (GradientPaint. p1 color1 p2 color2))
  ([p1 color1 p2 color2 cyclic]
  (GradientPaint. p1 color1 p2 color2 cyclic))
  ([x1 y1 color1 x2 y2 color2]
  (GradientPaint. x1 y1 color1 x2 y2 color2))
  ([x1 y1 color1 x2 y2 color2 cyclic]
  (GradientPaint. x1 y1 color1 x2 y2 color2 cyclic)))

(defn texture-paint
  [texture anchor]
  (TexturePaint. texture anchor))

; Font
(def font-styles {:bold Font/BOLD
                  :italic Font/ITALIC
                  :plain Font/PLAIN
                  :center-baseline Font/CENTER_BASELINE
                  :hanging-baseline Font/HANGING_BASELINE
                  :roman-baseline Font/ROMAN_BASELINE})

(def font-names {:dialog Font/DIALOG
                 :dialog-input Font/DIALOG_INPUT
                 :monospaced Font/MONOSPACED
                 :sans-serif Font/SANS_SERIF
                 :serif Font/SERIF})

(defn font
  ([font]
    (Font. font))
  ([font-name style-vec size]
    (Font. font-name (reduce + style-vec) size)))

(defn derive-font
  ([font style-vec]
    (.derive font (reduce + style-vec)))
  ([fontname style-vec size]
    (.derive font (reduce + style-vec) size)))

; Stroke
(def stroke-cap-styles {:cap_butt BasicStroke/CAP_BUTT
                        :cap_round BasicStroke/CAP_ROUND
                        :cap_square BasicStroke/CAP_SQUARE})

(def stroke-join-styles {:join_miter BasicStroke/JOIN_MITER
                         :join_round BasicStroke/JOIN_ROUND
                         :join_bevel BasicStroke/JOIN_BEVEL})

(defn basic-stroke
  ([]
    (BasicStroke.))
  ([width]
    (BasicStroke. width))
  ([width cap-style join-style]
    (BasicStroke. width cap-style join-style))
  ([width cap-style join-style miter-limit]
    (BasicStroke. width cap-style join-style miter-limit))
  ([width cap-style join-style miter-limit dash dash-phase]
    ; TODO check that into-array works as expected
    (BasicStroke. width cap-style join-style miter-limit (into-array dash) dash-phase))
  )
