(ns vol-forthcoming-economic-releases.date
  (:require [date-clj :as dc]))

(defn date-parts [monday]
  (map #(% monday) [dc/year dc/month dc/day]))

(defn week-days [monday]
  (let [generate-days (fn [day] (-> day (dc/add 1 :day)))]
    (->> (iterate generate-days monday)
         (take 5 )
         (map #(dc/format-date % "yyyy-MM-dd")))))

(defn mondays [monday]
  (let [generate-days (fn [day] (-> day (dc/add 1 :week)))]
    (->> (iterate generate-days monday)
         (take 3 ))))
