;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.java.swing.swinglib
  (:use [org.soulspace.clj.java beans]
        [org.soulspace.clj.java awt]
        [org.soulspace.clj.java.awt event]
        [org.soulspace.clj.java.swing constants])
  (:import [java.awt CardLayout]
           [javax.swing AbstractAction AbstractListModel Action BorderFactory ButtonGroup
            ImageIcon InputVerifier
            JButton JCheckBox JCheckBoxMenuItem JColorChooser JComboBox JDesktopPane JDialog
            JEditorPane JFileChooser JFormattedTextField JFrame JLabel JLayeredPane JList
            JMenu JMenuBar JMenuItem JOptionPane JPanel JPasswordField JPopupMenu JProgressBar
            JRadioButton JRadioButtonMenuItem JSeparator JScrollPane JSlider JSpinner
            JSplitPane JTabbedPane JTable JTextArea JTextField JTextPane JToggleButton JToolBar
            JTree JWindow
            KeyStroke SwingConstants SwingUtilities UIManager]
           [javax.swing.event AncestorListener CaretListener CellEditorListener ChangeListener DocumentListener
            HyperlinkListener InternalFrameListener ListDataListener ListSelectionListener
            MenuDragMouseListener MenuKeyListener MenuListener MouseInputListener PopupMenuListener
            RowSorterListener TableColumnModelListener TableModelListener
            TreeExpansionListener TreeModelListener TreeSelectionListener TreeWillExpandListener
            UndoableEditListener]
           [javax.swing.table AbstractTableModel DefaultTableCellRenderer]
           [javax.swing.text MaskFormatter]
           [javax.swing.tree DefaultMutableTreeNode]
           [java.text DateFormat Format NumberFormat]
           [net.miginfocom.swing MigLayout]))

; Helpers
(defn init-swing
  "Intitializes a swing component with the arguments and items."
  ([c args]
    (if (seq args)
      (set-properties! c args))
    c)
  ([c args items]
    (if (seq args)
      (set-properties! c args))
    (if (seq items)
      (doseq [item items]
        (if (vector? item)
          (let [[child constraint] item]
            (.add c child constraint))
          (.add c item))))
    c))

; InputVerifier
(defn input-verifier
  "Creates an input verifier with the verify function 'vf' and the yield function 'yf'."
  [vf yf & args]
  (proxy [InputVerifier] []
    (verify [component] (vf component))
    (shouldYieldFocus [component] (yf component args))))

; Actions
(defn action
  "Creates an action."
  ([f]
    (let [action (proxy [AbstractAction] []
                   (actionPerformed [evt] (f evt)))]
      action))
  ([f args]
    (let [action (proxy [AbstractAction] []
                   (actionPerformed [evt] (f evt)))]
      (doseq [[k v] args]
        ;(println (str k " : " (action-keys k) " -> " v))
        (.putValue action (action-keys k) v))
      (.setEnabled action true)
      action)))

(defn key-stroke
  "Returns the key stroke for the given key code and the modifiers if given."
  ([keycode]
    (KeyStroke/getKeyStroke keycode))
  ([keycode & modifiers]
    (KeyStroke/getKeyStroke keycode (reduce + (map modifier-mask-keys modifiers)))))

(defn get-input-map
  "Returns the input map of the component."
  ([c]
    (.getInputMap c))
  ([c k]
    (.getInputMap c k)))

(defn add-key-binding
  "Adds a key binding for an action to the input map of the component."
  ([c binding-name stroke action]
    (.put (.getActionMap c) binding-name action)
    (.put (get-input-map c) stroke binding-name))
  ([c focus-key binding-name stroke action]
    (.put (.getActionMap c) binding-name action)
    (.put (get-input-map c focus-key) stroke binding-name)))

(defn installed-look-and-feels
  "Returns the installed look anf feels as a sequence of maps from names to class names."
  []
  (->>
    (UIManager/getInstalledLookAndFeels)
    (map (fn [lnf] [(.getName lnf) (.getClassName lnf)]))
    (reduce (fn [map [k v]] (assoc map k v)) {})))

(defn look-and-feel-available?
  "Checks the availability of the given look and feel by name."
  ([lnf]
    (look-and-feel-available? (installed-look-and-feels) lnf))
  ([installed-lnfs lnf]
    (contains? installed-lnfs lnf)))

; Look and feel
(defn set-look-and-feel
  "Sets the look and feel given by for the given frame (if it is available)."
  [frame lnf]
  (let [installed-lnfs (installed-look-and-feels)]
    (when (look-and-feel-available? installed-lnfs lnf)
      (UIManager/setLookAndFeel (installed-lnfs lnf))
      (SwingUtilities/updateComponentTreeUI frame))))

; Icons
(defn image-icon
  "Creates an image icon from the image data of the given URL."
  [url args]
  (init-swing (ImageIcon. url) args))

; Borders
(defn titled-border
  "Creates a titled border."
  [title]
  (BorderFactory/createTitledBorder title))

(defn show-component
  "Shows the component by setting its visibility to true."
  [c]
  (.setVisible c true))

(defn hide-component
  "Hides the component by setting its visibility to false."
  [c]
  (.setVisible c false))

; Components
(defn label
  "Creates a label."
  [args]
  (init-swing (JLabel.) args))

; getter and setter for (typed) field values
(defn get-text
  "Returns the field value as a string."
  [field]
  (.getText field))

(defn set-text
  "Sets the field value to text."
  [field text]
  (.setText field text))

(defn get-number
  "Returns the field value as a number."
  [field]
  (.parse (NumberFormat/getNumberInstance) (.getText field)))

(defn get-integer
  "Returns the field value as an integer."
  [field]
  (.parse (NumberFormat/getIntegerInstance) (.getText field)))

(defn mask-formatter
  "Creates a mask formatter for the given pattern."
  [s]
  (MaskFormatter. s))

(defn number-field
  "Creates a number field."
  ([]
    (JFormattedTextField. (NumberFormat/getNumberInstance)))
  ([args]
    (init-swing (JFormattedTextField. (NumberFormat/getNumberInstance)) args)))

(defn integer-field
  "Creates an integer field."
  ([]
    (JFormattedTextField. (NumberFormat/getIntegerInstance)))
  ([args]
    (init-swing (JFormattedTextField. (NumberFormat/getIntegerInstance)) args)))

(defn formatted-text-field
  "Creates a text field."
  ([^Format fmt]
    (JFormattedTextField. fmt))
  ([^Format fmt args]
    (init-swing (JFormattedTextField. fmt) args)))

(defn text-field
  "Creates a text field."
  ([]
    (JTextField.))
  ([args]
    (init-swing (JTextField.) args)))
  
(defn text-area
  "Creates a text area."
  ([]
    (JTextArea.))
  ([args]
    (init-swing (JTextArea.) args)))

(defn editor-pane
  "Creates a editor pane."
  ([]
    (JEditorPane.))
  ([args]
    (init-swing (JEditorPane.) args)))

(defn text-pane
  "Creates a text pane."
  ([]
    (JTextPane.))
  ([args]
    (init-swing (JTextPane.) args)))

(defn button
  "Creates a button."
  ([]
    (JButton.))
  ([args]
    (init-swing (JButton.) args)))

(defn toggle-button
  "Creates a toggle button."
  ([]
    (JToggleButton.))
  ([args]
    (init-swing (JToggleButton.) args)))

(defn check-box
  "Creates a check box."
  ([]
    (JCheckBox.))
  ([args]
    (init-swing (JCheckBox.) args)))

(defn radio-button
  "Creates a radio button."
  ([]
    (JRadioButton.))
  ([args]
    (init-swing (JRadioButton.) args)))

(defn button-group
  "Creates a button group."
  [args items]
  (init-swing (ButtonGroup.) args items))

(defn slider
  "Creates a slider."
  ([]
    (JSlider.))
  ([args]
    (init-swing (JSlider.) args)))

(defn spinner
  "Creates a spinner."
  ([]
    (JSpinner.))
  ([args]
    (init-swing (JSpinner.) args)))

(defn combo-box
  "Creates a combo box."
  [args items]
  (let [c (init-swing (JComboBox.) args)]
    (if (not (nil? items))
      (doseq [item items]
        (.addItem c item)))
    c))

(defn progress-bar
  "Creates a progress bar."
  ([]
    (JProgressBar.))
  ([args]
    (init-swing (JProgressBar.) args)))

(defn table
  "Creates a table."
  ([]
    (JTable.))
  ([args]
    (init-swing (JTable.) args)))

(defn j-list
  "Creates a swing list component."
  ([]
    (JList.))
  ([args]
    (init-swing (JList.) args)))

(defn j-tree
  "Creates a swing tree component."
  ([]
    (JTree.))
  ([args]
    (init-swing (JTree.) args)))

(defn separator
  "Creates a separator."
  ([]
    (JSeparator.))
  ([args]
    (init-swing (JSeparator.) args)))

(defn popup-menu
  "Creates a popup menu."
  [args items]
  (init-swing (JPopupMenu.) args items))

(defn menu-bar
  "Creates a menu bar."
  [args items]
  (init-swing (JMenuBar.) args items))

(defn menu
  "Creates a menu."
  [args items]
  (init-swing (JMenu.) args items))

(defn menu-item
  "Creates a menu item."
  [args]
  (init-swing (JMenuItem.) args))

(defn checkbox-menu-item
  "Creates a check box menu item."
  ([]
    (JCheckBoxMenuItem.))
  ([args]
    (init-swing (JCheckBoxMenuItem.) args)))

(defn radio-button-menu-item
  "Creates a radio button menu item."
  ([]
    (JRadioButtonMenuItem.))
  ([args]
    (init-swing (JRadioButtonMenuItem.) args)))

(defn panel
  "Creates a panel."
  [args items]
  (init-swing (JPanel.) args items))

(defn split-pane
  "Creates a split pane."
  [args items]
  (init-swing (JSplitPane.) args items))

(defn horizontal-split-pane
  "Creates a horizontal split pane."
  [args items]
  (init-swing (JSplitPane. (swing-keys :horizontal)) args items))

(defn vertical-split-pane
  "Creates a vertical split pane."
  [args items]
  (init-swing (JSplitPane. (swing-keys :vertical)) args items))

(defn scroll-pane 
  "Creates a scroll pane."
  ([item]
    (JScrollPane. item))
  ([item args]
    (let [c (JScrollPane. item)]
      (set-properties! c args)
      c)))

(defn tabbed-pane
  "Creates a tabbed pane."
  [args items]
  (let [ c (init-swing (JTabbedPane.) args)]
    (if (not (nil? items))
      (doseq [[title component] items]
        (.addTab c title component)))
    c))

(defn layered-pane
  "Creates a layered pane."
  [args items]
  (init-swing (JLayeredPane.) args items))

(defn tool-bar
  "Creates a tool bar."
  [args items]
  (init-swing (JToolBar.) args items))

(defn canvas-panel
  "Creates a panel into which the paint function can paint using the provided graphics context."
  [paint-fn args items]
  (init-swing
    (proxy [javax.swing.JPanel] []
      (paintComponent [^java.awt.Graphics g]
        (proxy-super paintComponent g)
        (paint-fn g)))
    args items))

(defn frame
  "Creates a frame."
  [args cp-items]
  (let [c (JFrame.)]
    (set-properties! c args)
    (if (not (nil? cp-items))
      (doseq [item cp-items]
        (.add (.getContentPane c) item)))
    c))

(defn window
  "Creates a window."
  [args cp-items]
  (let [c (JWindow.)]
    (set-properties! c args)
    (if (not (nil? cp-items))
      (doseq [item cp-items]
        (.add (.getContentPane c) item)))
    c))

(defn dialog
  "Creates a dialog. If a frame is provided, the dialog is centered on the frame."
  ([args cp-items]
    (let [c (JDialog.)]
      (set-properties! c args)
      (if (not (nil? cp-items))
        (doseq [item cp-items]
          (.add (.getContentPane c) item)))
      (.pack c)
      c))
  ([frame args cp-items]
    (let [c (JDialog. frame)]
      (set-properties! c args)
      (if (not (nil? cp-items))
        (doseq [item cp-items]
          (.add (.getContentPane c) item)))
      (.pack c)
      (.setLocationRelativeTo c frame)
      c)))

; standard dialogs
; TODO add frame parameter to the dialogs
(defn file-open-dialog
  "Returns the filename to open or nil if the dialog was aborted."
  ([filename]
    (let [d (JFileChooser. filename)]
      (let [state (.showOpenDialog d nil)]
        (if (= state JFileChooser/APPROVE_OPTION)
          (.getSelectedFile d)
          nil)))))

(defn file-save-dialog
  "Returns the filename to save or nil if the dialog was aborted."
  ([filename]
    (let [d (JFileChooser. filename)]
      (let [state (.showSaveDialog d nil)]
        (if (= state JFileChooser/APPROVE_OPTION)
          (.getSelectedFile d)
          nil)))))

(defn color-choose-dialog
  "Creates a color choose dialog."
  ([c title color]
    (JColorChooser/showDialog c title color)))

(defn message-dialog
  "Creates a message dialog."
  ([text]
    (JOptionPane/showMessageDialog nil text))
  ([text title type]
    (JOptionPane/showMessageDialog
      nil text title (option-pane-message-keys type)))
  ([text title type icon]
    (JOptionPane/showMessageDialog
      nil text title (option-pane-message-keys type) icon)))

(defn confirm-dialog
  "Creates a confirm dialog."
  ([text title options]
    (JOptionPane/showConfirmDialog nil text title options))
  ([text title options type]
    (JOptionPane/showConfirmDialog
      nil text title options (option-pane-message-keys type)))
  ([text title options type icon]
    (JOptionPane/showConfirmDialog
      nil text title options (option-pane-message-keys type) icon)))

(defn input-dialog
  "Creates an input dialog."
  ([text]
    (JOptionPane/showInputDialog text))
  ([text title]
    (JOptionPane/showInputDialog nil text title))
  ([text title type]
    (JOptionPane/showInputDialog
      nil text title (option-pane-message-keys type)))
  ([text title type icon values initial]
    (JOptionPane/showInputDialog
      nil text title (option-pane-message-keys type) icon (into-array values) initial)))

(defn option-pane
  "Creates an option pane dialog."
  ([]
    (JOptionPane.))
  ([args]
    (init-swing (JOptionPane.) args)))

; Renderer
(defn table-cell-renderer
  "Creates a cell renderer with the rebder function 'rf'."
  ([rf args]
    (proxy [DefaultTableCellRenderer] []
      (getTableCellRendererComponent [table value isSelected hasFocus row column]
        (let [result (proxy-super getTableCellRendererComponent table value isSelected hasFocus row column)]
          (set-properties! this args)
          (.setText this (rf value))
          result)))))


; mapseq ColumnSpec
;[{:label "Text" :key :text :edit false :converter function}]

; TODO use labeled keyword arguments instead of a map
; Default models
(defn mapseq-table-model
  "Creates a table model backed with a sequence of maps. The col-spec map may contain "
  [col-spec data]
  (proxy [AbstractTableModel] []
    (getColumnCount [] (count col-spec))
    (getRowCount [] (count data))
    (isCellEditable [_ col] (:edit (nth col-spec col) false))
    (getColumnName [col] (:label (nth col-spec col) (str "Label " col)))
    (getValueAt [row col] ((:converter (nth col-spec col) identity)
                            ((:key (nth col-spec col)) (nth data row))))))

; TODO add converter like in mapseq-table-model?
(defn seq-list-model
  "Creates a list model backed with the 'data' sequence."
  ([data]
    (proxy [AbstractListModel] []
      (getElementAt [idx] (nth data idx))
      (getSize [] (count data)))))

; DefaultMutableTreeNode
(defn tree-node
  "Creates a tree node."
  [obj args items]
  (init-swing (DefaultMutableTreeNode. obj) args items))


; CardLayout
(defn card-layout
  "Creates a card layout."
  [args items]
  (init-swing (CardLayout.) args items))

(defn first-card
  "Shows the first card on the container."
  [cl container]
  (.first cl container))

(defn next-card
  "Shows the next card on the container."
  [cl container]
  (.next cl container))

(defn previous-card
  "Shows the previous card on the container."
  [cl container]
  (.previous cl container))

(defn last-card
  "Shows the last card on the container."
  [cl container]
  (.last cl container))

(defn show-card
  "Shows the card on the container with the given name."
  [cl container name]
  (.show cl container name))


; MigLayout
(defn mig-layout
  "Creates a mig layout."
  [args]
  (init-swing (MigLayout.) args))
