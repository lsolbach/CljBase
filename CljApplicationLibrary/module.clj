[
 :module "CljApplicationLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.6.0"
 :description "The CljApplicationLibrary contains abstractions and functions for building applications in Clojure"
 :scm-url "https://github.com/lsolbach/CljBase"
 :plugins [["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.8.0"]
                ["org.soulspace.clj/CljLibrary, 0.7.0"]
                ["org.tcrawley/dynapath, 0.2.3"]] ; dynamic classpath support
 ]
