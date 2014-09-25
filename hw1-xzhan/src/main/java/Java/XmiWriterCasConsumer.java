/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package Java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.XMLSerializer;
import org.xml.sax.SAXException;

/**
 * A simple CAS consumer that writes the CAS to Text format.
 * <p>
 * This CAS Consumer takes one parameter:
 */
public class XmiWriterCasConsumer extends CasConsumer_ImplBase {

  /**
   * Processes the CAS which was populated by the TextAnalysisEngines. <br>
   * In this case, the CAS is converted to XMI and written into the output file .
   * 
   * @param aCAS
   *          a CAS which has been populated by the TAEs
   * 
   * @throws IOExcepiton
   *           if the path of doc is not valid
   * 
   * @see org.apache.uima.collection.base_cpm.CasObjectProcessor#processCas(org.apache.uima.cas.CAS)
   */
  public void processCas(CAS aCAS) throws ResourceProcessException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }
    
    
    FSIterator<Annotation> fs = jcas.getAnnotationIndex(WordAnalyser.type).iterator();
    
    // New a file to store result
    File outFile = null;
    try {
      outFile = new File("hw1-xzhan.out");
      BufferedWriter output = new BufferedWriter(new FileWriter(outFile));
      while(fs.hasNext()){
        String result = "";
        Annotation a = fs.next();
        WordAnalyser w = (WordAnalyser) a;
        result += w.getId();
        result += "|";
        result += w.getBegin();
        result += " ";
        result += w.getEnd();
        result += "|";
        result += w.getSentence();
        result += "\r\n";
        System.out.println(result);
        output.write(result);
      }
      
      output.close();
    } catch (IOException e) {
      // invalid URL, use default processing below
      e.printStackTrace();
    }

  }
}
