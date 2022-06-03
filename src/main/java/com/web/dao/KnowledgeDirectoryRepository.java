package com.web.dao;

import com.model.KnowledgeDirectory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface KnowledgeDirectoryRepository extends JpaRepository<KnowledgeDirectory, Long> {

    KnowledgeDirectory findByName(String name);

    @Override
    @Transactional
    List<KnowledgeDirectory> findAll();
}
