(ns org.soulspace.clj.java.text
  (:use [org.soulspace.clj.java beans])
  (:import [java.text ChoiceFormat DateFormat DecimalFormat MessageFormat NumberFormat SimpleDateFormat]))

(defn message-format
  "Creates a MessageFormat."
  ([fmt]
    (MessageFormat. fmt))
  ([fmt locale]
    (MessageFormat. fmt locale)))

(defn choice-format
  "Creates a ChoiceFormat."
  ([pattern]
    (ChoiceFormat. pattern))
  ([doubles formats]
    (ChoiceFormat. doubles formats)))

(defn decimal-format
  "Creates a DecimalFormat."
  ([fmt args]
    (let [f (DecimalFormat. fmt)]
      (set-properties! f args)
      f))
  ([fmt locale args]
    (let [f (DecimalFormat. fmt locale)]
      (set-properties! f args)
      f)))

(defn number-format
  "Returns the general NumberFormat instance."
  ([]
    (NumberFormat/getInstance))
  ([locale]
    (NumberFormat/getInstance locale)))

(defn currency-format
  "Returns the currency NumberFormat instance."
  ([]
    (NumberFormat/getCurrencyInstance))
  ([locale]
    (NumberFormat/getCurrencyInstance locale)))

(defn integer-format
  "Returns the integer NumberFormat instance."
  ([]
    (NumberFormat/getIntegerInstance))
  ([locale]
    (NumberFormat/getIntegerInstance locale)))

(defn percent-format
  "Returns the percent NumberFormat instance."
  ([]
    (NumberFormat/getPercentInstance))
  ([locale]
    (NumberFormat/getPercentInstance locale)))

(defn date-format
  "Returns the DateFormat instance."
  ([]
    (DateFormat/getDateInstance))
  ([locale]
    (DateFormat/getDateInstance locale)))

(defn simple-date-format
  "Creates a SimpleDateFormat."
  ([fmt args]
    (let [f (SimpleDateFormat. fmt)]
      (set-properties! f args)
      f))
  ([fmt locale args]
    (let [f (SimpleDateFormat. fmt locale)]
      (set-properties! f args)
      f)))

