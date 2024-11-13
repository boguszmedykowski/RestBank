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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private XmlFileRepository xmlFileRepository;

    @PostMapping("/upload-xml")
    public ResponseEntity<String> uploadXmlFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            logger.error("Uploaded file is empty");
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

            logger.info("File uploaded and saved to database successfully");
            return new ResponseEntity<>("File uploaded and saved to database successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Failed to parse and save XML file", e);
            return new ResponseEntity<>("Failed to parse and save XML file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}