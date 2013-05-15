(ns macros-jam-code.ex8
  (:use midje.sweet))

;;
;; Monad comprehension with the list monad
;;



;; We first define the list monad
(def list-monad
  {:return (fn [v] [v])
   :bind (fn [mv f]
           (if (seq mv)
             (apply concat (map f mv))
             []))})

;;
;; As per the monad above, the fact below holds true:
;;
(fact
 (let [bind (:bind list-monad)
      return (:return list-monad)]
  (-> [1 2]
      (bind (fn [a]
              (-> [a (- a)]
                  (bind (fn [b]
                          (return (* 3 b))))))))) => '(3 -3 6 -6))

;;
;; Write a macro 'domonad':
;;
(defmacro domonad [& args])

;; That allows for a nicer monad comprehension syntax and makes the
;;fact below hold true:

(fact
 (domonad list-monad
    [a [1 2]
     b [a, (- a)]]
     (* 3 b)) => '(3 -3 6 -6))