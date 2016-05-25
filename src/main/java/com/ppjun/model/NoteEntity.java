package com.ppjun.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author Rc3
 * @create 2016/05/25  11:55
 */
@Entity
@Table(name = "note", schema = "notedb", catalog = "")
public class NoteEntity
{
    private int id;
    private String noteTitle;
    private String noteContent;
    private Date pubDate;
    private UserEntity userByUserId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "note_title", nullable = true, length = 100)
    public String getNoteTitle()
    {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle)
    {
        this.noteTitle = noteTitle;
    }

    @Basic
    @Column(name = "note_content", nullable = true, length = 255)
    public String getNoteContent()
    {
        return noteContent;
    }

    public void setNoteContent(String noteContent)
    {
        this.noteContent = noteContent;
    }

    @Basic
    @Column(name = "pub_date", nullable = true)
    public Date getPubDate()
    {
        return pubDate;
    }

    public void setPubDate(Date pubDate)
    {
        this.pubDate = pubDate;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteEntity that = (NoteEntity) o;

        if (id != that.id) return false;
        if (noteTitle != null ? !noteTitle.equals(that.noteTitle) : that.noteTitle != null) return false;
        if (noteContent != null ? !noteContent.equals(that.noteContent) : that.noteContent != null) return false;
        if (pubDate != null ? !pubDate.equals(that.pubDate) : that.pubDate != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (noteTitle != null ? noteTitle.hashCode() : 0);
        result = 31 * result + (noteContent != null ? noteContent.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByUserId()
    {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId)
    {
        this.userByUserId = userByUserId;
    }
}
