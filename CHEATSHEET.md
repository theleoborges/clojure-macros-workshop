# Macros workshop cheat sheet

### Defining macros

```clojure
(defmacro macro-name [& args] 
	...)
```

### Quoting

Quoting prevents the form following the single-quote sign from being evaluated:

```clojure
user=> (def my-list '(1 2 3))
```

### Syntax-quote

Like quote in that it prevents the evaluation of the form following its operator (`). The difference is that it attempts to namespace-qualify all symbols in the given form:

```clojure
user=> `(+ my-list) 
;; (clojure.core/+ user/my-list)
```

### Unquoting

In a quoted form, unquote (~) forces the evaluation of the form following it at macro-expansion time:

```clojure
user=> `(+ ~my-list) 
;;(clojure.core/+ (1 2 3))
```

### Unquote-splicing

In a quoted form, unquote-splicing (~@) forces the evaluation of the form - which is assumed to be a list - and _unpacks_ its content in the position it is used:

```clojure
user=> `(+ ~@my-list) 
;; (clojure.core/+ 1 2 3)
```

### Debugging

The built-in `macroexpand` function expands the given quoted form until it doesn't represent a macro any longer:

```
user=>  (macroexpand '(cond 
                        (even? 2) "even"
                        :else "odd"))
               
;; (if (even? 2)
;;   "even"
;;   (clojure.core/cond :else "odd"))               
```

Sometimes it's useful to recursively expand macros until the form can't be expanded further. The function `macroexpand-all` from the `clojure.walk` namespace does just that:

```
user=> (require '[clojure.walk :as w])
user=> (w/macroexpand-all '(cond
                            (even? 2) "even"
                            :else "odd"))
                            
;; (if (even? 2)
;;   "even"
;;   (if :else "odd" nil))
```