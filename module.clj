[
 :module "CljSwingLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.2.0"
 :description "A library for building Java Swing user interfaces in Clojure"
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins ["global" "sdeps" "depsdot" "clojure" "package"]
 :dependencies [[["org.clojure" "clojure" "1.5.1"]]
                [["swing-utils" "swing-utils" "0.2.0"]]
                [["org.soulspace.clj" "CljJavaLibrary" "0.2.0"]]
                [["com.miglayout" "miglayout" "3.7.4"]]]
 ]