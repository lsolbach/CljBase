(ns org.soulspace.clj.java.reflection)

(defn annotation-seq
  "Returns a sequence of the annotations of the class."
  [cl]
  (seq (.getAnnotations cl)))

(defn declared-annotation-seq
  "Returns a sequence of the declared annotations of the class."
  [cl]
  (seq (.getDeclaredAnnotations cl)))

(defn field-seq
  "Returns a sequence of the fields of the class."
  [cl]
  (seq (.getFields cl)))

(defn declared-field-seq
  "Returns a sequence of the declared fields of the class."
  [cl]
  (seq (.getDeclaredFields cl)))

(defn method-seq
  "Returns a sequence of the methods of the class."
  [cl]
  (seq (.getMethods cl)))

(defn declared-method-seq
    "Returns a sequence of the declared methods of the class."
  [cl]
  (seq (.getDeclaredMethods cl)))

(defn find-method
  "Returns the method of the given name if found on class."
  [cl method-name]
  (filter #(= (.getName %) method-name) (method-seq cl)))

(defn method-params [method]
  (.getParameterTypes method))

; (map #(.getName %) (method-seq (.getClass (javax.swing.JTextField.))))
; (filter getter? (method-seq (.getClass (javax.swing.JTextField.))))
; (map #(.getName %) (filter getter? (method-seq (.getClass (javax.swing.JTextField.)))))
