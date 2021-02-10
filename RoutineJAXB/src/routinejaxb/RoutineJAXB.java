/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routinejaxb;

import custom.util.wrappers.CustomerWrapper;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author SharpSchnitzel
 */
public class RoutineJAXB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            Map<String, String> customer = new LinkedHashMap<>();
            List<Map<String, String>> customersList = new ArrayList<>();
            
            customer.put("last_name", "Korvin-Krukovskaya");
            customer.put("first_name", "Sofya");
            customer.put("middle_name", "Vasilyevna");
            customer.put("birth_date", "15-01-1850");
            customer.put("position", "Mathematician");
            
            customersList.add(new LinkedHashMap<>(customer));
            customer.clear();
            
            customer.put("last_name", "Washington");
            customer.put("first_name", "George");
            customer.put("birth_date", "30-04-1789");
            customer.put("position", "Mr. President");
            
            customersList.add(new LinkedHashMap<>(customer));
            customer.clear();
            
            CustomerWrapper customersObj = new CustomerWrapper(customersList);
            
            JAXBContext jc = JAXBContext.newInstance(CustomerWrapper.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println("[-- Object to XML...]\n");
            marshaller.marshal(customersObj, System.out);
            
            //#############################################################
            
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                            "<customers>" +
                                "<customer>" +
                                    "<last_name>Korvin-Krukovskaya</last_name>" +
                                    "<first_name>Sofya</first_name>" +
                                    "<middle_name>Vasilyevna</middle_name>" +
                                    "<birth_date>15-01-1850</birth_date>" +
                                    "<position>Mathematician</position>" +
                                "</customer>" +
                                "<customer>" +
                                    "<last_name>Washington</last_name>" +
                                    "<first_name>George</first_name>" +
                                    "<birth_date>30-04-1789</birth_date>" +
                                    "<position>Mr. President</position>" +
                                "</customer>" +
                                "<customer>" +
                                    "<last_name>Bonaparte</last_name>" +
                                    "<middle_name></middle_name>" +
                                    "<first_name>Napoléon</first_name>" +
                                    "<birth_date>15-08-1769</birth_date>" +
                                    "<position>Emperor</position>" +
                                "</customer>" +
                            "</customers>";
            
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            CustomerWrapper customers = (CustomerWrapper) unmarshaller.unmarshal(new StringReader(xml));
            System.out.println("\n[-- Vice versa...]\n");
            customers.printCustomers();
            
        } catch (JAXBException ex) {
            Logger.getLogger(RoutineJAXB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //[-- Object to XML...]
        //
        //<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
        //<customers>
        //    <customer>
        //        <last_name>Korvin-Krukovskaya</last_name>
        //        <first_name>Sofya</first_name>
        //        <middle_name>Vasilyevna</middle_name>
        //        <birth_date>15-01-1850</birth_date>
        //        <position>Mathematician</position>
        //    </customer>
        //    <customer>
        //        <last_name>Washington</last_name>
        //        <first_name>George</first_name>
        //        <birth_date>30-04-1789</birth_date>
        //        <position>Mr. President</position>
        //    </customer>
        //</customers>
        //
        //[-- Vice versa...]
        //
        //-- Customer #1 --
        //| Last name is Korvin-Krukovskaya
        //| First name is Sofya
        //| Middle name is Vasilyevna
        //| Birth date is 15-01-1850
        //| Position is Mathematician
        //------ EOL ------
        //
        //-- Customer #2 --
        //| Last name is Washington
        //| First name is George
        //| Birth date is 30-04-1789
        //| Position is Mr. President
        //------ EOL ------
        //
        //-- Customer #3 --
        //| Last name is Bonaparte
        //| First name is Napoléon
        //| Birth date is 15-08-1769
        //| Position is Emperor
        //------ EOL ------

    }
    
}
