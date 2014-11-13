;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.java.awt.geometry
  (:import [java.awt.geom Arc2D$Double CubicCurve2D$Double Dimension2D Ellipse2D$Double Line2D$Double Point2D$Double Rectangle2D$Double RoundRectangle2D$Double QuadCurve2D$Double]))

(defn arc2d
  ([ellipse-bounds start extend arc-type]
  (Arc2D$Double. ellipse-bounds start extend arc-type))
  ([x y w h start extend arc-type]
  (Arc2D$Double. x y w h start extend arc-type)))

(defn cubic-curve2d [x1 y1 ctrlx1 ctrly1 ctrlx2 ctrly2 x2 y2]
  (CubicCurve2D$Double. x1 y1 ctrlx1 ctrly1 ctrlx2 ctrly2 x2 y2))

(defn ellipse2d [x y w h]
    (Ellipse2D$Double. x y w h))

(defn line2d
  ([p1 p2]
    (Line2D$Double. p1 p2))
  ([x1 y1 x2 y2]
    (Line2D$Double. x1 y1 x2 y2)))

(defn point2d [x y]
  (Point2D$Double. x y))

(defn quad-curve2d [x1 y1 ctrlx ctrly x2 y2]
  (QuadCurve2D$Double. x1 y1 ctrlx ctrly x2 y2))

(defn rectangle2d [x y w h]
  (Rectangle2D$Double. x y w h))

(defn round-rectangle2d [x y w h arc-w arc-h]
  (RoundRectangle2D$Double. x y w h arc-w arc-h))
