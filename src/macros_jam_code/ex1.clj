(ns macros-jam-code.ex1
  (:use midje.sweet))


;; Define a macro 'unless':
(defmacro unless [& args])

;; So these facts hold true:
(fact
 (unless (> 10 5)
         "smaller"
         "greater") => "smaller")

(fact
 (unless (> 10 20)
         "smaller"
         "greater") => "greater")