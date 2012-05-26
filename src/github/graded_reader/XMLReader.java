package github.graded_reader;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XMLReader {
	/*
	 * Chapter holds the information actually needed about a chapter including title name, chapter number
	 * sentences, translations, and grammar information
	 */
	public class Chapter {
		private String title;
		private int chapNum;
		private String grammar;
		private String vocab;
		
		public Chapter() {
			
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
	}
	
	public XMLReader (File xmlFile) {
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
								
							}
						}
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			File xmlFile = new File("karlo.xml");
			XMLReader reader = new XMLReader(xmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
