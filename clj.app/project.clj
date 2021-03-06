(defproject org.soulspace.clj/clj.app "0.7.0"
  :description "The clj.app library  contains abstractions and functions for building applications in Clojure"
  :url "https://github.com/lsolbach/CljBase"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  ; use deps.edn dependencies
  :plugins [[lein-tools-deps "0.4.5"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:install :user :project]}

;  :dependencies [[org.clojure/clojure "1.10.1"]
;                 [org.tcrawley/dynapath "0.2.3"]] ; dynamic classpath support
  :test-paths ["test"])
