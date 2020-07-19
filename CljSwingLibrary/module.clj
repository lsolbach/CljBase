[
 :module "CljSwingLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.6.0"
 :description "CljSwingLibrary is a library for building Java Swing user interfaces in Clojure"
 :scm-url "https://github.com/lsolbach/CljBase"
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins [["org.soulspace.baumeister/ClojurePlugin"]
           ;["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.10.1"]
                ["swing-utils/swing-utils, 0.2.0"]
                ["org.soulspace.clj/CljJavaLibrary, 0.8.0"]
                ["com.miglayout/miglayout, 3.7.4"]]]
