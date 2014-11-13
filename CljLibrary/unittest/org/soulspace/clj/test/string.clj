(ns org.soulspace.clj.test.string
  (:use
    [clojure.test]
    [org.soulspace.clj.string]))

(deftest gt-test
  (is (true? (gt "b" "a")))
  (is (true? (gt "ab" "aa")))
  (is (false? (gt "a" "a")))
  (is (false? (gt "aa" "aa")))
  (is (false? (gt "a" "b")))
  (is (false? (gt "aa" "ab"))))

(deftest ge-test
  (is (true? (ge "b" "a")))
  (is (true? (ge "ab" "aa")))
  (is (true? (ge "a" "a")))
  (is (true? (ge "aa" "aa")))
  (is (false? (ge "a" "b")))
  (is (false? (ge "aa" "ab"))))

(deftest lt-test
  (is (false? (lt "b" "a")))
  (is (false? (lt "ab" "aa")))
  (is (false? (lt "a" "a")))
  (is (false? (lt "aa" "aa")))
  (is (true? (lt "a" "b")))
  (is (true? (lt "aa" "ab"))))

(deftest le-test
  (is (false? (le "b" "a")))
  (is (false? (le "ab" "aa")))
  (is (true? (le "a" "a")))
  (is (true? (le "aa" "aa")))
  (is (true? (le "a" "b")))
  (is (true? (le "aa" "ab"))))
