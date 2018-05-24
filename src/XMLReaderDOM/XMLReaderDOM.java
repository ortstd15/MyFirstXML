package XMLReaderDOM;


import controller.EmployeeController;
import model.Employee;

public class XMLReaderDOM {


    public static void main(String[] args) {

        EmployeeController controller = new EmployeeController();



        for (Employee e : controller.readEmployees()) {
            System.out.println(e);
        }


    }
}
