(ns org.soulspace.clj.java.awt.awtlib
  (:use [org.soulspace.clj.java beans])
  (:import [java.awt Color Event Font]
           [java.awt.event
            ActionListener AdjustmentListener ComponentAdapter ComponentListener
            ContainerAdapter ContainerListener FocusAdapter FocusListener
            HierarchyBoundsAdapter HierarchyBoundsListener HierarchyListener
            ItemListener KeyAdapter KeyEvent
            MouseAdapter MouseListener MouseMotionAdapter MouseMotionListener MouseWheelListener
            TextListener WindowAdapter WindowFocusListener WindowListener WindowStateListener]))

; AWT
; Listeners
(defn action-listener [f args]
  (proxy [ActionListener] []
    (actionPerformed [event] (apply f event args))))

(defn adjustment-listener [f args]
  (proxy [AdjustmentListener] []
    (adjustmentValueChanged [event] (f event args))))

(defn component-hidden-listener [f args]
  (proxy [ComponentAdapter] []
    (componentHidden [event] (f event args))))

(defn component-moved-listener [f args]
  (proxy [ComponentAdapter] []
    (componentMoved [event] (f event args))))

(defn component-resized-listener [f args]
  (proxy [ComponentAdapter] []
    (componentResized [event] (f event args))))

(defn component-shown-listener [f args]
  (proxy [ComponentAdapter] []
    (componentResized [event] (f event args))))

(defn container-added-listener [f args]
  (proxy [ContainerAdapter] []
    (componentAdded [event] (f event args))))

(defn container-removed-listener [f args]
  (proxy [ContainerAdapter] []
    (componentRemoved [event] (f event args))))

(defn focus-gained-listener [f args]
  (proxy [FocusAdapter] []
    (focusGained [event] (f event args))))

(defn focus-lost-listener [f args]
  (proxy [FocusAdapter] []
    (focusLost [event] (f event args))))

(defn hierarchy-bounds-moved-listener [f args]
  (proxy [HierarchyBoundsAdapter] []
    (ancestorMoved [event] (f event args))))

(defn hierarchy-bounds-resized-listener [f args]
  (proxy [HierarchyBoundsAdapter] []
    (ancestorResized [event] (f event args))))

(defn hierarchy-listener [f args]
  (proxy [HierarchyListener] []
    (hierarchyChanged [event] (f event args))))

; TODO input method (create adapter in clojure so there's a common programming model)

(defn item-listener [f args]
  (proxy [ItemListener] []
    (itemStateChanged [event] (f event args))))

(defn key-pressed-listener [f args]
  (proxy [KeyAdapter] []
    (keyPressed [event] (apply f event args))))

(defn key-released-listener [f args]
  (proxy [KeyAdapter] []
    (keyReleased [event] (apply f event args))))

(defn key-typed-listener [f args]
  (proxy [KeyAdapter] []
    (keyTyped [event] (apply f event args))))

(defn mouse-clicked-listener [f args]
  (proxy [MouseAdapter] []
    (mouseClicked [event] (f event args))))

(defn mouse-entered-listener [f args]
  (proxy [MouseAdapter] []
    (mouseEntered [event] (f event args))))

(defn mouse-exited-listener [f args]
  (proxy [MouseAdapter] []
    (mouseExited [event] (f event args))))

(defn mouse-pressed-listener [f args]
  (proxy [MouseAdapter] []
    (mousePressed [event] (f event args))))

(defn mouse-released-listener [f args]
  (proxy [MouseAdapter] []
    (mouseReleased [event] (f event args))))

(defn mouse-motion-dragged-listener [f args]
  (proxy [MouseMotionAdapter] []
    (mouseDragged [event] (f event args))))

(defn mouse-motion-moved-listener [f args]
  (proxy [MouseMotionAdapter] []
    (mouseMoved [event] (f event args))))

(defn mouse-wheel-listener [f args]
  (proxy [MouseWheelListener] []
    (mouseWheelMoved [event] (f event args))))

(defn text-listener [f args]
  (proxy [TextListener] []
    (textValueChanged [event] (f event args))))

(defn window-focus-gained-listener [f args]
  (proxy [WindowListener] []
    (windowGainedFocus [event] (f event args))))

(defn window-focus-lost-listener [f args]
  (proxy [WindowListener] []
    (windowLostFocus [event] (f event args))))

(defn window-activated-listener [f args]
  (proxy [WindowListener] []
    (windowActivated [event] (f event args))))

(defn window-closed-listener [f args]
  (proxy [WindowListener] []
    (windowClosed [event] (f event args))))

(defn window-closing-listener [f args]
  (proxy [WindowListener] []
    (windowClosing [event] (f event args))))

(defn window-deactivated-listener [f args]
  (proxy [WindowListener] []
    (windowDeactivated [event] (f event args))))

(defn window-deiconified-listener [f args]
  (proxy [WindowListener] []
    (windowDeiconified [event] (f event args))))

(defn window-iconified-listener [f args]
  (proxy [WindowListener] []
    (windowIconified [event] (f event args))))

(defn window-opened-listener [f args]
  (proxy [WindowListener] []
    (windowOpened [event] (f event args))))

(defn window-state-changed-listener [f args]
  (proxy [WindowListener] []
    (windowStateChanged [event] (f event args))))

; Color
(defn color
  ([value]
    (Color. value))
  ([r g b]
    (Color. r g b)))

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