(ns macros-jam-code.ex4
  (:use midje.sweet))

;;
;; Write a macro 'let-fn':
;;
(defmacro let-fn [& args])


;;
;; So that it allows us to declare a function
;; for use in that scope. This makes sense since the inner function
;; fac has no meaning outside the outer factorial function
;;
(defn factorial [n]
  (let-fn (fac [n acc]
               (if (= n 1)
                 acc
                 (recur (dec n) (* acc n))))
          (fac n 1)))


;; And these facts hold true:

(fact
 (factorial 5) => 120)