(ns org.soulspace.clj.java.print
  (:import [java.awt.print PrinterJob PageFormat Printable]
           [javax.print DocFlavour PrintServiceLookup ServiceUI ServiceUIFactory SimpleDoc
                        StreamPrintService StreamPrintServiceFactory]
           [javax.print.attribute Attribute AttributeSet]
           [javax.print.attribute.standard MediaSize])
  (:use [org.soulspace.clj.java.awt print]))

(def doc-flavours {:byte-array        DocFlavour/BYTE_ARRAY
                   :char-array        DocFlavour/CHAR_ARRAY
                   :input-stream      DocFlavour/INPUT_STREAM
                   :reader            DocFlavour/READER
                   :service-formatted DocFlavour/SERVICE_FORMATTED
                   :string            DocFlavour/STRING
                   :url               DocFlavour/URL})

(defn lookup-print-services
  "Returns the print services compatible with the doc flavour and the attribute set."
  ([doc-flavour attr-set]
    (PrintServiceLookup/lookupPrintServices doc-flavour attr-set)))

(defn create-print-job
  "Creates a print job on the print service."
  [print-service]
  (.createPrintJob print-service))

(defn simple-doc
  "Creates a simple doc for printing."
  ([print-data doc-flavour attr-set]
    (SimpleDoc. print-data doc-flavour attr-set)))

