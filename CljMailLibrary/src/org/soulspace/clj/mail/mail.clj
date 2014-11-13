(ns org.soulspace.clj.mail.mail
  (:import [java.util Properties]
           [javax.mail MessageRemovedException Session Transport]
           [javax.mail.internet MimeMessage MimeUtility])
  (:use [org.soulspace.clj.java beans]))

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
