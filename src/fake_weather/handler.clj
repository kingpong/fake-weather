(ns fake-weather.handler
  (:require [compojure.api.sweet :refer [api GET undocumented]]
            [compojure.response :as response]
            [compojure.route :as route]
            [fake-weather.weather :as weather]
            [jsonista.core :as json]
            [promesa.core :as promesa]
            [ring.middleware.json])
  (:import (java.util.concurrent CompletionStage)
           (java.util.function BiFunction)))

(defn- bifn [f f-ex]
  (reify BiFunction
    (apply [_ v ex] (if ex (f-ex ex) (f v)))))

(extend-protocol response/Sendable
  CompletionStage
  (send* [cs request respond raise]
    (.handleAsync cs (bifn #(response/send % request respond raise) raise))))

(defmacro after-random-delay
  [max-ms & body]
  `(promesa/delay (rand-int ~max-ms) (do ~@body)))

(def app*
  (api
    (GET "/conditions/:zipcode" [zipcode]
      (after-random-delay 100
        {:status 200 :body (weather/conditions zipcode)}))

    (GET "/forecast/:zipcode" [zipcode]
      (after-random-delay 250
        {:status 200 :body (weather/forecast zipcode)}))

    (undocumented
      (route/not-found
        {:body {:message "not-found"}}))))

(def app (-> app*
             (ring.middleware.json/wrap-json-response)))


