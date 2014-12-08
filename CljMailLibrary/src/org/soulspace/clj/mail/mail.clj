(ns org.soulspace.clj.mail.mail
  (:import [java.util Properties]
           [javax.mail MessageRemovedException Session Transport]
           [javax.mail.internet MimeMessage MimeUtility])
  (:use [org.soulspace.clj.java beans]))

(def ^:dynamic *mail-session*)

(defn properties
  "Creates properties from a map."
  ([m]
    (let [p (Properties.)]
      (.putAll p m)
      p)))

(defn session
  "Gets an instance of the mail session."
  ([props]
    (Session/getInstance props)))

(defn mime-message
  "Creates a mime message."
  ([props]
    (mime-message *mail-session* props))
  ([session props]
    (let [msg (MimeMessage. session)]
      (set-properties! msg props)
      msg)))

(defn send-mail
  "Sends a mail message."
  ([message]
    (Transport/send message))
  ([message user pw]
    (Transport/send message user pw)))

(defmacro with-mail-session
  "Executes the body in the context of a mail session."
  [props & body]
  `(binding [*mail-session* (session ~props)]
     ~@body
     ))
