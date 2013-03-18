(ns org.soulspace.clj.java.awt.geometry
  (:import [java.awt.geom Arc2D CubicCurve2D Dimension2D Ellipse2D Line2D Point2D Rectangle2D RoundRectangle2D QuadCurve2D]))

(defn arc2d
  ([ellipse-bounds start extend arc-type]
  (Arc2D/Double. ellipse-bounds start extend arc-type))
  ([x y w h start extend arc-type]
  (Arc2D/Double. x y w h start extend arc-type)))

(defn cubic-curve2d [x1 y1 ctrlx1 ctrly1 ctrlx2 ctrly2 x2 y2]
  (CubicCurve2D. x1 y1 ctrlx1 ctrly1 ctrlx2 ctrly2 x2 y2))

(defn ellipse2d [x y w h]
    (Ellipse2D. x y w h))

(defn line2d
  ([p1 p2]
    (Line2D. p1 p2))
  ([x1 y1 x2 y2]
    (Line2D. x1 y1 x2 y2)))

(defn point2d [x y]
  (Point2D. x y))

(defn quad-curve2d [x1 y1 ctrlx ctrly x2 y2]
  (QuadCurve2D. x1 y1 ctrlx ctrly x2 y2))

(defn rectangle2d [x y w h]
  (Rectangle2D. x y w h))

(defn round-rectangle2d [x y w h arc-w arc-h]
  (RoundRectangle2D. x y w h arc-w arc-h))
