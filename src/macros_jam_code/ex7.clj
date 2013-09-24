(ns macros-jam-code.ex7
  (:use midje.sweet))

;;
;; Given the following macro:
;;
(defmacro nif [val pos zero neg]
  "Numeric if. Executes pos, zero or neg depending
  if val is positive, zero or negative respectively"
  `(let [res# ~val]
     (cond (pos? res#) ~pos
           (zero? res#) ~zero
           :else ~neg)))


;;The fact below HOLDS true as it is:
(fact
 (nif -1
      "positive"
      "zero"
      "negative") => "negative")



;;
;; However this next fact doesn't. Can you spot the bug?
;; Now fix it.
;;

(fact "Should not throw an Exception"
      (let [res (java.util.Scanner. (java.io.FileInputStream. "project.clj"))]
        (do (nif 0
                 "positive"
                 (prn (.nextLine res))
                 (prn "negative"))
            (.close res))) => nil)