(ns org.soulspace.clj.file-search
  (:require [clojure.string :as str])
  (:use [clojure.java.io :exclude [delete-file]]
        [org.soulspace.clj.file]))

(defn split-path
  ([sep paths]
    "Split a path string with the given separator."
    (str/split paths (re-pattern sep)))
  ([paths]
    "Split a path string using ':' as separator."
    (split-path ":" paths)))

(defn build-path
  ([sep files]
    "Build a path by joining the given files with the separator."
    (str/join sep (map path files)))
  ([files]
    "Build a path by joining the given files with ':' as separator."
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
  ([dir filename]
    "Returns the absolute path of the file defined by the given directory and filename."
    (str (absolute-path dir) "/" filename))
  ([dir filename extension]
    "Returns the absolute path of the file defined by the given directory, filename and extension."
    (str (absolute-path dir) "/" filename "." extension)))

(defn existing-files
  ([dirs]
    "Returns all existing files in the given directories"
    (filter exists? (flatten (map all-files dirs))))
  ([ext dirs]
    "Returns all existing files with the specified extension in the given directories"
    (filter exists? (flatten (map #(all-files-by-extension ext %) dirs)))))

(defn existing-files-by-pattern [pattern dirs]
  "Returns all existing files matching the specified pattern in the given directories"
  (filter exists? (flatten (map #(all-files-by-pattern pattern %) dirs))))

(defn existing-files-on-path
  ([dir-path]
    "Returns all existing files on the given path"
    (existing-files (map as-file (split-path dir-path))))
  ([ext dir-path]
    "Returns all existing files with the specified extension on the given path"
    (existing-files ext (map as-file (split-paths dir-path)))))

(defn directory-searcher
  ([filename]
    (fn [dir]
      (let [abs-name (build-absolute-path dir filename)]
        (as-file abs-name))))
  ([filename extension]
    (fn [dir]
      (let [abs-name (build-absolute-path dir filename extension)]
        (as-file abs-name)))))

(defn locate-file
  ([searchpath filename]
    "Returns the first existing file on the search path for the specified filename"
    (first (filter exists? (map (directory-searcher filename) searchpath))))
  ([searchpath filename extension]
    "Returns the first existing file on the search path for the specified filename and extension"
    (first (filter exists? (map (directory-searcher filename extension) searchpath)))))

(defn file-locator
  ([searchpath]
;    (println "file-locator" searchpath)
    (fn [filename]
      (locate-file searchpath filename)))
  ([searchpath ext]
;    (println "file-locator" searchpath ext)
    (fn [filename]
      (locate-file searchpath filename ext))))
