(ns vol-forthcoming-economic-releases.twelve
  (:require [date-clj :as dc]
            [vol-forthcoming-economic-releases.date :as d]
            [vol-forthcoming-economic-releases.calendar :as c]))

(defn week-volume [monday]
  (let [monday-parts (d/date-parts monday)
        release-volume (c/bloomberg-important-dates monday-parts)
        week-days (d/week-days monday)]
    (do (Thread/sleep 300)
      (zipmap week-days release-volume))))

(defn twelve-days [date]
  (let [mondays (d/mondays date)]
    (->> mondays
         (map week-volume)
         ;dont (apply merge) ;map is no longer sorted
         )))

(twelve-days (dc/monday))



