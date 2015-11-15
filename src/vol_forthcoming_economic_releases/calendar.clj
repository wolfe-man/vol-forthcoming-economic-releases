(ns vol-forthcoming-economic-releases.calendar
  (:require [pl.danieljanus.tagsoup :as ts]
            [net.cgrand.enlive-html :as html]))

(defn find-event-type [html]
  (->> (:content html)
       (map #((comp :alt :attrs) %))
       (first)))

;"[Star]" "[djStar]"
;http://bloomberg.econoday.com/byweek.asp?day=23&month=11&year=2015&cust=bloomberg-us&lid=0
(as-> (str "http://bloomberg.econoday.com/byweek.asp?day=23&month=11&year=2015&cust=bloomberg-us&lid=0") %
      (ts/parse-xml %)
      (html/select % [:td.events :div.econoevents :a :span :img])
      ;(filter #(re-find #"Star" (find-event-type %)) %)
      ;(map find-event-type %)
      )

