(ns macros-jam-code.ex5
  (:use midje.sweet))

;;
;; Write a macro 'with-file':
;;
(defmacro with-file [file-name & body]
  `(let [~'file (java.util.Scanner. (java.io.File. ~file-name))]
     (try
       ~@body
       (finally
        (.close ~'file)))))


;;
;; So that it allows us to read the contents of a file and have the file
;; be automatically closed at the end.
;;
;; And these facts hold true:

(fact
 (with-file "project.clj"
   (if (.hasNextLine file)
     (.nextLine file))) => "(defproject macros-jam-code \"0.1.0-SNAPSHOT\"")