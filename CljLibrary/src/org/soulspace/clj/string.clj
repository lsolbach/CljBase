;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.string
  (:require [clojure.string :refer [upper-case lower-case]]))

(defn gt
  "Greater than string comparison."
  [s1 s2]
  (> (.compareTo s1 s2) 0))

(defn ge
  "Greater or equal string comparison."
  [s1 s2]
  (>= (.compareTo s1 s2) 0))

(defn lt
  "Less than string comparison."
  [s1 s2]
  (< (.compareTo s1 s2) 0))

(defn le
  "Less or equal string comparison."
  [s1 s2]
  (<= (.compareTo s1 s2) 0))

(defn eq
  "Equal string comparison."
  [s1 s2]
  (= s1 s2))

(defn ne
  "Not equal string comparison."
  [s1 s2]
  (not= s1 s2))

(defn substring
  "Returns a substring of string defined by the indices."
  ([begin-idx s]
   (.substring s begin-idx))
  ([begin-idx end-idx s]
   (.substring s begin-idx end-idx)))

(defn upper-case?
  "Returns true if the char is upper case."
  [c]
  (Character/isUpperCase c))

(defn lower-case?
  "Returns true if the char is lower case."
  [c]
  (Character/isLowerCase c))

(defn first-upper-case
  "Returns the string with the first letter converted to upper case."
  [s]
  (str (upper-case (substring 0 1 s)) (substring 1 s)))

(defn first-lower-case
  "Returns the string with the first letter converted to lower case."
  [s]
  (str (lower-case (substring 0 1 s)) (substring 1 s)))

(defn to-camel-case
  "Converts a string s into camel case. Removes occurences of 'c' and converts
  the next character to upper case."
  [c s]
  (loop [chars (seq s) cc-chars []]
    (if (seq chars)
      (if (= (first chars) c)
        (recur (rest (rest chars)) (conj cc-chars (upper-case (second chars))))
        (recur (rest chars) (conj cc-chars (str (first chars)))))
      (apply str cc-chars))))

(defn from-camel-case
  "Converts a string s from camel case to a lower case string with the spacer character
  'c' inserted in front of any intra word uppercase char."
  [c s]
  (loop [chars (seq s) r-chars [] begin true]
    (if (seq chars)
      (if (and (upper-case? (first chars)) (not begin))
        (recur (rest chars) (conj r-chars c (first chars)) false)
        (recur (rest chars) (conj r-chars (first chars)) false))
      (apply str r-chars))))

(defn hyphen-to-camel-case
  "Converts hyphenized strings to camel case strings."
  [s]
  (to-camel-case \- s))

(defn camel-case-to-hyphen
  "Converts camel case strings to hyphenized strings."
  [s]
  (from-camel-case \- s))

(defn underscore-to-camel-case
  "Converts underscored strings to camel case strings."
  [s]
  (to-camel-case \_ s))

(defn camel-case-to-underscore
  "Converts camel case strings to underscored strings."
  [s]
  (from-camel-case \_ s))

(defn parse-number
  "Reads a number from a string. Returns nil if not a number."
  [s]
  (if (re-find #"^-?\d+\.?\d*([Ee]\+\d+|[Ee]-\d+|[Ee]\d+)?$" (.trim s))
    (read-string s)))
