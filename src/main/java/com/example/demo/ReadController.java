package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/readerlist")
public class ReadController {

    private ReadRepository readRepository;

    @Autowired
    public ReadController (ReadRepository readRepository){
        this.readRepository=readRepository;
    }

    @RequestMapping(value = "/{reader}",method = RequestMethod.GET)
    public String readerBooks(@PathVariable("reader") String reader, Model model){
        List<Book> readingList =readRepository.findByReader(reader);
        if (readingList != null){
            model.addAttribute("books",readingList);
        }
        return  "readingList";
    }

    @RequestMapping(value = "/{reader}",method = RequestMethod.POST)
    public String addToReadList(@PathVariable("reader")String reader,Book book){
        book.setReader(reader);
        readRepository.save(book);
        return "redirect:/readerlist/{reader}";
    }

}
