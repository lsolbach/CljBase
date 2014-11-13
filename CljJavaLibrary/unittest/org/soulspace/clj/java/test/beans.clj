(ns org.soulspace.clj.java.test.beans
  (:use
    [clojure.test]
    [org.soulspace.clj.java.beans]))

(defn method-name [method]
  (.getName method))

(deftest get-method-test
  (is (= (getter-method javax.swing.JFrame "menuBar") nil))
  )

(deftest set-method-test
  (is (= (method-name (setter-method javax.swing.JFrame "menuBar")) "setMenuBar"))
  )
