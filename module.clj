[
 :module "CljJavaLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.4.0"
 :description "The CljJavaLibrary contains various functions for Clojure/Java integration."
 :project-lead "Ludger Solbach"
 :provider "soulspace.org"
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins ["global" "dependencies" "clojure" "clojuretest" "package"]
 :dependencies [[["org.clojure" "clojure" "1.5.1"]]
                [["org.soulspace.clj" "CljLibrary" "0.5.0"]]]
 ]
