(ns macros-jam-code.ex9
  (:require [clojure.java.jdbc :as jdbc])
  (:use midje.sweet
        korma.db
        korma.core
        inflections.core))

;; Korma is a SQL DSL for clojure
;; We start by defining a database
(defdb db (sqlite3 {:db "db/sample.db"}))

;; And declaring the vars we'll be using
(declare users addresses)

;; Now we can define our entities
(defentity users
  (entity-fields :first :last)
  (has-one addresses {:fk :user_id}))

(defentity addresses
  (entity-fields :address :postcode)
  (belongs-to users {:fk :user_id}))

;; And write some facts about the DSL
(with-state-changes
  [(before :facts (do (delete users)
                      (delete addresses)
                      (insert users
                              (values {:first "john" :last "doe"}))
                      
                      (insert users
                              (values {:first "James" :last "Hetfield"}))))]
  (fact "Retrieves all users"
        (count (select users))  => 2)

  
  (fact "Retrieves a single user and their address"
        (let [user-id (-> (insert users
                                  (values {:first "Leonardo" :last "Borges"}))
                          vals
                          first)]
          (insert addresses
                  (values {:address "Pitt St" :postcode "2000" :user_id user-id}))
          
          (-> (select users                         
                      (where {:id user-id}))
              first
              :first)

          => "Leonardo"
          
          (-> (select users
                      (with addresses
                            (fields :addresses.address
                                    :addresses.postcode))
                      (where {:id user-id}))
              first
              :address)
          => "Pitt St")))

;; The DSL is very concise and clear, but I'd like to have nice
;; shortcuts for common operations such as finding all users, creating
;; users and finding a single user.

;;To that end, write a macro `def-basic-entity` that replaces the
;;`defentity` macro above in a way that it makes the following facts true



(defn all-users [& args])
(defn create-user [& args])
(defn find-user [& args])
(defn create-address [& args])

;; Note that the functions above are only so declared to make the code
;; compile. The actual magic should be in your macro. The objective of
;; this exercise is to NOT have to define these functions/macros manually.
(defmacro def-basic-entity [& args])

(def-basic-entity users
  (entity-fields :first :last)
  (has-one addresses {:fk :user_id}))

(def-basic-entity addresses
  (entity-fields :address :postcode)
  (belongs-to users {:fk :user_id}))




(with-state-changes
  [(before :facts (do (delete users)
                      (delete addresses)
                      (create-user {:first "john" :last "doe"}) 
                      (create-user {:first "James" :last "Hetfield"})))]
  (fact "Retrieves all users"
        (count (all-users))  => 2)

  
  (fact "Retrieves a single user and their address"
        (let [user-id (-> (create-user {:first "Leonardo" :last "Borges"})
                          vals
                          first)]
          (create-address {:address "Pitt St" :postcode "2000" :user_id user-id})
          
          (:first (find-user user-id))
          => "Leonardo"
          
          (:address (find-user user-id
                               (with addresses
                                     (fields :addresses.address
                                             :addresses.postcode))))
          => "Pitt St")))