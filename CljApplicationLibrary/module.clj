[
 :module "clj.app"
 :project "org.soulspace.clj"
 :type :library
 :version "0.7.0"
 :description "The clj.app library contains abstractions and functions for building applications in Clojure"
 :scm-url "https://github.com/lsolbach/CljBase"
 :plugins [["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.10.1"]
                ["org.tcrawley/dynapath, 0.2.3"]]] ; dynamic classpath support
