[
 :module "CljApplicationLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.5.2"
 :description "The CljApplicationLibrary contains abstractions and functions for building applications in clojure."
 :plugins ["global"
           ["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.7.0"]
                ["org.soulspace.clj/CljLibrary, 0.6.1"]
                ["org.tcrawley/dynapath, 0.2.3"]] ; dynamic classpath support
 ]
