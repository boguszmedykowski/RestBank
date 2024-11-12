package com.medykowski.RestBank.service;

import com.medykowski.RestBank.entity.XmlFile;
import com.medykowski.RestBank.repository.XmlFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class XmlFileService {

    @Autowired
    private XmlFileRepository xmlFileRepository;

    public void saveXmlFile(InputStream xmlInputStream) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlInputStream);

            // Konwersja dokumentu XML na tablicę bajtów
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(document.toString().getBytes(StandardCharsets.UTF_8));
            byte[] content = outputStream.toByteArray();

            // Zapis w bazie danych
            XmlFile xmlFile = new XmlFile();
            xmlFile.setId(1L); // Przykładowe ID - może być dynamicznie generowane
            xmlFile.setContent(content);
            xmlFileRepository.save(xmlFile);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("Failed to parse and save XML file: " + e.getMessage());
        }
    }
}
