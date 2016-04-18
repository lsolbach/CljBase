(defproject org.soulspace.clj/CljSwingLibrary "0.5.0"
  :description "A library for building Java Swing user interfaces in Clojure"
  :url "https://github.com/lsolbach/CljBase"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
 :dependencies [[org.clojure/clojure "1.8.0"]
                [swing-utils/swing-utils "0.2.0" :exclusions [org.clojure/clojure]]
                [org.soulspace.clj/CljJavaLibrary "0.7.0"]
                [com.miglayout/miglayout "3.7.4"]]
  :test-paths ["unittest"])
