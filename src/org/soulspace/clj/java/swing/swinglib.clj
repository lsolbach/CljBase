(ns org.soulspace.clj.java.swing.swinglib
  (:use [org.soulspace.clj.java beans]
        [org.soulspace.clj.java awt]
        [org.soulspace.clj.java.awt event]
        [org.soulspace.clj.java.swing constants])
  (:import [javax.swing AbstractAction AbstractListModel Action BorderFactory ButtonGroup InputVerifier
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
           [javax.swing.table AbstractTableModel]
           [java.text NumberFormat DateFormat]
           [net.miginfocom.swing MigLayout]))

; Helpers
(defn init-swing
  ([c args]
    (set-properties! c args)
    c)
  ([c args items]
    (set-properties! c args)
    (if (seq items)
      (doseq [item items]
        (if (vector? item)
          (let [[child constraint] item]
            (.add c child constraint))
          (.add c item))))
    c))

; Listeners
; TODO add adapters for multi listeners and add listener functions
(defn caret-listener [f args]
  (proxy [CaretListener] []
    (caretUpdate [event] (f event args))))

(defn change-listener [f args]
  (proxy [ChangeListener] []
    (stateChanged [event] (f event args))))

(defn hyperlink-listener [f args]
  (proxy [HyperlinkListener] []
    (hyperlinkUpdate [event] (f event args))))

(defn list-selection-listener [f args]
  (proxy [ListSelectionListener] []
    (valueChanged [event] (f event args))))

; InputVerifier
(defn input-verifier [vf yf & args]
  (proxy [InputVerifier] []
    (verify [component] (vf component))
    (shouldYieldFocus [component] (yf component args))))

; Actions
(defn action
  ([f]
    (let [action (proxy [AbstractAction] []
                   (actionPerformed [evt] (f evt)))]
      action))
  ([f args]
    (let [action (proxy [AbstractAction] []
                   (actionPerformed [evt] (f evt)))]
      (doseq [[k v] args]
        (println (str k " : " (action-keys k) " -> " v))
        (.putValue action (action-keys k) v))
      (.setEnabled action true)
      action)))

(defn key-stroke
  ([chr]
    (KeyStroke/getKeyStroke chr))
  ([keycode & modifiers]
    (KeyStroke/getKeyStroke keycode (reduce + (map modifier-mask-keys modifiers)))))

; Look and feel
(defn set-look-and-feel [frame look]
  "Sets the look and feel for the given frame."
  (cond (= look :metal)
        (UIManager/setLookAndFeel "javax.swing.plaf.metal.MetalLookAndFeel")
        (= look :nimbus)
        (UIManager/setLookAndFeel "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel")
        (= look :synth)
        (UIManager/setLookAndFeel "javax.swing.plaf.synth.SynthLookAndFeel")
        ; (= look :windows)
        ; (UIManager/setLookAndFeel "com.sun.java.swing.plaf.windows.WindowsLookAndFeel")
        (= look :gtk)
        (UIManager/setLookAndFeel "com.sun.java.swing.plaf.gtk.GTKLookAndFeel"))
  (SwingUtilities/updateComponentTreeUI frame))

; Borders
(defn titled-border [title]
  (BorderFactory/createTitledBorder title))

; getter for typed field values
(defn get-number [field]
  (.parse (NumberFormat/getNumberInstance) (.getText field)))

(defn get-integer [field]
  (.parse (NumberFormat/getIntegerInstance) (.getText field)))

; Components
(defn label [args]
  (init-swing (JLabel.) args))

(defn number-field [args]
  (init-swing (JFormattedTextField. (NumberFormat/getNumberInstance)) args))

(defn integer-field [args]
  (init-swing (JFormattedTextField. (NumberFormat/getIntegerInstance)) args))

(defn text-field [args]
  (init-swing (JTextField.) args))
  
(defn text-area [args]
  (init-swing (JTextArea.) args))

(defn editor-pane [args]
  (init-swing (JEditorPane.) args))

(defn text-pane [args]
  (init-swing (JTextPane.) args))

(defn button [args]
  (init-swing (JButton.) args))

(defn toggle-button [args]
  (init-swing (JToggleButton.) args))

(defn checkbox [args]
  (init-swing (JCheckBox.) args))

(defn radio-button [args]
  (init-swing (JRadioButton.) args))

(defn button-group [args items]
  (init-swing (ButtonGroup.) args items))

(defn slider [args]
  (init-swing (JSlider.) args))

(defn spinner [args]
  (init-swing (JSpinner.) args))

(defn combo-box [args items]
  (let [c (init-swing (JComboBox.) args)]
    (if (not (nil? items))
      (doseq [item items]
        (.addItem c item)))
    c))

(defn progress-bar [args]
  (init-swing (JProgressBar.) args))

(defn table [args]
  (init-swing (JTable.) args))

(defn j-list [args]
  (init-swing (JList.) args))

(defn j-tree [args]
  (init-swing (JTree.) args))

(defn separator [args]
  (init-swing (JSeparator.) args))

(defn popup-menu [args items]
  (init-swing (JPopupMenu.) args items))

(defn menu-bar [args items]
  (init-swing (JMenuBar.) args items))

(defn menu [args items]
  (init-swing (JMenu.) args items))

(defn menu-item [args]
  (init-swing (JMenuItem.) args))

(defn checkbox-menu-item [args]
  (init-swing (JCheckBoxMenuItem.) args))

(defn radio-button-menu-item [args]
  (init-swing (JRadioButtonMenuItem.) args))

(defn panel [args items]
  (init-swing (JPanel.) args items))

(defn split-pane [args items]
  (init-swing (JSplitPane.) args items))

(defn horizontal-split-pane [args items]
  (init-swing (JSplitPane. (swing-keys :horizontal)) args items))

(defn vertical-split-pane [args items]
  (init-swing (JSplitPane. (swing-keys :vertical)) args items))

(defn scroll-pane 
  ([item]
    (JScrollPane. item))
  ([item args]
    (let [c (JScrollPane. item)]
      (set-properties! c args)
      c)))

(defn tabbed-pane [args items]
  (let [ c (init-swing (JTabbedPane.) args)]
    (if (not (nil? items))
      (doseq [[title component] items]
        (.addTab c title component)))
    c))

(defn layered-pane [args items]
  (init-swing (JLayeredPane.) args items))

(defn tool-bar [args items]
  (init-swing (JToolBar.) args items))

(defn frame [args cp-items]
  (let [c (JFrame.)]
    (set-properties! c args)
    (if (not (nil? cp-items))
      (doseq [item cp-items]
        (.add (.getContentPane c) item)))
    c))

(defn window [args items]
  (init-swing (JLabel.) args items))

; standard dialogs
; TODO add frame parameter to the dialogs
(defn file-open-dialog [filename]
  "returns the filename to open or nil if the dialog was aborted"
  (let [d (JFileChooser. filename)]
    (let [state (.showOpenDialog d nil)]
      (if (= state JFileChooser/APPROVE_OPTION)
        (.getSelectedFile d)
        nil))))

(defn file-save-dialog [filename]
  "returns the filename to save or nil if the dialog was aborted"
  (let [d (JFileChooser. filename)]
    (let [state (.showSaveDialog d nil)]
      (if (= state JFileChooser/APPROVE_OPTION)
        (.getSelectedFile d)
        nil))))

(defn color-choose-dialog
  ([c title color]
    (JColorChooser/showDialog c title color)))

(defn dialog [args cp-items]
  "create a dialog"
  (let [c (JDialog.)]
    (set-properties! c args)
    (if (not (nil? cp-items))
      (doseq [item cp-items]
        (.add (.getContentPane c) item)))
    c))

(defn message-dialog
  "create a message dialog"
  ([text]
    (JOptionPane/showMessageDialog nil text))
  ([text title type]
    (JOptionPane/showMessageDialog
      nil text title (option-pane-message-keys type)))
  ([text title type icon]
    (JOptionPane/showMessageDialog
      nil text title (option-pane-message-keys type) icon)))

(defn confirm-dialog
  "create a confirm dialog"
  ([text title options]
    (JOptionPane/showConfirmDialog nil text title options))
  ([text title options type]
    (JOptionPane/showConfirmDialog
      nil text title options (option-pane-message-keys type)))
  ([text title options type icon]
    (JOptionPane/showConfirmDialog
      nil text title options (option-pane-message-keys type) icon)))

(defn input-dialog
  "create an input dialog"
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

(defn option-pane [args]
  "create an option pane dialog"
  (init-swing (JOptionPane.) args))

; mapseq ColumnSpec
;[{:label "Text" :key :text :edit false :converter function}]

; Default models
(defn mapseq-table-model [col-spec data]
  "creates a table model backed with a sequence of maps"
  (proxy [AbstractTableModel] []
    (getColumnCount [] (count col-spec))
    (getRowCount [] (count @data))
    (isCellEditable [_ col] (:edit (nth col-spec col) false))
    (getColumnName [col] (:label (nth col-spec col) (str "Label " col)))
    (getValueAt [row col] ((:converter (nth col-spec col) identity)
                            ((:key (nth col-spec col)) (nth @data row))))))

(defn seq-list-model [data]
  "creates a list model backed with a sequence"
  (proxy [AbstractListModel] []
    (getElementAt [idx] (nth @data idx))
    (getSize [] (count @data))))

; DefaultMutableTreeNode
(defn tree-node [obj args items]
  (init-swing (DefaultMutableTreeNode. obj) args items))

; MigLayout
(defn mig-layout [args]
  "creates a mig layout"
  (init-swing (MigLayout.) args))
