(ns macros-jam-code.ex1
  (:use midje.sweet))


;; Define a macro 'unless':
(defmacro unless [test then else]
  `(cond ~test
     ~else
     :else ~then))

;; So these facts hold true:
(fact
 (unless (> 10 5)
         "smaller"
         "greater") => "greater")

(fact
 (unless (> 10 20)
         "smaller"
         "greater") => "smaller")