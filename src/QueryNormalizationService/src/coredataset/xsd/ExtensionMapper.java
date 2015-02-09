
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package coredataset.xsd;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://CoreDataset/xsd".equals(namespaceURI) &&
                  "classifiedRelationship".equals(typeName)){
                   
                            return  coredataset.xsd.ClassifiedRelationship.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://CoreDataset/xsd".equals(namespaceURI) &&
                  "kinshipConcepts".equals(typeName)){
                   
                            return  coredataset.xsd.KinshipConcepts.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://CoreDataset/xsd".equals(namespaceURI) &&
                  "normalizedConcept".equals(typeName)){
                   
                            return  coredataset.xsd.NormalizedConcept.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://CoreDataset/xsd".equals(namespaceURI) &&
                  "entityRelationship".equals(typeName)){
                   
                            return  coredataset.xsd.EntityRelationship.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://CoreDataset/xsd".equals(namespaceURI) &&
                  "Concept".equals(typeName)){
                   
                            return  coredataset.xsd.Concept.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://CoreDataset/xsd".equals(namespaceURI) &&
                  "classifiedConcept".equals(typeName)){
                   
                            return  coredataset.xsd.ClassifiedConcept.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://NormalForm/xsd".equals(namespaceURI) &&
                  "NormalizedExpression".equals(typeName)){
                   
                            return  normalform.xsd.NormalizedExpression.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.openrdf.org/xsd".equals(namespaceURI) &&
                  "Value".equals(typeName)){
                   
                            return  org.openrdf.model.xsd.Value.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://NormalForm/xsd".equals(namespaceURI) &&
                  "SnomedRelationship".equals(typeName)){
                   
                            return  normalform.xsd.SnomedRelationship.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    