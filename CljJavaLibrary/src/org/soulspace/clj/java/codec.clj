;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.java.codec
  (:import [javax.xml.bind DatatypeConverter]))

;;
;; Functions to encode/decode to Hex and Base64
;;

(defn encode-hex
  "Encode the bytes as hexadecimal string."
  [bytes]
  (DatatypeConverter/printHexBinary bytes))

(defn decode-hex
  "Parse a hexadecimal string into bytes."
  [s]
  (DatatypeConverter/parseHexBinary s))

(defn encode-base64
  "Encode the bytes as Base64 string."
  [bytes]
  (DatatypeConverter/printBase64Binary bytes))

(defn decode-base64
  "Parse a Base64 string into bytes."
  [s]
  (DatatypeConverter/parseBase64Binary s))
