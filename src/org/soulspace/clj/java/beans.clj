;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.java.beans
  (:use [org.soulspace.clj string] 
        [org.soulspace.clj.java reflection type-conversion]))

;
; Method-based reflective access to Java bean style objects.
; 
; TODO memoize with core.memoize for performance
;

(defn getter? [method]
  (or
    (starts-with "get" (.getName method))
    (starts-with "is" (.getName method))))

(defn setter? [method]
  (starts-with "set" (.getName method)))

(defn getter-method 
  "Returns the getter method for this property. Works for derived properties too."
  [cl property]
  (let [pname (first-upper-case property)]
    (first (filter #(and
                      (or (= (str "get" pname) (.getName %))
                          (= (str "is" pname) (.getName %)))
                      (nil? (.getParameterTypes %)))
                   (method-seq cl)))))

; TODO add value as parameter and return the setter based on the type of the value
(defn setter-method
  "Returns the setter method for this property. Works for derived properties too."
  [cl property]
  (let [pname (str "set" (first-upper-case property))]
    (first (filter #(= pname (.getName %)) (method-seq cl)))))

; TODO add value as parameter and return the adder based on the type of the value
(defn adder-method
  "Returns the add method for this multivalued property."
  [cl property]
  (let [pname (str "add" (first-upper-case property))]
    (first (filter #(= pname (.getName %)) (method-seq cl)))))

; TODO add value as parameter and return the remover based on the type of the value
(defn remover-method
  "Returns the remove method for this multivalued property."
  [cl property]
  (let [pname (str "remove" (first-upper-case property))]
    (first (filter #(= pname (.getName %)) (method-seq cl)))))

(defn has-get-method? [cl property]
  (getter-method cl property))

(defn has-set-method? [cl property]
  (setter-method cl property))

(defn has-add-method? [cl property]
  (adder-method cl property))

(defn has-remove-method? [cl property]
  (remover-method cl property))

(defn get-property
  "Returns the value of this property."
  [obj property]
  (if-let [property-get (getter-method (class obj) property)]
    (.invoke property-get obj nil)
    (throw (IllegalArgumentException. (str "No such property " property ".")))))

(defn set-property!
  "Sets the value of this property."
  [obj property value]
  (if (has-set-method? (class obj) property)
    (let [property-set (setter-method (class obj) property)
          param-type (first (.getParameterTypes property-set))]
      (.invoke property-set obj (into-array [(coerce param-type value)])))
    (throw (IllegalArgumentException. (str "No such property " property ".")))))
  
(defn add-property!
  "Adds the value to the property collection."
  [obj property value]
  (if (has-add-method? (class obj) property)
    (let [property-add (adder-method (class obj) property)
          param-type (first (.getParameterTypes property-add))]
      (.invoke property-add obj (into-array [(coerce param-type value)])))
    (throw (IllegalArgumentException. (str "No such property " property ".")))))

(defn remove-property!
  "Removes the value to the property collection."
  [obj property value]
  (if-let [property-remove (remover-method (class obj) property)]
    (.invoke property-remove obj (into-array [value]))
    (throw (IllegalArgumentException. (str "No such property " property ".")))))

(defn set-properties!
  "Sets the properties given in the map to the instance"
  [obj prop-map]
  (doseq [[k v] prop-map] (set-property! obj (name k) v))
  obj) 

(defn add-properties!
  "Adds the properties given in the map to the instance"
  [obj prop-map]
  (doseq [[k v] prop-map] 
    (if (coll? v)
      (doseq [value v]
        (add-property! obj (name k) value))
      (add-property! obj (name k) v))))

(defn remove-properties!
  "Removes the properties given in the map to the instance"
  [obj prop-map]
  (doseq [[k v] prop-map]
    (if (coll? v)
      (doseq [value v]
        (remove-property! obj (name k) value))
      (remove-property! obj (name k) v))))

(defn init-properties!
  ([obj set-props]
    (set-properties! obj set-props))
  ([obj set-props add-props]
    (set-properties! obj set-props)
    (add-properties! obj add-props)
    obj))

; TODO works for symbols and strings but not for class names yet
(defn create
  "Creates an instance of the given class"
  [cl & args]
  (clojure.lang.Reflector/invokeConstructor
    (Class/forName (name cl)) (into-array Object args)))

(defn create-bean
  "Creates an instance of the given bean and initializes it with the given data."
  ([bean-class set-props]
    (init-properties! (create bean-class) set-props))
  ([bean-class set-props add-props]
    (init-properties! (create bean-class) set-props add-props)))
