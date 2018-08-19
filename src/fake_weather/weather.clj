(ns fake-weather.weather)

(defn- rounded-rand
  [bound places]
  (let [m (loop [p (int places) v (int 1)]
            (if (zero? p) v (recur (dec p) (* v 10))))
        n (rand-int (* m bound))]
    (/ n (float m))))

(def wind-directions
  ["N" "NNE" "NE" "ENE" "E" "ESE" "SE" "SSE"
   "S" "SSW" "SW" "WSW" "W" "WNW" "NW" "NNW"])

(def descriptions
  ["Sunny" "Partly Cloudy" "Mostly Cloudy"
   "Thunderstorms" "Scattered Thunderstorms"
   "Rainy" "Foggy" "Snowy" "Stormy"])

(defn- randomized
  [v]
  (v (rand-int (count v))))

(defn generate
  []
  {"precip" (rounded-rand 5 1)
   "temperature" (+ 40 (rand-int 60))
   "desc" (randomized descriptions)
   "humidity" (rand-int 101)
   "dewPoint" (+ 32 (rand-int 40))
   "wind" (rand-int 15)
   "windDir" (randomized wind-directions)
   "pressureInHg" (+ 29.0 (rounded-rand 2.0 2))})

(defn conditions
  [zipcode]
  {"conditions"
   (assoc (generate) "location" zipcode)})

(defn forecast
  [zipcode]
  {"forecast"
   (mapv (fn [_] (generate)) (range 7))})
