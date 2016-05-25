package com.ppjun.repository;

import com.ppjun.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Rc3 on 2016/5/25.
 */
public interface NoteRepository extends JpaRepository<NoteEntity, Integer>
{

    @Modifying
    @Transactional
    @Query("update NoteEntity note set note.noteTitle=:qTitle,note.noteContent=:qContent,note.pubDate=:qPubDate,note.userByUserId.id=:qUserId where note.id=:qId")
    void updateNote(@Param("qTitle") String title, @Param("qContent") String content, @Param("qPubDate") Date pubDate, @Param("qUserId") int userId, @Param("qId") int id);

}
