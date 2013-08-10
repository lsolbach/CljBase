[
 :module "CljApplicationLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.5.1"
 :description "The CljApplicationLibrary contains abstractions and functions for building applications in clojure."
 :plugins ["global" "dependencies" "clojure" "package"] ;  "clojuretest"
 :dependencies [["org.clojure/clojure, 1.5.1"]
                ["org.soulspace.clj/CljLibrary, 0.5.0"]
                ["org.tcrawley/dynapath, 0.2.3"]] ; dynamic classpath support
 ]
