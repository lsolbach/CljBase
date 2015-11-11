[
 :module "CljMailLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.1.1"
 :description "The CljMailLibrary is a clojure wrapper library for the JavaMail api."
 :project-lead "Ludger Solbach"
 :provider "soulspace.org"
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins ["global"
           ["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.7.0"]
                ; ["javax.mail/javax.mail-api, 1.5.1"]
                ["com.sun.mail/javax.mail, 1.5.1"]
                ["org.soulspace.clj/CljJavaLibrary, 0.6.1"]]
 ]