(ns macros-jam-code.ex3
  (:use midje.sweet))

;;
;; LINQ inspired API
;;

(def names ["Burke", "Connor", "Frank", "Everett", "Albert", "George", "Harris", "David"]) 


;;
;; Write a macro 'from':
;;
(defmacro from [& args])



;;
;; So that these facts hold true:
;;
(fact
 (from n in names
       (where (= (count n) 5))
       (orderby n)
       (select (.toUpperCase n))) => '("BURKE" "DAVID" "FRANK"))