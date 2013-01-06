(ns org.soulspace.clj.java.type-conversion)

(defmulti coerce
  "coerce clojure data types to java data types"
  (fn [dest-class obj] [dest-class (class obj)]))
(defmethod coerce [Boolean/TYPE Boolean] [_ obj]
  obj)
(defmethod coerce [Integer/TYPE Long] [_ obj]
  (Integer. (.intValue obj)))
(defmethod coerce [Short/TYPE Long] [_ obj]
  (Short. (.shortValue obj)))
(defmethod coerce [Byte/TYPE Long] [_ obj]
  (Byte. (.byteValue obj)))
(defmethod coerce [Character/TYPE Long] [_ obj]
  (Character. (.charValue obj)))
(defmethod coerce [java.io.File String] [_ str] 
  (java.io.File. str))
(defmethod coerce [java.util.List clojure.lang.PersistentVector] [_ v]
  (java.util.ArrayList. v))
(defmethod coerce :default [dest-class obj] 
   ;(println (str (.getSimpleName dest-class) " " (type obj)))
   (cast dest-class obj))



