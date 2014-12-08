[
 :module "CljSwingLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.4.0"
 :description "A library for building Java Swing user interfaces in Clojure"
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins ["global"
           ["org.soulspace.baumeister/DependencyPlugin"]
           ["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.5.1"]
                ["swing-utils/swing-utils, 0.2.0"]
                ["org.soulspace.clj/CljJavaLibrary, 0.5.0"]
                ["com.miglayout/miglayout, 3.7.4"]]
 ]