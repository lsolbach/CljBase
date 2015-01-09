(ns org.soulspace.clj.java.print
  (:import [java.awt.print PrinterJob PageFormat Printable]
           [javax.print DocFlavor DocFlavor$BYTE_ARRAY DocFlavor$CHAR_ARRAY DocFlavor$INPUT_STREAM
                        DocFlavor$READER DocFlavor$SERVICE_FORMATTED DocFlavor$STRING DocFlavor$URL
                        PrintServiceLookup ServiceUI ServiceUIFactory SimpleDoc
                        StreamPrintService StreamPrintServiceFactory]
           [javax.print.attribute Attribute AttributeSet]
           [javax.print.attribute.standard MediaSize])
  ;(:use [org.soulspace.clj.java.awt print])
  )

(def doc-flavors {:byte-array        DocFlavor$BYTE_ARRAY
                  :char-array        DocFlavor$CHAR_ARRAY
                  :input-stream      DocFlavor$INPUT_STREAM
                  :reader            DocFlavor$READER
                  :service-formatted DocFlavor$SERVICE_FORMATTED
                  :string            DocFlavor$STRING
                  :url               DocFlavor$URL})

(defn lookup-print-services
  "Returns the print services compatible with the doc flavour and the attribute set."
  ([^javax.print.DocFlavor doc-flavor ^javax.print.attribute.AttributeSet attr-set]
    (PrintServiceLookup/lookupPrintServices doc-flavor attr-set)))

(defn create-print-job
  "Creates a print job on the print service."
  [print-service]
  (.createPrintJob print-service))

(defn simple-doc
  "Creates a simple doc for printing."
  ([print-data ^javax.print.DocFlavor doc-flavor ^javax.print.attribute.AttributeSet attr-set]
    (SimpleDoc. print-data doc-flavor attr-set)))

