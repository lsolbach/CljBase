[
 :module "clj.java"
 :project "org.soulspace.clj"
 :type :library
 :version "0.8.0"
 :description "The clj.java library contains various functions for Clojure/Java integration."
 :project-lead "Ludger Solbach"
 :provider "soulspace.org"
 :scm-url "https://github.com/lsolbach/CljBase"
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins [["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]

 :dependencies [["org.clojure/clojure, 1.10.1"]
                ["org.soulspace.clj/clj.base, 0.8.0"]]]
