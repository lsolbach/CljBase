[
 :module "CljJavaLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.6.1"
 :description "The CljJavaLibrary contains various functions for Clojure/Java integration."
 :project-lead "Ludger Solbach"
 :provider "soulspace.org"
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins ["global"
           ["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]
           ]
 :dependencies [["org.clojure/clojure, 1.7.0"]
                ["org.soulspace.clj/CljLibrary, 0.6.1"]]
 ]
