;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.java.awt.graphics
  (:use [org.soulspace.clj.java beans])
  (:import [java.awt BasicStroke Color Dimension Font GradientPaint Graphics2D TexturePaint]
           [java.awt.geom Arc2D$Double CubicCurve2D$Double Dimension2D Ellipse2D$Double
            Line2D$Double Point2D$Double Rectangle2D$Double RoundRectangle2D$Double QuadCurve2D$Double]
           [javax.imageio ImageIO]))

(def stroke-cap-styles {:cap_butt BasicStroke/CAP_BUTT
                        :cap_round BasicStroke/CAP_ROUND
                        :cap_square BasicStroke/CAP_SQUARE})

(def stroke-join-styles {:join_miter BasicStroke/JOIN_MITER
                         :join_round BasicStroke/JOIN_ROUND
                         :join_bevel BasicStroke/JOIN_BEVEL})

; Paint
(defn gradient-paint
  "Create a gradient paint."
  ([p1 color1 p2 color2]
    (GradientPaint. p1 color1 p2 color2))
  ([p1 color1 p2 color2 cyclic]
    (GradientPaint. p1 color1 p2 color2 cyclic))
  ([x1 y1 color1 x2 y2 color2]
    (GradientPaint. x1 y1 color1 x2 y2 color2))
  ([x1 y1 color1 x2 y2 color2 cyclic]
    (GradientPaint. x1 y1 color1 x2 y2 color2 cyclic)))

(defn texture-paint
  "Create a texture paint."
  [texture anchor]
  (TexturePaint. texture anchor))

; Stroke
(defn basic-stroke
  "Creates basic strokes."
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
    (BasicStroke. width cap-style join-style miter-limit (into-array dash) dash-phase)))

(defn arc2d
  "Creates a 2d arc."
  ([ellipse-bounds start extend arc-type]
    (Arc2D$Double. ellipse-bounds start extend arc-type))
  ([x y w h start extend arc-type]
    (Arc2D$Double. x y w h start extend arc-type)))

(defn cubic-curve2d
  "Creates a 2d cubic curve."
  [x1 y1 ctrlx1 ctrly1 ctrlx2 ctrly2 x2 y2]
  (CubicCurve2D$Double. x1 y1 ctrlx1 ctrly1 ctrlx2 ctrly2 x2 y2))

(defn ellipse2d
  "Creates a 2d ellipse."
  [x y w h]
  (Ellipse2D$Double. x y w h))

(defn line2d
  "Creates a 2d line."
  ([p1 p2]
    (Line2D$Double. p1 p2))
  ([x1 y1 x2 y2]
    (Line2D$Double. x1 y1 x2 y2)))

(defn point2d
  "Creates a 2d point."
  [x y]
  (Point2D$Double. x y))

(defn quad-curve2d
  "Creates a 2d quadratic curve."
  [x1 y1 ctrlx ctrly x2 y2]
  (QuadCurve2D$Double. x1 y1 ctrlx ctrly x2 y2))

(defn rectangle2d
  "Creates a 2d rectangle."
  [x y w h]
  (Rectangle2D$Double. x y w h))

(defn round-rectangle2d
  "Creates a 2d rounded rectangle."
  [x y w h arc-w arc-h]
  (RoundRectangle2D$Double. x y w h arc-w arc-h))

(defn draw
  "Draws an element."
  ([^java.awt.Graphics2D gfx e]
    (.draw gfx e))
  ([^java.awt.Graphics2D gfx e color]
    (.draw gfx e color)))

(defn fill
  "Draws a filled element."
  ([^java.awt.Graphics2D gfx e]
    (.fill gfx e))
  ([^java.awt.Graphics2D gfx e color]
    (.setColor gfx color)
    (.fill gfx e)))

(defn draw-point
  "Draws a point."
  ([^java.awt.Graphics2D gfx x y]
    (.draw gfx (point2d x y)))
  ([^java.awt.Graphics2D gfx x y color]
    (.setColor gfx color)
    (.draw gfx (point2d x y)))
  )

(defn draw-line
  "Draws a line."
  ([^java.awt.Graphics2D gfx p1 p2]
    (.draw gfx (line2d p1 p2)))
  ([^java.awt.Graphics2D gfx x1 y1 x2 y2]
    (.draw gfx (line2d x1 y1 x2 y2)))
  ([^java.awt.Graphics2D gfx x1 y1 x2 y2 color]
    (.setColor gfx color)
    (.draw gfx (line2d x1 y1 x2 y2)))
  )

(defn fill-rect
  "Draws a filled rectangle."
  ([^java.awt.Graphics2D gfx x y width height]
    (.fillRect gfx x y width height))
  ([^java.awt.Graphics2D gfx x y width height color]
    (.setColor gfx color)
    (.fillRect gfx x y width height)))

(defn fill-colored-rect
  "Draws a filled rectangle with the given color."
  ([^java.awt.Graphics2D gfx x y width height color]
    (.setColor gfx color)
    (.fillRect gfx x y width height)))

;Image IO
(defn write-image
  "Writes an image in the given format to the file."
  [image format file]
  (ImageIO/write image format file))
