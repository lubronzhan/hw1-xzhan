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

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.util.AbstractExternalizable;

import Java.WordAnalyser;

/**
 * using lingpipe HMMChunker model to check whether targets are genetag words.
 */
public class RoomNumberAnnotator extends JCasAnnotator_ImplBase {

  /**
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas aJCas) {
    // get document text
    String docText = aJCas.getDocumentText();
    // search for Yorktown room numbers
    

    WordAnalyser word = null;
    try{

      // Analyze Line by line
      String [] textStr = docText.split("\n");
      int length = textStr.length;
      // lingpipe
      Chunking chunking = null;
      
      // Read HHM model 
      File modelFile = new File("src/main/resources/descriptor/ner/ne-en-bio-genetag-2.HmmChunker");
      // model instance
      Chunker chunker
      = (Chunker) AbstractExternalizable.readObject(modelFile);
      
      // Analyse line info line by line
      for(int i = 0; i < length; i++){
        // Line number;
        // Cut the 
        String id = textStr[i].substring(0, 14);
        // Content after line number
        String line = textStr[i].substring(15, textStr[i].length());
        
        // Serialize chunk
        chunking = chunker.chunk(line);
        // Iterate chunking to get start and end
        Iterator<Chunk> iter = chunking.chunkSet().iterator();
        // Eliminate the space before each word
        int spaceIn = 0; 
        int spaceBefore = 0;
        // Put each genetag word into annotation
        while(iter.hasNext()){// Check whether exist
          // get next Chunk
          Chunk entry = iter.next();
          int key = entry.start();
          int value = entry.end();
          
          // count the number of space in sentence.
          
          String [] wordDivide = line.substring(key, value).split("\\s");
          spaceIn = wordDivide.length - 1;
          
          // and before sentence
          if(key == 0){
            spaceBefore = 0;
          }
          else{
            String [] beforeDivide = line.substring(0, key + 2).split("\\s");
            spaceBefore = beforeDivide.length - 1;
          }
          
          
          // build annotation and add to each instance of WordAnalyser
          WordAnalyser annotation = new WordAnalyser(aJCas);
          
          
          
          // value : the index after the word
          annotation.setBegin(key - spaceBefore);
          annotation.setEnd(value - 1 - spaceIn - spaceBefore);
          annotation.setId(id);
          annotation.setSentence(line.substring(key, value)); 
          annotation.addToIndexes();

        }
  
//      for(int i = 0; i < length; i++){
//        // Line number;
//        String id = textStr[i].substring(0, 13);
//        // Content after line number
//        String line = textStr[i].substring(15, textStr[i].length()-1);
//        // input content in each line into Stanford map
//        Map<Integer,Integer> map = pos.getGeneSpans(line);        
//        // to iterate through map to get each word
//        Iterator entries =  map.entrySet().iterator();   
//
//        while(entries.hasNext()){
//          Map.Entry<Integer, Integer> entry = (Entry<Integer, Integer>) entries.next();
//          int key = entry.getKey();
//          int value = entry.getValue();
//          WordAnalyser annotation = new WordAnalyser(aJCas);
//          annotation.setBegin(key);
//          annotation.setEnd(value);
//          annotation.setId(id);
//          annotation.setSentence(line.substring(key, value)); 
//          annotation.addToIndexes();
//        }
        }  
      }catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (ClassNotFoundException e) { // Hmm model
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
  
  }

}
