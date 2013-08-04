(ns org.soulspace.clj.file
  (:use [clojure.core :exclude [name]]
        [clojure.java.io :exclude [delete-file]]
        [org.soulspace.clj string function])
  (:import [java.io File]))

(defn- list-files [file]
  (let [file (as-file file)]
    (seq (.listFiles file))))

(defn- list-paths [file]
  (let [file (as-file file)]
    (seq (.list file))))

(defn exists?
  "Returns true, if the given file exists."
  [file]
  (let [file (as-file file)]
    (and (not-nil? file) (.exists file))))

(defn is-dir?
  "Returns true, if the given file exists and is a directory."
  [file]
  (let [file (as-file file)]
    (and (exists? file) (.isDirectory file))))

(defn file-name
  "Returns the name of the file."
  [file]
  (let [file (as-file file)]
    (.getName file)))

(defn parent-path 
  "Returns the parent path for the file if it exists."
  [file]
  (let [file (as-file file)]
    (.getParent file)))

(defn parent-dir 
  "Returns the parent dir of the file if it exists."
  [file]
  (let [file (as-file file)]
    (.getParentFile file)))

(defn path
  "Returns the path of the file."
  [file]
  (let [file (as-file file)]
    (.getPath file)))

(defn absolute-path
  "Returns the absolute path of the file."
  [file]
  (let [file (as-file file)]
    (.getAbsolutePath file)))

(defn absolute-file
  "Returns the file as absolute file."
  [file]
  (let [file (as-file file)]
    (.getAbsoluteFile file)))

(defn canonical-path
  "Returns the canonical path of the file."
  [file]
  (let [file (as-file file)]
    (.getCanonicalPath file)))

(defn canonical-file
  "Returns the canonical file of the file."
  [file]
  (let [file (as-file file)]
    (.getCanonicalFile file)))

(defn relative-path [base-path file]
  "Returns the path of the file relative to the base-path."
  (let [cpath (canonical-path file)
        cbase-path (canonical-path (as-file base-path))]
    (if (starts-with cbase-path cpath)
      (if (ends-with "/" cbase-path)
        (substring (str-length cbase-path) cpath)
        (substring (+ (str-length cbase-path) 1) cpath))
      (path file)))) ; TODO think about: walk up to a common parent and down again (../../src/bla)?

(defn has-extension?
  "Returns true if the path of the file ends with the given extension."
  [ext file]
  (and (exists? file) (ends-with (str "." ext) (path file))))

(defn matches?
  "Returns true if the path of the file matches the given pattern."
  [pattern file]
  (and (exists? file) (re-matches pattern (path file))))

(defn create-dir
  "Creates a directory including missing parent directories."
  [file]
  (if-not (exists? file)
    (.mkdirs file)))

; TODO implement generic directory walker (see/use walk function)
; (defn dir-walker [fn dir] ...)?!

(defn files
  "Returns a sequence of the files in a directory given as file.
If the given file is not a directory, it is returned as only file in the sequence."
  [file]
  (if (exists? file)
    (if (is-dir? file)
      (let [files (list-files file)]
        files)
      [file])))

(defn all-files [file]
  "Returns a sequence of the files in a directory given as file and its sub directories.
If the given file is not a directory, it is returned as only file in the sequence."
  (if (exists? file)
    (if (is-dir? file)
      (let [files (conj [] file)]
        (concat files (flatten (map all-files (list-files file)))))
      [file])))

(defn all-files-by-extension [ext file]
  (filter (partial has-extension? ext) (all-files file)))

(defn all-files-by-pattern [pattern file]
  (filter (partial matches? pattern) (all-files file)))

(defn delete-file [file]
  (let [file (as-file file)]
    (when (exists? file)
      (.delete file))))

(defn delete-dir [file]
  "delete directory recursively"
  (let [file (as-file file)]
    (doseq [f (reverse (all-files file))]
      (delete-file f))))
