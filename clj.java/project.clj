(defproject org.soulspace.clj/clj.java "0.8.0"
  :description "The clj.java library contains various functions for Clojure/Java integration"
  :url "https://github.com/lsolbach/CljBase"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.soulspace.clj/clj.base "0.8.1"]]
  :test-paths ["test"]
  :deploy-repositories [["clojars"  {:sign-releases false :url "https://clojars.org/repo"}]])
