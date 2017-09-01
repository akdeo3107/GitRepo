package biz.neustar.page;
import java.beans.PropertyVetoException;
import java.lang.NullPointerException;
import java.io.*;
import java.util.ArrayList;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CreateOrder{
public static void main(String[] args) throws Exception{
try{	
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
Document doc = builder.parse(new File("E:\\workspace\\omsautomation-master-5f97d767fe946aee63adbc964302301da052c826\\ui-gateways\\repository\\testflows\\Create_Order.xml"));
Element rootElement = doc.getDocumentElement();
NodeList list = rootElement.getChildNodes();

int len = list.getLength();
ArrayList<String> value = new ArrayList<String>();

for(int i=1;i<=len;i++){
	Node childNode = list.item(i);
	value.add(childNode.getNodeName()+","+childNode.getFirstChild());
	for (int j=0;j<=value.size();j++){
		if (j==value.size()){
		System.out.println(value.get(j));
		}
		
	}
}

	
	
}


catch(Exception e){
	e.printStackTrace();
}
}
}

