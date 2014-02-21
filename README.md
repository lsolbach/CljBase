CljLibrary
==========
The CljLibrary is a library for clojure providing convenience functions with no other dependencies than java and clojure.

It contains
* string functions as supplement to clojure.string
* file functions as supplement to clojure.java.io
* file search functions
* namespace handling functions
* network related functions
* system functions based on java.lang.System

Requires JavaSE 7.

Author/Project Lead
-------------------
Ludger Solbach

Copyright
---------
© 2012-2013 Ludger Solbach

License
-------
[Eclipse Public License 1.0] (http://www.eclipse.org/legal/epl-v10.html "EPL 1.0")

Code Repository
---------------
[https://github.com/lsolbach/CljLibrary] (https://github.com/lsolbach/CljLibrary)

History
-------

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
