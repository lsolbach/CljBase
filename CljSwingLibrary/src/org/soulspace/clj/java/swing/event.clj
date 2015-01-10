;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.java.swing.event
  (:import [javax.swing.event AncestorListener CaretListener CellEditorListener ChangeListener DocumentListener
            HyperlinkListener InternalFrameListener ListDataListener ListSelectionListener
            MenuDragMouseListener MenuKeyListener MenuListener MouseInputListener PopupMenuListener
            RowSorterListener TableColumnModelListener TableModelListener
            TreeExpansionListener TreeModelListener TreeSelectionListener TreeWillExpandListener
            UndoableEditListener]))

; Listeners
; TODO add adapters for multi listeners and add listener functions

(defn ancestor-listener
  "Creates an ancestor listener. Calls function 'f' on ancestor updates."
  [added-f moved-f removed-f args]
  (proxy [AncestorListener] []
    (ancestorAdded [event] (added-f event args))
    (ancestorMoved [event] (moved-f event args))
    (ancestorRemoved [event] (removed-f event args))))

(defn ancestor-added-listener
  "Creates an ancestor listener. Calls function 'f' on ancestor updates."
  [f args]
  (proxy [AncestorListener] []
    (ancestorAdded [event] (f event args))
    (ancestorMoved [_] nil)
    (ancestorRemoved [_] nil)))

(defn ancestor-moved-listener
  "Creates an ancestor listener. Calls function 'f' on ancestor updates."
  [f args]
  (proxy [AncestorListener] []
    (ancestorAdded [_] nil)
    (ancestorMoved [event] (f event args))
    (ancestorRemoved [_] nil)))

(defn ancestor-removed-listener
  "Creates an ancestor listener. Calls function 'f' on ancestor updates."
  [f args]
  (proxy [AncestorListener] []
    (ancestorAdded [_] nil)
    (ancestorMoved [_] nil)
    (ancestorRemoved [event] (f event args))))

(defn caret-listener
  "Creates a caret listener. Calls function 'f' on caret updates."
  [f args]
  (proxy [CaretListener] []
    (caretUpdate [event] (f event args))))

(defn change-listener
  "Creates a change listener. Calls function 'f' on state changes."
  [f args]
  (proxy [ChangeListener] []
    (stateChanged [event] (f event args))))

(defn hyperlink-listener
  "Creates a hyperlink listener. Calls function 'f' on hyperlink updates."
  [f args]
  (proxy [HyperlinkListener] []
    (hyperlinkUpdate [event] (f event args))))

(defn list-selection-listener
  "Creates a list selection listener. Calls function 'f' on value changes."
  [f args]
  (proxy [ListSelectionListener] []
    (valueChanged [event] (f event args))))

; Add listeners
(defn add-ancestor-listener
  "Adds an ancestor listener to the JComponent."
  [^javax.swing.JComponent c ^javax.swing.event.AncestorListener l]
  (.addAncestorListener c l))

(defn add-change-listener
  "Adds a change listener to the JComponent."
  [c ^javax.swing.event.ChangeListener l]
  (.addChangeListener c l))

(defn add-popup-menu-listener
  "Adds a popup menu listener to the component."
  [c ^javax.swing.event.PopupMenuListener l]
  (.addPopupMenuListener c l))