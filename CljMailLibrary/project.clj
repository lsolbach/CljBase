(defproject org.soulspace.clj/CljMailLibrary "0.1.1"
  :description "The CljMailLibrary is a clojure wrapper library for the JavaMail api."
  :url "https://github.com/lsolbach/CljBase"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                [javax.mail/javax.mail-api "1.5.1"]
                [com.sun.mail/javax.mail "1.5.1"]
                [org.soulspace.clj/CljJavaLibrary "0.6.1"]]
  :test-paths ["unittest"])

