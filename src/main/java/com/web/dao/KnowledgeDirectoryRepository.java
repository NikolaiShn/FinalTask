package com.web.dao;

import com.model.KnowledgeDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface KnowledgeDirectoryRepository extends JpaRepository<KnowledgeDirectory, Long> {

    KnowledgeDirectory findByName(String name);

    KnowledgeDirectory findByThemesId(Long themeId);

    @Override
    @Transactional
    List<KnowledgeDirectory> findAll();

    @Modifying
    @Transactional
    @Query("update KnowledgeDirectory knowledgeDirectory set knowledgeDirectory.name = :newName where knowledgeDirectory.name = :oldName")
    void editKnowledgeDirectoryName(@Param("newName") String newName, @Param("oldName") String oldName);
}
