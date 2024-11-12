package com.medykowski.RestBank.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
package com.medykowski.RestBank.controler;

import com.medykowski.RestBank.entity.XmlFile;
import com.medykowski.RestBank.repository.XmlFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

@RestController
public class FileUploadController {

    @Autowired
    private XmlFileRepository xmlFileRepository;

    @PostMapping("/upload-xml")
    public ResponseEntity<String> uploadXmlFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file.getInputStream());

            // Save the file to the database
            XmlFile xmlFile = new XmlFile();
            xmlFile.setContent(file.getBytes());
            xmlFileRepository.save(xmlFile);

            return new ResponseEntity<>("File uploaded and saved to database successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to parse and save XML file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}