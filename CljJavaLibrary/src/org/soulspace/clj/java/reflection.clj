;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.java.reflection
  (:refer-clojure :exclude [methods]))

(defn annotations
  "Returns a sequence of the annotations of the class."
  [cl]
  (seq (.getAnnotations cl)))

(defn declared-annotations
  "Returns a sequence of the declared annotations of the class."
  [cl]
  (seq (.getDeclaredAnnotations cl)))

(defn fields
  "Returns a sequence of the fields of the class."
  [cl]
  (seq (.getFields cl)))

(defn declared-fields
  "Returns a sequence of the declared fields of the class."
  [cl]
  (seq (.getDeclaredFields cl)))

(defn methods
  "Returns a sequence of the methods of the class."
  [cl]
  (seq (.getMethods cl)))

(defn declared-methods
    "Returns a sequence of the declared methods of the class."
  [cl]
  (seq (.getDeclaredMethods cl)))

(defn find-method
  "Returns the method of the given name if found on class."
  [cl method-name]
  (filter #(= (.getName %) method-name) (methods cl)))

(defn parameter-types
  "Returns a sequence with the parameter types of the method."
  [method]
  (seq (.getParameterTypes method)))

; (map #(.getName %) (methods (.getClass (javax.swing.JTextField.))))
; (filter getter? (methods (.getClass (javax.swing.JTextField.))))
; (map #(.getName %) (filter getter? (methods (.getClass (javax.swing.JTextField.)))))
