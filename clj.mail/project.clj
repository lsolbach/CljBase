(defproject org.soulspace.clj/clj.mail "0.3.0"
  :description "The clj.mail library is a clojure wrapper library for the JavaMail API"
  :url "https://github.com/lsolbach/CljBase"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  ; use deps.edn dependencies
  :plugins [[lein-tools-deps "0.4.5"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:install :user :project]}

;  :dependencies [[org.clojure/clojure "1.10.1"]
;                 [javax.mail/javax.mail-api "1.6.2"]
;                 [org.soulspace.clj/clj.java "0.8.0"]]

  :test-paths ["test"])
