(ns org.soulspace.clj.string
  (:use [clojure.string :only [upper-case lower-case]]))

(defn gt [v1 v2]
  (> (.compareTo v1 v2) 0))

(defn ge [v1 v2]
  (>= (.compareTo v1 v2) 0))

(defn lt [v1 v2]
  (< (.compareTo v1 v2) 0))

(defn le [v1 v2]
  (<= (.compareTo v1 v2) 0))

(defn eq [v1 v2]
  (= v1 v2))

(defn ne [v1 v2]
  (not= v1 v2))

(defn starts-with [pattern string]
  (.startsWith string pattern))

(defn ends-with [pattern string]
  (.endsWith string pattern))

(defn str-length [string]
  (.length string))

; FIXME doesn't work yet
(defn str-contains? [pattern string]
  (.contains string pattern))

(defn index-of
  ([chr string]
    (.indexOf string chr))
  ([chr from-idx string]
    (.indexOf string chr from-idx)))

(defn last-index-of 
  ([chr string]
    (.lastIndexOf string chr))
  ([chr from-idx string]
    (.lastIndexOf string chr from-idx)))

(defn substring 
  ([begin-idx string]
    (.substring string begin-idx))
  ([begin-idx end-idx string]
    (.substring string begin-idx end-idx)))

(defn first-upper-case [string]
  (str (upper-case (substring 0 1 string)) (substring 1 string)))

(defn first-lower-case [string]
  (str (lower-case (substring 0 1 string)) (substring 1 string)))

; FIXME implement
(defn to-camel-case [chr string]
  )

; FIXME implement
(defn from-camel-case [chr string]
  )

(defn hyphen-to-camel-case [string]
  (to-camel-case \- string))

(defn camel-case-to-hyphen [string]
  (from-camel-case \- string))

(defn underscore-to-camel-case [string]
  (to-camel-case \_ string))

(defn camel-case-to-underscore [chr string]
  (from-camel-case \_ string))
