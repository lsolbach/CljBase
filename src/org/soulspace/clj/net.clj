(ns org.soulspace.clj.net
  (:use [clojure.java.io :only [as-url]]))

(defn test-url
  "Test an URL, returns true if content is available"
  ([url]
    (test-url url 500)
    )
  ([url timeout]
    (try
      (let [conn (.openConnection url)]
        (.setConnectTimeout conn 500)
        (.connect conn)
        (contains? #{200} (.getResponseCode conn)))
      (catch Exception _ false))))
