(ns vol-forthcoming-economic-releases.twelve
  (:require [date-clj :as dc]
            [vol-forthcoming-economic-releases.date :as d]
            [vol-forthcoming-economic-releases.calendar :as c]))

(defn week-volume [monday]
  (let [parts (d/date-parts monday)
        release-volume (c/bloomberg-important-dates parts)
        week-days (d/week-days monday)]
    (zipmap week-days release-volume)))

(defn twelve-days [date]
  (let [mondays (d/mondays date)]
    (->> mondays
         (map week-volume)
         (apply merge))))

(twelve-days (dc/monday))





