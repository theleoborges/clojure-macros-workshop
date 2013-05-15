(ns macros-jam-code.ex6
  (:use midje.sweet))

;;
;; Macro hygiene
;;

;;
;; Part I
;;

;;
;; The macro below has a bug. Can you spot it?
;;

(defmacro square [n]
  `(* ~n ~n))

(fact
 (let [seed (atom 9)
       next (fn [] (swap! seed inc))]
   (square (next))) => 100)