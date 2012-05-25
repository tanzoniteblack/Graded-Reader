//package github.graded_reader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Text {
	/*
	 * Chapter holds the information actually needed about a chapter including title name, chapter number
	 * sentences, translations, and grammar information
	 */
	public class Chapter {
		private String title;
		private int chapNum;
		private String grammar;
		private String vocab;
		private ArrayList<ArrayList<Sentence>> paragraphs;
		
		public Chapter() {
			paragraphs = new ArrayList<ArrayList<Sentence>>();
		}
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getChapNum() {
			return chapNum;
		}
		public void setChapNum(int chapNum) {
			this.chapNum = chapNum;
		}

		public String getGrammar() {
			return grammar;
		}

		public void setGrammar(String grammar) {
			this.grammar = grammar;
		}

		public String getVocab() {
			return vocab;
		}

		public void setVocab(String vocab) {
			this.vocab = vocab;
		}
		
		public void addParagraph(ArrayList<Sentence> paragraph) {
			paragraphs.add(paragraph);
		}
		
		public int getParagraphCount() {
			return paragraphs.size();
		}
	}
	
	/*
	 * Class to hold translations of a sentence, as well as an ArrayList of the words in it
	 */
	public class Sentence {
		String sentenceTranslation;
		ArrayList<Word> words;
		
		public Sentence () {
			words = new ArrayList<Word>();
		}
		
		public void addWord(Word word) {
			words.add(word);
		}

		public String getSentenceTranslation() {
			return sentenceTranslation;
		}

		public void setSentenceTranslation(String sentenceTranslation) {
			this.sentenceTranslation = sentenceTranslation;
		}
	}
	
	/*
	 * Class to hold translations and originals of a word
	 */
	public class Word {
		String wordText;
		String wordTranslation;
		
		public Word() {
			wordText = "";
			wordTranslation = "";
		}

		public String getWordText() {
			return wordText;
		}

		public void setWordText(String wordText) {
			this.wordText = wordText;
		}

		public String getWordTranslation() {
			return wordTranslation;
		}

		public void setWordTranslation(String wordTranslation) {
			this.wordTranslation = wordTranslation;
		}
		
		
	}
	
	private ArrayList<Chapter> chapters = new ArrayList<Chapter>();
	
	public Text (File xmlFile) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList chapterList = doc.getElementsByTagName("chapter");
			
			for (int temp = 0; temp < chapterList.getLength(); temp++) {
				Node nNode = chapterList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Chapter chap = new Chapter();
					
					NamedNodeMap attributes = nNode.getAttributes();
					chap.setChapNum(new Integer(attributes.getNamedItem("num").getTextContent()));
					chap.setTitle(attributes.getNamedItem("title").getTextContent());
					
					NodeList childNodes = eElement.getChildNodes();

					for (int topNodes = 0; topNodes < childNodes.getLength(); topNodes++) {
						Node topNode = childNodes.item(topNodes);
						if (topNode.getNodeType() == Node.ELEMENT_NODE) {
							Element childNode = (Element) topNode;
							
							// only 1 grammar node per chapter, find it and set it
							if (childNode.getNodeName().equals("grammar")) {
								chap.setGrammar(childNode.getTextContent());
							}
							
							// only 1 vocab list per chapter, find it and set it
							if (childNode.getNodeName().equals("vocab")) {
								chap.setVocab(childNode.getTextContent());
								System.out.println(chap.getVocab());
							}
							
							// unlimited paragraph nodes per chapter, find them in order and add them
							if (childNode.getNodeName().equals("paragraph")) {
								NodeList sentences = childNode.getChildNodes();
								ArrayList<Sentence> paragraph = new ArrayList<Sentence>();
								
								// cycle through sentence nodes
								for (int sentNum = 0; sentNum < sentences.getLength(); sentNum++) {
									Node sentNode = sentences.item(sentNum);
									if (sentNode.getNodeType() == Node.ELEMENT_NODE) {
										Element sentence = (Element) sentNode;
										Sentence sent = new Sentence();
										
										// cycle through word and translation nodes
										NodeList sentChildren = sentence.getChildNodes();
										for (int wordNum = 0; wordNum < sentChildren.getLength(); wordNum++) {
											Node wordNode = sentChildren.item(wordNum);
											if (wordNode.getNodeType() == Node.ELEMENT_NODE) {
												Element word = (Element) wordNode;
												// if this is a translation node, set the sentence translation
												if (word.getNodeName().equals("trans")) {
													sent.setSentenceTranslation(word.getTextContent());
												}
												
												// if this is a word node, create a word object and put in sentence
												if (word.getNodeName().equals("word")) {
													Word newWord = new Word();
													
													// go through children nodes of word
													NodeList wordChildren = word.getChildNodes();
													for (int wordChildNum = 0; wordChildNum < wordChildren.getLength(); wordChildNum++) {
														Node l1Node = wordChildren.item(wordChildNum);
														if (l1Node.getNodeType() == Node.ELEMENT_NODE) {
															Element l1 = (Element) l1Node;
															// set l1
															if (l1.getNodeName().equals("l1")) {
																newWord.setWordText(l1.getTextContent());
															}
															// set l2
															if (l1.getNodeName().equals("l2")) {
																newWord.setWordTranslation(l1.getTextContent());
															}
														}
													}
													// add this word to the sentence
													sent.addWord(newWord);
												}
											}
										}
										paragraph.add(sent);
									}
								}
								chap.addParagraph(paragraph);
							}
						}
					}
					chapters.add(chap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Chapter> getChapters() {
		return chapters;
	}
	
	public static void main(String[] args) {
		try {
			File xmlFile = new File("karlo.xml");
			Text reader = new Text(xmlFile);
			ArrayList<Chapter> karloChapt = reader.getChapters();
			
			for (Chapter chap : karloChapt) {
				System.out.println(chap.getParagraphCount());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
