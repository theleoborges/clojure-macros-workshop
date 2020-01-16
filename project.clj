(defproject macros-jam-code "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [korma "0.4.3"]
                 [org.xerial/sqlite-jdbc "3.30.1"]
                 [inflections "0.13.2"]]
  :profiles {:dev
             {:dependencies [[midje "1.9.9"]]
              :plugins [[lein-midje "3.2.2"]]}}
  :local-repo "repository")
