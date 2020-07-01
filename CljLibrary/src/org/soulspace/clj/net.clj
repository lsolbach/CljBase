;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.net
  (:use [clojure.java.io]
        [clojure.string :only [join]]
        [org.soulspace.clj system]))

(defn url-content
  "Read the content from an URL."
  ([url]
   (with-open [rdr (reader url)]
     (slurp rdr)))
  ([url timeout]
     ; TODO implement reasonably
   (url-content url)))

(defn test-url
  "Test an URL, returns true if content is available."
  ([url]
   (test-url url 500))
  ([url timeout]
   (try
     (let [conn (.openConnection url)]
       (.setConnectTimeout conn 500)
       (.connect conn)
       (contains? #{200} (.getResponseCode conn)))
     (catch Exception _ false))))

(defn use-system-proxies
  "Tell the JVM that the system proxies should be used."
  []
  (set-system-property "java.net.useSystemProxies" "true"))

(defn set-http-proxy
  "Set an HTTP proxy for the JVM."
  ([host port]
   (set-system-property "http.proxyHost" host)
   (set-system-property "http.proxyPort" port))
  ([host port bypassed-hosts]
   (set-http-proxy host port)
   (set-system-property "http.nonProxyHosts" (join "|" bypassed-hosts))))

(defn set-https-proxy
  "Set an HTTPS proxy for the JVM."
  [host port]
  (set-system-property "https.proxyHost" host)
  (set-system-property "https.proxyPort" port))

(defn set-ftp-proxy
  "Set a FTP proxy for the JVM."
  ([host port]
   (set-system-property "ftp.proxyHost" host)
   (set-system-property "ftp.proxyPort" port))
  ([host port bypassed-hosts]
   (set-https-proxy host port)
   (set-system-property "ftp.nonProxyHosts" (join "|" bypassed-hosts))))

(defn set-socks-proxy
  "Set an SOCKS proxy for the JVM."
  [host port]
  (set-system-property "socksProxyHost" host)
  (set-system-property "socksProxyPort" port))
