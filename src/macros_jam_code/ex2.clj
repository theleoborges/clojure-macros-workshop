(ns macros-jam-code.ex2
  (:use midje.sweet))

;;
;; Given this map:
;;

(def guitar
  {:model "EC-401FM"
   :brand "ESP"
   :specs {
           :pickups {:neck {:brand "EMG"
                            :model "EMG 60"}
                     :bridge {:brand "EMG"
                              :model "EMG 81"}}
           :body "Mahoganny"
           :neck "Mahoganny"}})


;;
;; Write a macro 't':
;;
(defmacro t [& args])


;; So these facts hold true:

(fact
 (t guitar :specs :pickups :neck :model) => "EMG 60")


(fact (t 2
         (+ 4)
         (* 50)
         (/ 6)) => 50)