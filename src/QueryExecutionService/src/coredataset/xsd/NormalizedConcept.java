
/**
 * NormalizedConcept.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package coredataset.xsd;
            

            /**
            *  NormalizedConcept bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class NormalizedConcept
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = normalizedConcept
                Namespace URI = http://CoreDataset/xsd
                Namespace Prefix = ns3
                */
            

                        /**
                        * field for JSON
                        */

                        
                                    protected java.lang.String localJSON ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localJSONTracker = false ;

                           public boolean isJSONSpecified(){
                               return localJSONTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getJSON(){
                               return localJSON;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param JSON
                               */
                               public void setJSON(java.lang.String param){
                            localJSONTracker = true;
                                   
                                            this.localJSON=param;
                                    

                               }
                            

                        /**
                        * field for Alternatives
                        * This was an Array!
                        */

                        
                                    protected coredataset.xsd.ClassifiedConcept[] localAlternatives ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAlternativesTracker = false ;

                           public boolean isAlternativesSpecified(){
                               return localAlternativesTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return coredataset.xsd.ClassifiedConcept[]
                           */
                           public  coredataset.xsd.ClassifiedConcept[] getAlternatives(){
                               return localAlternatives;
                           }

                           
                        


                               
                              /**
                               * validate the array for Alternatives
                               */
                              protected void validateAlternatives(coredataset.xsd.ClassifiedConcept[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Alternatives
                              */
                              public void setAlternatives(coredataset.xsd.ClassifiedConcept[] param){
                              
                                   validateAlternatives(param);

                               localAlternativesTracker = true;
                                      
                                      this.localAlternatives=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param coredataset.xsd.ClassifiedConcept
                             */
                             public void addAlternatives(coredataset.xsd.ClassifiedConcept param){
                                   if (localAlternatives == null){
                                   localAlternatives = new coredataset.xsd.ClassifiedConcept[]{};
                                   }

                            
                                 //update the setting tracker
                                localAlternativesTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localAlternatives);
                               list.add(param);
                               this.localAlternatives =
                             (coredataset.xsd.ClassifiedConcept[])list.toArray(
                            new coredataset.xsd.ClassifiedConcept[list.size()]);

                             }
                             

                        /**
                        * field for ClassCode
                        */

                        
                                    protected java.lang.String localClassCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localClassCodeTracker = false ;

                           public boolean isClassCodeSpecified(){
                               return localClassCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getClassCode(){
                               return localClassCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ClassCode
                               */
                               public void setClassCode(java.lang.String param){
                            localClassCodeTracker = true;
                                   
                                            this.localClassCode=param;
                                    

                               }
                            

                        /**
                        * field for Code
                        */

                        
                                    protected coredataset.xsd.ClassifiedConcept localCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCodeTracker = false ;

                           public boolean isCodeSpecified(){
                               return localCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return coredataset.xsd.ClassifiedConcept
                           */
                           public  coredataset.xsd.ClassifiedConcept getCode(){
                               return localCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Code
                               */
                               public void setCode(coredataset.xsd.ClassifiedConcept param){
                            localCodeTracker = true;
                                   
                                            this.localCode=param;
                                    

                               }
                            

                        /**
                        * field for CodeOrig
                        */

                        
                                    protected java.lang.String localCodeOrig ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCodeOrigTracker = false ;

                           public boolean isCodeOrigSpecified(){
                               return localCodeOrigTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCodeOrig(){
                               return localCodeOrig;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CodeOrig
                               */
                               public void setCodeOrig(java.lang.String param){
                            localCodeOrigTracker = true;
                                   
                                            this.localCodeOrig=param;
                                    

                               }
                            

                        /**
                        * field for Entities
                        * This was an Array!
                        */

                        
                                    protected coredataset.xsd.EntityRelationship[] localEntities ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEntitiesTracker = false ;

                           public boolean isEntitiesSpecified(){
                               return localEntitiesTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return coredataset.xsd.EntityRelationship[]
                           */
                           public  coredataset.xsd.EntityRelationship[] getEntities(){
                               return localEntities;
                           }

                           
                        


                               
                              /**
                               * validate the array for Entities
                               */
                              protected void validateEntities(coredataset.xsd.EntityRelationship[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Entities
                              */
                              public void setEntities(coredataset.xsd.EntityRelationship[] param){
                              
                                   validateEntities(param);

                               localEntitiesTracker = true;
                                      
                                      this.localEntities=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param coredataset.xsd.EntityRelationship
                             */
                             public void addEntities(coredataset.xsd.EntityRelationship param){
                                   if (localEntities == null){
                                   localEntities = new coredataset.xsd.EntityRelationship[]{};
                                   }

                            
                                 //update the setting tracker
                                localEntitiesTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localEntities);
                               list.add(param);
                               this.localEntities =
                             (coredataset.xsd.EntityRelationship[])list.toArray(
                            new coredataset.xsd.EntityRelationship[list.size()]);

                             }
                             

                        /**
                        * field for RelationshipPair
                        * This was an Array!
                        */

                        
                                    protected coredataset.xsd.ClassifiedRelationship[] localRelationshipPair ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localRelationshipPairTracker = false ;

                           public boolean isRelationshipPairSpecified(){
                               return localRelationshipPairTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return coredataset.xsd.ClassifiedRelationship[]
                           */
                           public  coredataset.xsd.ClassifiedRelationship[] getRelationshipPair(){
                               return localRelationshipPair;
                           }

                           
                        


                               
                              /**
                               * validate the array for RelationshipPair
                               */
                              protected void validateRelationshipPair(coredataset.xsd.ClassifiedRelationship[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param RelationshipPair
                              */
                              public void setRelationshipPair(coredataset.xsd.ClassifiedRelationship[] param){
                              
                                   validateRelationshipPair(param);

                               localRelationshipPairTracker = true;
                                      
                                      this.localRelationshipPair=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param coredataset.xsd.ClassifiedRelationship
                             */
                             public void addRelationshipPair(coredataset.xsd.ClassifiedRelationship param){
                                   if (localRelationshipPair == null){
                                   localRelationshipPair = new coredataset.xsd.ClassifiedRelationship[]{};
                                   }

                            
                                 //update the setting tracker
                                localRelationshipPairTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localRelationshipPair);
                               list.add(param);
                               this.localRelationshipPair =
                             (coredataset.xsd.ClassifiedRelationship[])list.toArray(
                            new coredataset.xsd.ClassifiedRelationship[list.size()]);

                             }
                             

                        /**
                        * field for Text
                        */

                        
                                    protected java.lang.String localText ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTextTracker = false ;

                           public boolean isTextSpecified(){
                               return localTextTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getText(){
                               return localText;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Text
                               */
                               public void setText(java.lang.String param){
                            localTextTracker = true;
                                   
                                            this.localText=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://CoreDataset/xsd");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":normalizedConcept",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "normalizedConcept",
                           xmlWriter);
                   }

               
                   }
                if (localJSONTracker){
                                    namespace = "http://CoreDataset/xsd";
                                    writeStartElement(null, namespace, "JSON", xmlWriter);
                             

                                          if (localJSON==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localJSON);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAlternativesTracker){
                                       if (localAlternatives!=null){
                                            for (int i = 0;i < localAlternatives.length;i++){
                                                if (localAlternatives[i] != null){
                                                 localAlternatives[i].serialize(new javax.xml.namespace.QName("http://CoreDataset/xsd","alternatives"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                            writeStartElement(null, "http://CoreDataset/xsd", "alternatives", xmlWriter);

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                writeStartElement(null, "http://CoreDataset/xsd", "alternatives", xmlWriter);

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 } if (localClassCodeTracker){
                                    namespace = "http://CoreDataset/xsd";
                                    writeStartElement(null, namespace, "classCode", xmlWriter);
                             

                                          if (localClassCode==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localClassCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCodeTracker){
                                    if (localCode==null){

                                        writeStartElement(null, "http://CoreDataset/xsd", "code", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localCode.serialize(new javax.xml.namespace.QName("http://CoreDataset/xsd","code"),
                                        xmlWriter);
                                    }
                                } if (localCodeOrigTracker){
                                    namespace = "http://CoreDataset/xsd";
                                    writeStartElement(null, namespace, "codeOrig", xmlWriter);
                             

                                          if (localCodeOrig==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCodeOrig);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localEntitiesTracker){
                                       if (localEntities!=null){
                                            for (int i = 0;i < localEntities.length;i++){
                                                if (localEntities[i] != null){
                                                 localEntities[i].serialize(new javax.xml.namespace.QName("http://CoreDataset/xsd","entities"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                            writeStartElement(null, "http://CoreDataset/xsd", "entities", xmlWriter);

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                writeStartElement(null, "http://CoreDataset/xsd", "entities", xmlWriter);

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 } if (localRelationshipPairTracker){
                                       if (localRelationshipPair!=null){
                                            for (int i = 0;i < localRelationshipPair.length;i++){
                                                if (localRelationshipPair[i] != null){
                                                 localRelationshipPair[i].serialize(new javax.xml.namespace.QName("http://CoreDataset/xsd","relationshipPair"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                            writeStartElement(null, "http://CoreDataset/xsd", "relationshipPair", xmlWriter);

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                writeStartElement(null, "http://CoreDataset/xsd", "relationshipPair", xmlWriter);

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 } if (localTextTracker){
                                    namespace = "http://CoreDataset/xsd";
                                    writeStartElement(null, namespace, "text", xmlWriter);
                             

                                          if (localText==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localText);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://CoreDataset/xsd")){
                return "ns3";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localJSONTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                      "JSON"));
                                 
                                         elementList.add(localJSON==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localJSON));
                                    } if (localAlternativesTracker){
                             if (localAlternatives!=null) {
                                 for (int i = 0;i < localAlternatives.length;i++){

                                    if (localAlternatives[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                          "alternatives"));
                                         elementList.add(localAlternatives[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                          "alternatives"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                          "alternatives"));
                                        elementList.add(localAlternatives);
                                    
                             }

                        } if (localClassCodeTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                      "classCode"));
                                 
                                         elementList.add(localClassCode==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localClassCode));
                                    } if (localCodeTracker){
                            elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                      "code"));
                            
                            
                                    elementList.add(localCode==null?null:
                                    localCode);
                                } if (localCodeOrigTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                      "codeOrig"));
                                 
                                         elementList.add(localCodeOrig==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodeOrig));
                                    } if (localEntitiesTracker){
                             if (localEntities!=null) {
                                 for (int i = 0;i < localEntities.length;i++){

                                    if (localEntities[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                          "entities"));
                                         elementList.add(localEntities[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                          "entities"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                          "entities"));
                                        elementList.add(localEntities);
                                    
                             }

                        } if (localRelationshipPairTracker){
                             if (localRelationshipPair!=null) {
                                 for (int i = 0;i < localRelationshipPair.length;i++){

                                    if (localRelationshipPair[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                          "relationshipPair"));
                                         elementList.add(localRelationshipPair[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                          "relationshipPair"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                          "relationshipPair"));
                                        elementList.add(localRelationshipPair);
                                    
                             }

                        } if (localTextTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://CoreDataset/xsd",
                                                                      "text"));
                                 
                                         elementList.add(localText==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localText));
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static NormalizedConcept parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            NormalizedConcept object =
                new NormalizedConcept();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"normalizedConcept".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (NormalizedConcept)coredataset.xsd.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list2 = new java.util.ArrayList();
                    
                        java.util.ArrayList list6 = new java.util.ArrayList();
                    
                        java.util.ArrayList list7 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://CoreDataset/xsd","JSON").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setJSON(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://CoreDataset/xsd","alternatives").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list2.add(null);
                                                              reader.next();
                                                          } else {
                                                        list2.add(coredataset.xsd.ClassifiedConcept.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone2 = false;
                                                        while(!loopDone2){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone2 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://CoreDataset/xsd","alternatives").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list2.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list2.add(coredataset.xsd.ClassifiedConcept.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone2 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setAlternatives((coredataset.xsd.ClassifiedConcept[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                coredataset.xsd.ClassifiedConcept.class,
                                                                list2));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://CoreDataset/xsd","classCode").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setClassCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://CoreDataset/xsd","code").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setCode(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setCode(coredataset.xsd.ClassifiedConcept.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://CoreDataset/xsd","codeOrig").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCodeOrig(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://CoreDataset/xsd","entities").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list6.add(null);
                                                              reader.next();
                                                          } else {
                                                        list6.add(coredataset.xsd.EntityRelationship.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone6 = false;
                                                        while(!loopDone6){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone6 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://CoreDataset/xsd","entities").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list6.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list6.add(coredataset.xsd.EntityRelationship.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone6 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setEntities((coredataset.xsd.EntityRelationship[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                coredataset.xsd.EntityRelationship.class,
                                                                list6));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://CoreDataset/xsd","relationshipPair").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list7.add(null);
                                                              reader.next();
                                                          } else {
                                                        list7.add(coredataset.xsd.ClassifiedRelationship.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone7 = false;
                                                        while(!loopDone7){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone7 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://CoreDataset/xsd","relationshipPair").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list7.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list7.add(coredataset.xsd.ClassifiedRelationship.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone7 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setRelationshipPair((coredataset.xsd.ClassifiedRelationship[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                coredataset.xsd.ClassifiedRelationship.class,
                                                                list7));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://CoreDataset/xsd","text").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setText(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    