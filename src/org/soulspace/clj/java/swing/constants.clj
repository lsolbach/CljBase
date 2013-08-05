;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.java.swing.constants
  (:import [java.awt Event]
           [javax.swing Action JFileChooser JOptionPane SwingConstants WindowConstants]))

; Constant maps
(def swing-keys {:bottom     SwingConstants/BOTTOM
                 :center     SwingConstants/CENTER
                 :east       SwingConstants/EAST
                 :horizontal SwingConstants/HORIZONTAL
                 :leading    SwingConstants/LEADING
                 :left       SwingConstants/LEFT
                 :next       SwingConstants/NEXT
                 :north      SwingConstants/NORTH
                 :north-east SwingConstants/NORTH_EAST
                 :north-west SwingConstants/NORTH_WEST
                 :previous   SwingConstants/PREVIOUS
                 :right      SwingConstants/RIGHT
                 :south      SwingConstants/SOUTH
                 :south-east SwingConstants/SOUTH_EAST
                 :south-west SwingConstants/SOUTH_WEST
                 :top        SwingConstants/TOP
                 :trailing   SwingConstants/TRAILING
                 :vertical   SwingConstants/VERTICAL
                 :west       SwingConstants/WEST
                 })

(def window-keys {:nothing WindowConstants/DO_NOTHING_ON_CLOSE
                  :dispose WindowConstants/DISPOSE_ON_CLOSE
                  :hide    WindowConstants/HIDE_ON_CLOSE
                  :exit    WindowConstants/EXIT_ON_CLOSE
                  })

(def action-keys {:name        Action/NAME
                  :accelerator Action/ACCELERATOR_KEY
                  :command-key Action/ACTION_COMMAND_KEY
                  :long-desc   Action/LONG_DESCRIPTION
                  :short-desc  Action/SHORT_DESCRIPTION
                  :mnemonic    Action/MNEMONIC_KEY
                  :icon        Action/SMALL_ICON
                  })

(def modifier-mask-keys {:ctrl  Event/CTRL_MASK
                         :shift Event/CTRL_MASK
                         :alt   Event/ALT_MASK})

(def filechooser-keys {:approve               JFileChooser/APPROVE_OPTION
                       :cancel                JFileChooser/CANCEL_OPTION
                       :error                 JFileChooser/ERROR_OPTION
                       :directories-only      JFileChooser/DIRECTORIES_ONLY
                       :files-only            JFileChooser/FILES_ONLY
                       :files-and-directories JFileChooser/FILES_AND_DIRECTORIES
                       })

; TODO use in message dialogs
(def option-pane-message-keys {:error    JOptionPane/ERROR_MESSAGE
                               :warning  JOptionPane/WARNING_MESSAGE
                               :question JOptionPane/QUESTION_MESSAGE
                               :info     JOptionPane/INFORMATION_MESSAGE
                               :plain    JOptionPane/PLAIN_MESSAGE
                               })

(def option-pane-keys {:default       JOptionPane/DEFAULT_OPTION
                       :yes-no        JOptionPane/YES_NO_OPTION
                       :yes-no-cancel JOptionPane/YES_NO_CANCEL_OPTION
                       :ok-cancel     JOptionPane/OK_CANCEL_OPTION
                       :yes           JOptionPane/YES_OPTION
                       :no            JOptionPane/NO_OPTION
                       :cancel        JOptionPane/CANCEL_OPTION
                       :ok            JOptionPane/OK_OPTION
                       :closed        JOptionPane/CLOSED_OPTION
                       })
