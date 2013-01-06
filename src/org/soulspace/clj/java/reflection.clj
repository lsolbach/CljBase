(ns org.soulspace.clj.java.reflection)

(defn annotation-seq [cl]
  (seq (.getAnnotations cl)))

(defn declared-annotation-seq [cl]
  (seq (.getDeclaredAnnotations cl)))

(defn field-seq [cl]
  (seq (.getFields cl)))

(defn declared-field-seq [cl]
  (seq (.getDeclaredFields cl)))

(defn method-seq [cl]
  (seq (.getMethods cl)))

(defn declared-method-seq [cl]
  (seq (.getDeclaredMethods cl)))

(defn find-method [cl method-name]
  (filter #(= (.getName %) method-name) (method-seq cl)))

(defn method-params [method]
  (.getParameterTypes method))

; (map #(.getName %) (method-seq (.getClass (javax.swing.JTextField.))))
; (filter getter? (method-seq (.getClass (javax.swing.JTextField.))))
; (map #(.getName %) (filter getter? (method-seq (.getClass (javax.swing.JTextField.)))))
