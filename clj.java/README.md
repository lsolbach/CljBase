clj.java
========
The clj.java library contains various functions for Clojure/Java integration.
clj.java only requires classes from the JDK. It does not have external dependencies.
The functions in this library are expected to provide an idiomatic API for Clojure and some additional value over plain Java interop.

clj-java contains the following namespaces
* beaninfo - functions to set Java bean properties via property descriptors
* beans- functions for method-based reflective access to Java bean style objects
* codec- functions to encode/decode to Hex and Base64
* i18n - functions for internationalization via Java resource bundles
* message-digest - functions to create message digests
* net - functions for network access
* properties - function to create Java Properties from a Clojure map
* reflection - functions for reflective introspection of Java classes
* system - functions for interacting with java.lang.System
* type-conversion - Java type coercion/conversion functions

clj.java uses clj.base.

Usage
-----
Leiningen:
[org.soulspace.clj/clj.java "0.8.0"]

Copyright
---------
© 2011-2020 Ludger Solbach

License
-------
[Eclipse Public License 1.0](http://www.eclipse.org/legal/epl-v10.html)

Code Repository
---------------
[CljBase on GitHub](https://github.com/lsolbach/CljBase)
