;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.namespace
  (:require [clojure.string :as str]))

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


