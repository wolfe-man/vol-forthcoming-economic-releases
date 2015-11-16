(ns vol-forthcoming-economic-releases.calendar
  (:require [pl.danieljanus.tagsoup :as ts]
            [net.cgrand.enlive-html :as html]))

(defn count-important [icons]
  (->> icons
       (filter #(re-find #"Star" %)) ;"[Star]" "[djStar]"
       (count)))

(defn get-events [html]
  (->> (:content html)
       (map #((comp :alt :attrs) %))
       (first)))

(defn get-event-type [html]
  (let [events (html/select html [:a :span])]
    (map get-events events)))

(defn group-by-day [html]
  (html/select html [:div.econoevents]))

(defn bloomberg-important-dates [day month year]
  ;http://bloomberg.econoday.com/byweek.asp?day=23&month=11&year=2015&cust=bloomberg-us&lid=0
  (as-> (str "http://bloomberg.econoday.com/byweek.asp?day=23&month=11&year=2015&cust=bloomberg-us&lid=0") %
        (ts/parse-xml %)
        (html/select % [:td.events])
        (map group-by-day %)
        (map get-event-type %)
        (map count-important %)))

