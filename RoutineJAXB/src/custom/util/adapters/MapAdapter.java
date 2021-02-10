/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom.util.adapters;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author SharpSchnitzel
 */
public class MapAdapter extends XmlAdapter<MapAdapter.AdaptedMap, Map<String, String>> {
    private final DocumentBuilder docBuilder;

    public MapAdapter() throws Exception {
        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
    
    public static class AdaptedMap {        
        @XmlAnyElement
        public List<Element> elements = new ArrayList<Element>();
    }

    @Override
    public AdaptedMap marshal(Map<String, String> map) throws Exception {
        Document document = docBuilder.newDocument();
        AdaptedMap adaptedMap = new AdaptedMap();
        map.entrySet().stream().forEach(entry -> {
            Element element = document.createElement(entry.getKey());
            element.setTextContent(entry.getValue());
            adaptedMap.elements.add(element);
        });
        return adaptedMap;
    }

    @Override
    public Map<String, String> unmarshal(AdaptedMap adaptedMap) throws Exception {
        Map<String, String> map = new LinkedHashMap<>();
        adaptedMap.elements.forEach(element -> {
            if (!element.getTextContent().isEmpty()) {
               map.put(element.getLocalName(), element.getTextContent()); 
            }
        });
        return map;
    }
}
