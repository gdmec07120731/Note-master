package com.ppjun.controller;

import com.ppjun.model.NoteEntity;
import com.ppjun.model.UserEntity;
import com.ppjun.repository.NoteRepository;
import com.ppjun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * NoteRepository
 *
 * @author Rc3
 * @create 2016/05/25  15:58
 */

@Controller
public class NoteController
{


    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/admin/notes", method = RequestMethod.GET)
    public String showNotes(ModelMap modelMap)
    {
        List<NoteEntity> noteList = noteRepository.findAll();
        modelMap.addAttribute("noteList", noteList);
        return "admin/notes";

    }

    @RequestMapping(value = "/init",method = RequestMethod.GET)
    public String init(){

        return "你好";
    }


    @RequestMapping(value = "/admin/notes/add", method = RequestMethod.GET)
    public String addNote(ModelMap modelMap)
    {
        List<UserEntity> userList = userRepository.findAll();
        modelMap.addAttribute("userList", userList);
        return "admin/addNote";

    }


    @RequestMapping(value = "/admin/notes/addP", method = RequestMethod.POST)
    public String addNotePost(@ModelAttribute("note") NoteEntity note)
    {

        noteRepository.saveAndFlush(note);
        return "redirect:/admin/notes";
    }


    @RequestMapping(value = "admin/notes/show/{id}", method = RequestMethod.GET)
    public String showNote(@PathVariable("id") int id, ModelMap modelMap)
    {

        modelMap.addAttribute("note", noteRepository.findOne(id));
        return "admin/noteDetail";

    }

    @RequestMapping(value = "admin/notes/update/{id}", method = RequestMethod.GET)
    public String updateNote(@PathVariable("id") int id, ModelMap modelMap)
    {

        NoteEntity note = noteRepository.findOne(id);
        List<UserEntity> userList = userRepository.findAll();
        modelMap.addAttribute("note", note);
        modelMap.addAttribute("userList", userList);

        return "admin/updateNote";


    }

    @RequestMapping(value = "/admin/notes/updateP",method = RequestMethod.POST)
    public String updateNoteP(@ModelAttribute("noteP") NoteEntity note){

        noteRepository.updateNote(note.getNoteTitle(),note.getNoteContent(),note.getPubDate(),note.getUserByUserId().getId(),note.getId());
        noteRepository.flush();
        return "redirect:/admin/notes";
    }

    @RequestMapping(value = "/admin/notes/delete/{id}",method = RequestMethod.GET)
    public String deleteNote(@PathVariable("id") int id){

        noteRepository.delete(id);
        noteRepository.flush();
        return "redirect:/admin/notes";
    }


}
