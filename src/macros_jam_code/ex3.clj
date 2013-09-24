(ns macros-jam-code.ex3
  (:use midje.sweet))

;;
;; LINQ inspired API
;;

(def names ["Burke", "Connor", "Frank", "Everett", "Albert", "George", "Harris", "David"]) 


;;
;; Write a macro 'from':
;;
(defmacro from [name _ coll [_ where-body] [_ orderby-body] [_ select-body]]
  `(->> ~coll
        (filter (fn [~name]
                  ~where-body))
        (sort-by (fn [~name]
                   ~orderby-body))
        (map (fn [~name]
               ~select-body))))


;;
;; So that these facts hold true:
;;
(fact
 (from n in names
       (where (= (. n length) 5))
       (orderby n)
       (select (. n toUpperCase))) => '("BURKE" "DAVID" "FRANK"))