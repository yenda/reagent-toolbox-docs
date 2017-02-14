;;;; Copyright © 2017 Flexpoint Tech Ltd

(ns reagent-toolbox-docs.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            reagent-toolbox-docs.components.app-bar
            reagent-toolbox-docs.components.autocomplete
            reagent-toolbox-docs.components.avatar
            [reagent-toolbox-docs.db :as db]
            [reagent-toolbox-docs.routing :as routing]
            [reagent-toolbox-docs.layout :as layout]
            [reagent-toolbox-docs.config :as config]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [layout/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (dev-setup)
  (re-frame/dispatch-sync [:initialize-db])
  (routing/start!)
  (mount-root))