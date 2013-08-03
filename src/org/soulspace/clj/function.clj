(ns org.soulspace.clj.function
  (:require [clojure.string :as str]))

(defn not-nil? 
  "Tests if the argument is not nil. Same as (not (nil? x)) or ((complement nil?) x)."
  [x]
  (not (nil? x)))

(defn get-env
  "Returns the environment variable named var."
  ([var]
    (System/getenv (name var)))
  ([var default]
    (let [env (get-env var)]
      (if-not (nil? env)
        env
        default))))

(defn ns-to-path 
  "Converts a namespace into a path."
  [ns]
  (str/replace ns \. \/))

(defn path-to-ns
  "Converts a path into a namespace."
  [path]
  (str/replace path \/ \.))

(defn ns-to-filename 
  "Converts a namespace into a fileneame."
  [ns]
 (str/replace ns \- \_))

(defn filename-to-ns
  "Converts a filename into a namespace."
  [file]
  (str/replace file \_ \-))

(defn ns-to-file
  "Converts a namespace into a fileneame."
  [ns]
  (str/replace (str/replace ns \- \_) \. \/))

(defn file-to-ns
  "Converts a filename into a namespace."
  [file]
 (str/replace (str/replace file \_ \-) \/ \.))

; TODO check for function with 'fn?'
(defn call-by-name
  "Resolves a function by name and calls it."
  ([^String name]
    (when-let [func (ns-resolve (symbol name))]
      (if (fn? func) (func))))
  ([^String name & args]
    (when-let [func (ns-resolve (symbol name))]
      (if (fn? func) (apply func args)))))

(defn call-by-ns-name
  "Resolves a function by name in the given namespace and calls it."
  ([^String ns ^String name]
    (when-let [func (ns-resolve (symbol ns) (symbol name))]
      (if (fn? func) (func))))
  ([^String ns ^String name & args]
    (when-let [func (ns-resolve (symbol ns) (symbol name))]
      (if (fn? func) (apply func args)))))

;(defn apply-for [f v]
;  (if (coll? v)
;    (doseq [value v]
;      (f value))
;    (f v)))
