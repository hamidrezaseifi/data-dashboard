package de.mediqon.generic.data_dashboard;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

public class TestApp {

    public static void main(String[] args) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        String xml = FileUtils.readFileToString(new File("C:\\Seifi\\test\\260100023-01-2019-xml.xml"), Charset.forName("UTF-8"));

        Map map = xmlMapper.readValue(xml, Map.class);


        int k = map.keySet().size();
    }
}
