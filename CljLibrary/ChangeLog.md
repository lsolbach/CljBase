ChangeLog
=========

Version 0.8.0 (--.--.2020)
-------------------------- 
* renamed to clj-base to make the library compatible with clojars
* updated project files accordingly
* made the code compliant to current Clojure coding practice (e.g. require over use)
* updated comments and documentation in the code
* moved some Java specific namespaces to clj-java (codec, message-digest)
* moved some CLojure only code from clj-app to clj-base
* updated Clojure version to 1.10.1

Version 0.6.0 (--.--.2014)
--------------------------
* added system properties support
* added JVM proxy settings
* added path normalization for file paths (java.io)
* added codec for base64 and hex coding of byte arrays
* added message digests
* now requires JavaSE 7

Version 0.5.0 (05.08.2013)
--------------------------
* added camel case string functions
* added copyright header in source files
* moved namespace related functions to 'namespace' namespace
* replaced str-length with count
* updated module files

Version 0.4.0 (03.08.2013)
--------------------------
* added absolute-file and canonical-file
* fixed file search
* code cleanups
* updated module files

Version 0.3.0 (03.08.2013)
--------------------------
* added convenience in file functions
* added unit tests for string functions
* added doc strings
* added license.txt
* refactored file functions
* updated module files
* initial github import
* initial git import
