(ns macros-jam-code.ex6
  (:use midje.sweet))

;; TODO: Level: Easy

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
  `(let [n# ~n]
     (* n# n#)))

(fact
 (let [seed (atom 9)
       next (fn [] (swap! seed inc))]
   (square (next))) => 100)