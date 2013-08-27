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