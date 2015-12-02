(ns vol-forthcoming-economic-releases.calendar
  (:require [pl.danieljanus.tagsoup :as ts]
            [net.cgrand.enlive-html :as html]))

(defn get-event-names [html]
  (->> (html/select html [:a])
       (map :content)
       (ffirst)))

(defn get-significant-events [html]
  (as-> (html/select html [:a :span.star-img :img]) %
        (map #((comp :alt :attrs) %) %)
        (if (= "[Star]" (first %)) html)
        ))

(defn group-by-day [html]
  (html/select html [:div.econoevents]))

(defn bloomberg-important-dates [monday-parts]
  ;http://bloomberg.econoday.com/byweek.asp?day=23&month=11&year=2015&cust=bloomberg-us&lid=0
  (let [year (first monday-parts)
        month (inc (second monday-parts))
        day (last monday-parts)]
    (as-> (str "http://bloomberg.econoday.com/byweek.asp?day=" day "&month="month"&year="year"&cust=bloomberg-us&lid=0") %
          (ts/parse-xml %)
          (html/select % [:td.events])
          (map group-by-day %)
          (map #(map get-significant-events %) %)
          (map #(remove nil? %) %)
          (map #(map get-event-names %) %)
          (map #(hash-map (count %) %) %)
          )))

;(bloomberg-important-dates [2015 10 23])
