package com.medykowski.RestBank.repository;

import com.medykowski.RestBank.entity.XmlFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XmlFileRepository extends JpaRepository<XmlFile, Long> {
}