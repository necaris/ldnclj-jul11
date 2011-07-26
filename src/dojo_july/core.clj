(ns dojo-july.core
  (:use compojure.core
        hiccup.core
        hiccup.page-helpers
        hiccup.form-helpers)
  (:require
   [compojure.route :as route]
   [compojure.handler :as handler]))

(def signup-list (atom [{:name "Steve" :email "Steve@steve.com"}] ))

(defn get-signup []
  (html [:h1 "Welcome to Team 4's Signup"]
        [:form {:method "post" :action "/signup"}
         (text-field "name" "Your name here")
         (text-field "email" "Your email here")
         (submit-button "Sign up!")]\
         [:p (str "There are " (count @signup-list) " people here")]))

(defn post-signup [name email]
  (swap! signup-list conj {:name name, :email email})
  (html [:h1 "Thanks for signing up!"]
        [:p (str "You're signed up as "
                 name
                 " with email "
                 email)]))

(defn admin-page []
  (html [:p "The people signed up so far are:"]
        (ordered-list (map-indexed (fn [i x] [:p (str (x :name)
                                        " "
                                        (x :email) " ") [:a {:href (str "/delete/" i)} "Delete"]])
                           @signup-list))))

(defn delete [x]
   (swap! signup-list (fn [l] (concat (take (dec x) l) (drop x l))))
   (html [:p "Deleted! "] [:a {:href "/admin"} "Back"]))

(defroutes main-routes
  (GET "/" [] (get-signup))
  (GET "/signup" [] (get-signup))
  (POST "/signup" [name email]
        (post-signup name email))
  (GET "/admin" [] (admin-page))
  (GET "/delete/:x" [x] (delete (Integer/parseInt x)))
  (route/not-found "Page not found")
  )

(def app (handler/site main-routes))
