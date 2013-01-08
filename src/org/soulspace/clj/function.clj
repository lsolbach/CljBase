(ns org.soulspace.clj.function
  (:require [clojure.string :as str]))

(defn not-nil? [x]
  (not (nil? x)))

(defn get-env
  ([var]
    (System/getenv (name var)))
  ([var default]
    (let [env (get-env var)]
      (if-not (nil? env)
        env
        default))))

(defn ns-to-path [ns]
  (str/replace ns \. \/))

(defn path-to-ns [path]
  (str/replace path \/ \.))

(defn ns-to-filename [ns]
 (str/replace ns \- \_))

(defn filename-to-ns [file]
 (str/replace file \_ \-))

(defn ns-to-file [ns]
 (str/replace (str/replace ns \- \_) \. \/))

(defn file-to-ns [file]
 (str/replace (str/replace file \_ \-) \/ \.))

; TODO check for function with 'fn?'
(defn call-by-name
  ([^String name]
    (when-let [func (ns-resolve (symbol name))]
      (func)))
  ([^String name & args]
    (when-let [func (ns-resolve (symbol name))]
      (apply func args))))

; TODO check for function with 'fn?'
(defn call-by-ns-name
  ([^String ns ^String name]
    (when-let [func (ns-resolve (symbol ns) (symbol name))]
      (func)))
  ([^String ns ^String name & args]
    (when-let [func (ns-resolve (symbol ns) (symbol name))]
      (apply func args))))

;(defn apply-for [f v]
;  (if (coll? v)
;    (doseq [value v]
;      (f value))
;    (f v)))
