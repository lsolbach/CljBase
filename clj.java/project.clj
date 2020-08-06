(defproject org.soulspace.clj/clj.java "0.8.1-SNAPSHOT"
  :description "The clj.java library contains various functions for Clojure/Java integration"
  :url "https://github.com/lsolbach/CljBase"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  ; use deps.edn dependencies
  :plugins [[lein-tools-deps "0.4.5"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:install :user :project]}

  :test-paths ["test"]
  :deploy-repositories [["clojars"  {:sign-releases false :url "https://clojars.org/repo"}]])
