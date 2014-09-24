

/* First created by JCasGen Mon Sep 22 22:38:08 EDT 2014 */
package Java;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Sep 23 15:34:09 EDT 2014
 * XML source: /Users/lubron/git/hw1-xzhan /hw1-xzhan/src/main/resources/Descriptor/roomNumberDescriptor.xml
 * @generated */
public class WordAnalyser extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(WordAnalyser.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected WordAnalyser() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public WordAnalyser(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public WordAnalyser(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public WordAnalyser(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: Id

  /** getter for Id - gets 
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (WordAnalyser_Type.featOkTst && ((WordAnalyser_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "Java.WordAnalyser");
    return jcasType.ll_cas.ll_getStringValue(addr, ((WordAnalyser_Type)jcasType).casFeatCode_Id);}
    
  /** setter for Id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (WordAnalyser_Type.featOkTst && ((WordAnalyser_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "Java.WordAnalyser");
    jcasType.ll_cas.ll_setStringValue(addr, ((WordAnalyser_Type)jcasType).casFeatCode_Id, v);}    
   
    
  //*--------------*
  //* Feature: Sentence

  /** getter for Sentence - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSentence() {
    if (WordAnalyser_Type.featOkTst && ((WordAnalyser_Type)jcasType).casFeat_Sentence == null)
      jcasType.jcas.throwFeatMissing("Sentence", "Java.WordAnalyser");
    return jcasType.ll_cas.ll_getStringValue(addr, ((WordAnalyser_Type)jcasType).casFeatCode_Sentence);}
    
  /** setter for Sentence - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentence(String v) {
    if (WordAnalyser_Type.featOkTst && ((WordAnalyser_Type)jcasType).casFeat_Sentence == null)
      jcasType.jcas.throwFeatMissing("Sentence", "Java.WordAnalyser");
    jcasType.ll_cas.ll_setStringValue(addr, ((WordAnalyser_Type)jcasType).casFeatCode_Sentence, v);}    
  }

    