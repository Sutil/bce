package br.com.biblioteca.app.login;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class ReaderProperties {
	
	private String property;

	private ReaderProperties(String property) {
		this.property = property;
	}
	
	public static ReaderProperties newInstance(String property){
		checkArgument(!Strings.isNullOrEmpty(property));
		return new ReaderProperties(property);
	}
	
	public List<String> getListFiles(){
		List<String> paths = Lists.newLinkedList();
		String path = "";
		try {
			path = getPath();
			File folder = new File(path);
			System.out.println(path);
			System.out.println("pasta? "+folder.isDirectory());
			
			String[] list = folder.list();
			
			for(String s : list){
				paths.add(String.format("%s/%s", path, s));
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Não foi possível ler os arquivos desta propriedade");
			
		}
		return paths;
		
	}
	
	
	public String getPath() throws ParserConfigurationException, SAXException, IOException{
		Element job = getProperty();
		return job.getElementsByTagName("path").item(0).getTextContent();
	}
	
	
	private Element getProperty() throws ParserConfigurationException, SAXException, IOException{

		System.out.println("lendo arquivo");
		Document xml = readXml();

		System.out.println("pegando propriedades");
		NodeList jobs = xml.getElementsByTagName("property");
		
		for(int i = 0 ; i < jobs.getLength()  ; i++){
			Node job = jobs.item(i);
			if(job.getNodeType() == Node.ELEMENT_NODE){
				Element elementJob = (Element) job;
				String name = elementJob.getAttribute("id");
				if(property.equals(name)){
					return elementJob;
				} 
			}
		}
		return null;
	}
	
	private Document readXml() throws ParserConfigurationException, SAXException, IOException{
		File fXmlFile = new File("src/main/resources/configPath.xml"); 
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		return dBuilder.parse(fXmlFile);
	}
}
