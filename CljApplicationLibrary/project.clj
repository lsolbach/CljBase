(defproject org.soulspace.clj/CljApplicationLibrary "0.6.0"
  :description "The CljApplicationLibrary contains abstractions and functions for building applications in clojure"
  :url "https://github.com/lsolbach/CljBase"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                [org.soulspace.clj/CljLibrary "0.7.0"]
                [org.tcrawley/dynapath "0.2.3"] ; dynamic classpath support
                ]
  :test-paths ["unittest"])
