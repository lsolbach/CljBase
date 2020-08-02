;;
;;   Copyright (c) Ludger Solbach. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;   which can be found in the file license.txt at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.
;;

(ns org.soulspace.clj.function)

(defn not-nil?
  "Tests if 'x' is not nil.

  Same as (not (nil? x)) or ((complement nil?) x).
  If 'x' is sequable, use (seq x) instead."
  [x]
  (not (nil? x)))

(defn ^:static atom?
  "Returns true, if 'x' is an Atom."
  [x]
  (instance? clojure.lang.Atom x))

(defn ^:static ref?
  "Returns true, if 'x' is a Ref."
  [x]
  (instance? clojure.lang.Ref x))

(defn ^:static agent?
  "Returns true, if 'x' is an Agent."
  [x]
  (instance? clojure.lang.Agent x))

(defn ^:static reftype?
  "Returns true, if 'x' is an Atom, a Ref or an Agent."
  [x]
  (or (atom? x) (ref? x) (agent? x)))
