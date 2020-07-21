;;
;;   Copyright (c) Ludger Solbach. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;   which can be found in the file license.txt at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.
;;

(ns org.soulspace.clj.mail.mail
  (:require [org.soulspace.clj.java.beans :as b])
  (:import [javax.mail MessageRemovedException Session Transport]
           [javax.mail.internet MimeMessage MimeUtility]))

;;
;; Functions to send mails with the JavaX Mail API
;;

(def ^:dynamic *mail-session*)

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
     (b/set-properties! msg props)
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
     ~@body))
