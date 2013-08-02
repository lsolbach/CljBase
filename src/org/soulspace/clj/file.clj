(ns org.soulspace.clj.file
  (:use [clojure.core :exclude [name]]
        [clojure.java.io :exclude [delete-file]]
        [org.soulspace.clj string function])
  (:import [java.io File]))

(defn exists? [file]
  (let [file (as-file file)]
    (and (not-nil? file) (.exists file))))

(defn is-dir? [file]
  (let [file (as-file file)]
    (and (exists? file) (.isDirectory file))))

(defn file-name [file]
  "Returns the name of the file."
  (.getName file))

(defn parent-path [file]
  "Returns the parent path for the file if it exists."
  (.getParent file))

(defn parent-dir [file]
  "Returns the parent dir of the file if it exists."
  (.getParentFile file))

(defn path [file]
  "Returns the path of the file."
  (.getPath file))

(defn absolute-path [file]
  "Returns the absolute path of the file."
  (.getAbsolutePath file))

(defn canonical-path [file]
  "Returns the canonical path of the file."
  (.getCanonicalPath file))

(defn relative-path [base-path file]
  "Returns the path of the file relative to the base-path."
  (let [cpath (canonical-path file)
        cbase-path (canonical-path (as-file base-path))]
    (if (starts-with cbase-path cpath)
      (if (ends-with "/" cbase-path)
        (substring (str-length cbase-path) cpath)
        (substring (+ (str-length cbase-path) 1) cpath))
      (path file)))) ; TODO think about: walk up to a common parent and down again (../../src/bla)?

(defn list-files [file]
  (seq (.listFiles file)))

(defn list-paths [file]
  (seq (.list file)))

(defn has-extension? [ext file]
  (and (exists? file) (ends-with (str "." ext) (path file))))

(defn matches? [pattern file]
  (and (exists? file) (re-matches pattern (path file))))

(defn create-dir [file]
  "create directory including missing parent directories."
  (if-not (exists? file)
    (.mkdirs file)))

; TODO implement generic directory walker (see/use walk function)
; (defn dir-walker [fn dir] ...)?!

(defn files [file]
  (if (exists? file)
    (if (is-dir? file)
      (let [files (list-files file)]
        files)
      file)))

(defn all-files [file]
  (if (exists? file)
    (if (is-dir? file)
      (let [files (conj [] file)]
        (concat files (flatten (map all-files (list-files file)))))
      file)))

(defn all-files-by-extension [ext file]
  (filter (partial has-extension? ext) (all-files file)))

(defn all-files-by-pattern [pattern file]
  (filter (partial matches? pattern) (all-files file)))

(defn delete-file [file]
  (when (exists? file)
    (.delete file)))

(defn delete-dir [file]
  "delete directory recursively"
  (doseq [f (reverse (all-files file))]
    (delete-file f)))
