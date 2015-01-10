(ns org.soulspace.clj.java.print
  (:import [java.awt.print PrinterJob PageFormat Printable]
           [javax.print DocFlavor DocFlavor$BYTE_ARRAY DocFlavor$CHAR_ARRAY DocFlavor$INPUT_STREAM
                        DocFlavor$READER DocFlavor$SERVICE_FORMATTED DocFlavor$STRING DocFlavor$URL
                        PrintServiceLookup ServiceUI ServiceUIFactory SimpleDoc
                        StreamPrintService StreamPrintServiceFactory]
           [javax.print.attribute Attribute AttributeSet HashPrintRequestAttributeSet]
           [javax.print.attribute.standard MediaSize MediaSizeName])
  ;(:use [org.soulspace.clj.java.awt print])
  )

(def doc-flavors {:byte-array        DocFlavor$BYTE_ARRAY
                  :char-array        DocFlavor$CHAR_ARRAY
                  :input-stream      DocFlavor$INPUT_STREAM
                  :reader            DocFlavor$READER
                  :service-formatted DocFlavor$SERVICE_FORMATTED
                  :string            DocFlavor$STRING
                  :url               DocFlavor$URL})

(defn mime-type
  "Returns the mime type of the doc flavor."
  [doc-flavor]
  (.getMimeType doc-flavor))

(defn add-attribute
  "Adds the attribute to the attribute set."
  [attr-set attr]
  (.add attr-set attr))

(defn print-request-attribute-set
  "Creates a print request attribute set and adds the attributes if given."
  ([]
    (HashPrintRequestAttributeSet.))
  ([coll]
    (let [attr-set (print-request-attribute-set)]
      (doseq [attr coll]
        (add-attribute attr-set attr))
      attr-set)))

(defn lookup-stream-print-service-factories
  "Returns the stream print service factories."
  [doc-flavor mime-type]
  (StreamPrintServiceFactory/lookupStreamPrintServiceFactories doc-flavor mime-type))

(defn get-stream-print-service
  "Returns the stream print service from the stream print service factory for the output stream."
  [factory out]
  (.getPrintService factory out))

(defn lookup-default-print-service
  "Returns the default print service if it exists."
  []
  (PrintServiceLookup/lookupDefaultPrintService))

(defn lookup-print-services
  "Returns the print services compatible with the doc flavour and the attribute set."
  ([^javax.print.DocFlavor doc-flavor ^javax.print.attribute.AttributeSet attr-set]
    (PrintServiceLookup/lookupPrintServices doc-flavor attr-set)))

(defn print-dialog
  "Shows the print dialog and returns the print service. If no print service is returned, the print must be cancelled."
  ([x y print-services default-print-service attr-set]
    ; TODO lookup api signatures
    (ServiceUI/printDialog nil x y print-services default-print-service nil attr-set)))

(defn create-print-job
  "Creates a print job on the print service."
  [print-service]
  (.createPrintJob print-service))

(defn simple-doc
  "Creates a simple doc for printing."
  ([print-data ^javax.print.DocFlavor doc-flavor ^javax.print.attribute.AttributeSet attr-set]
    (SimpleDoc. print-data doc-flavor attr-set)))

(defn print-document
  "Print the document with the specified attribute set on the print job."
  [print-job doc attr-set]
  (.print print-job doc attr-set))

(defn print-data
  "Print the data with the specified attribute set on the print service."
  [print-service data doc-flavor attr-set]
  (let [print-job (create-print-job print-service)
        doc (simple-doc data doc-flavor attr-set)]
    (print-document print-job doc attr-set)))
