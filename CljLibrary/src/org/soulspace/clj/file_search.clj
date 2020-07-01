;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.file-search
  (:require [clojure.string :as str])
  (:use [clojure.java.io :exclude [delete-file]]
        [org.soulspace.clj.file]))

(defn split-path
  "Split a path string with the given separator or ':' as default."
  ([sep paths]
   (str/split paths (re-pattern sep)))
  ([paths]
   (split-path ":" paths)))

(defn build-path
  "Build a path by joining the given files with the separator or ':' as default."
  ([sep files]
   (str/join sep (map path files)))
  ([files]
   (build-path ":" files)))

; convert ** -> ('filename pattern'|/)+  convert * -> ('filename pattern')+
(defn path-pattern [ant-pattern]
  "Convert ant style path patterns to regex path patterns."
  (let [file-regex "\\w|\\d|–"]
    (str/replace (str/replace ant-pattern "**" (str "(?:" file-regex "|/)+")) "*" (str "(?:" file-regex ")+"))))

(defn build-searchpath [pathnames]
  "Creates a sequence containing the directories to search."
  (if (coll? pathnames)
    (map as-file pathnames)
    (map as-file (split-path pathnames))))

(defn build-absolute-path
  "Returns the absolute path of the file defined by the given directory, filename (and extension)."
  ([dir filename]
   (str (absolute-path dir) "/" filename))
  ([dir filename extension]
   (str (absolute-path dir) "/" filename "." extension)))

(defn existing-files
  "Returns all existing files (with the specified extension) in the given directories."
  ([dirs]
   (filter exists? (flatten (map all-files dirs))))
  ([ext dirs]
   (filter exists? (flatten (map #(all-files-by-extension ext %) dirs)))))

(defn existing-files-by-pattern [pattern dirs]
  "Returns all existing files matching the specified pattern in the given directories."
  (filter exists? (flatten (map #(all-files-by-pattern pattern %) dirs))))

(defn existing-files-on-path
  "Returns all existing files (with the specified extension) on the given path."
  ([dir-path]
   (existing-files (map as-file (split-path dir-path))))
  ([ext dir-path]
   (existing-files ext (map as-file (split-path dir-path)))))

(defn directory-searcher
  "Returns a function that takes a directory and returns the file specified by filename (and extension)."
  ([filename]
   (fn [dir]
     (let [abs-name (build-absolute-path dir filename)]
       (as-file abs-name))))
  ([filename extension]
   (fn [dir]
     (let [abs-name (build-absolute-path dir filename extension)]
       (as-file abs-name)))))

(defn locate-file
  "Returns the first existing file on the search path for the specified filename (and extension)."
  ([searchpath filename]
   (first (filter exists? (map (directory-searcher filename) searchpath))))
  ([searchpath filename extension]
   (first (filter exists? (map (directory-searcher filename extension) searchpath)))))

(defn file-locator
  "Returns a function that locates a file by name on the search path."
  ([searchpath]
   (fn [filename]
     (locate-file searchpath filename)))
  ([searchpath ext]
   (fn [filename]
     (locate-file searchpath filename ext))))
