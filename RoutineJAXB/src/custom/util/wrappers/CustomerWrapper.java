/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom.util.wrappers;

import custom.util.adapters.MapAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author SharpSchnitzel
 */

@XmlRootElement(name="customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerWrapper {
    
    @XmlElement(name = "customer")
    @XmlJavaTypeAdapter(MapAdapter.class)
    private final List<Map<String, String>> list;

    public CustomerWrapper() {
        this.list = new ArrayList<>();
    }

    public CustomerWrapper(Map<String, String> value) {
        this();
        this.list.add(value);
    }
    
    public CustomerWrapper(List<Map<String, String>> value) {
        this();
        value.forEach(_item -> {
           this.list.add(_item); 
        });
    }
    
    public void printCustomers() {
        IntStream.range(0, this.list.size()).forEach(_idx -> {
            System.out.println("-- Customer #" + (_idx + 1) + " --");
            this.list.get(_idx).entrySet().forEach(_entry -> {
                System.out.println("| " + _entry.getKey().substring(0,1).toUpperCase() + _entry.getKey().substring(1).replaceAll("_", " ") + " is " + _entry.getValue());
            });
            System.out.println("------ EOL ------\n");
        });
    }
    
    public List<Map<String, String>> getList() {
        return this.list;
    }

}
