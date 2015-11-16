(ns vol-forthcoming-economic-releases.date
  (:require [joda-time :as jt]))


(-> (jt/local-date)
    (jt/property :dayOfMonth) jt/value)

(-> (jt/local-date)
    (jt/property :monthOfYear) jt/value)

(-> (jt/local-date)
    (jt/property :year) jt/value)
