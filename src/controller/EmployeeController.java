package controller;

import model.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import serverproxy.IServerProxy;
import serverproxy.ServerProxyFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {

    private List<Employee> niggas;

    public EmployeeController() {
        niggas = new ArrayList<>();
    }

    public List<Employee> readEmployees() {
        IServerProxy server = ServerProxyFactory.getInstance();

        Document doc = server.readXML(new File("src/xml/employee.xml"));

        Element element = doc.getDocumentElement();

        for (int i = 0; i < element.getChildNodes().getLength(); i++) {
            Node node = element.getChildNodes().item(i);

            if (!node.getNodeName().equals("#text")) {
                Employee e = getEmployee(node);

                niggas.add(e);
            }
        }
        return niggas;
    }

    public Employee getEmployee(Node node){
        Employee e = new Employee();
        NodeList props = node.getChildNodes();

        for (int j = 0; j < props.getLength(); j++) {
            Node prop = props.item(j);


            if (!prop.getNodeName().equals("#text")) {
                String propName = prop.getNodeName();
                String propValue = prop.getTextContent();
                        /*System.out.println("propName: " + propName);
                        System.out.println("propValue: " + propValue);*/

                switch (propName) {
                    case "name":
                        e.setName(propValue);
                        break;
                    case "age":
                        e.setAge(Integer.parseInt(propValue));
                        break;
                    case "role":
                        e.setRole(propValue);
                        break;
                    case "gender":
                        e.setGender(propValue);
                }
            }
        }
        return e;
    }
}
