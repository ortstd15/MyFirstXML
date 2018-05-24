package controller;

import model.Employee;
import org.w3c.dom.*;
import serverproxy.IServerProxy;
import serverproxy.ServerProxyFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void addEmployee(Employee emp) {
        try {
            Document doc;
            IServerProxy server = ServerProxyFactory.getInstance();

            doc = server.readXML(new File("employee.xml"));

            Element employee = doc.createElement("Employee");
            Element name = doc.createElement("name");
            Text nameT = doc.createTextNode(emp.getName());
            Element age = doc.createElement("age");
            Text ageT = doc.createTextNode(emp.getAge() + "");
            Element role = doc.createElement("role");
            Text roleT = doc.createTextNode(emp.getRole());
            Element gender = doc.createElement("gender");
            Text genderT = doc.createTextNode(emp.getGender());

            ((Element) doc.getElementsByTagName("Employees").item(0)).appendChild(employee);
            employee.appendChild(name);
            employee.appendChild(age);
            employee.appendChild(role);
            employee.appendChild(gender);
            name.appendChild(nameT);
            age.appendChild(ageT);
            role.appendChild(roleT);
            gender.appendChild(genderT);

            DOMSource source = new DOMSource(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult("employee.xml");
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(
                    EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
