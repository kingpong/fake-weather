(ns fake-weather.handler)

(defn app
  [request respond raise]
  (respond {:status 200
            :body "OK"
            :headers {"content-type" "text/plain; charset=utf-8"}}))
