[
 :module "CljLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.7.0"
 :description "The CljLibrary is a library for clojure providing convenience functions with no other dependencies than java and clojure."
 :project-lead "Ludger Solbach"
 :provider "soulspace.org"
 :scm-url "https://github.com/lsolbach/CljBase"
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins ["global"
           ["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.8.0"]]
 ]
