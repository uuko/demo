package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/readerlist")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('PM')")
public class ReadController {

    private ReadRepository readRepository;

    @Autowired
    public ReadController (ReadRepository readRepository){
        this.readRepository=readRepository;
    }

    //read
    @GetMapping("/books")
    public List<Book> getAllBook(){
        return readRepository.findAll();
    }

    //create
    @PostMapping(value = "/books")
    public Book addBookList(@Valid @RequestBody Book book){
        return readRepository.save(book);
    }

    //update
    @PutMapping(value = "books/{id}")
    public Book updateBook(@PathVariable(value = "id") Long id,@Valid @RequestBody Book book){
        Optional<Book> bookList=readRepository.findById(id);

        book.setTitle(bookList.get().getTitle());
        book.setReader(bookList.get().getReader());
        book.setAuthor(bookList.get().getAuthor());
        book.setDescription(bookList.get().getDescription());
        book.setIsbn(bookList.get().getDescription());

        Book updateBookList=readRepository.save(book);
        return updateBookList;
    }

    //delete
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> delBook(@PathVariable("id") Long id){
        Book book=readRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
        readRepository.delete(book);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{reader}",method = RequestMethod.GET)
    public List<Book> readerBooks(@PathVariable("reader") String reader){
        List<Book> readingList =readRepository.findByReader(reader);
        return  readingList;
    }

    @RequestMapping(value = "/{reader}",method = RequestMethod.POST)
    public Book addToReadList(@PathVariable("reader") String reader,@Valid @RequestBody Book book){
        book.setReader(reader);
        return readRepository.save(book);
    }



//    @RequestMapping(value = "/{reader}",method = RequestMethod.GET)
//    public String readerBooks(@PathVariable("reader") String reader, Model model){
//        List<Book> readingList =readRepository.findByReader(reader);
//        if (readingList != null){
//            model.addAttribute("books",readingList);
//        }
//        return  "readingList";
//    }

//    @RequestMapping(value = "/{reader}",method = RequestMethod.POST)
//    public String addToReadList(@PathVariable("reader")String reader,Book book){
//        book.setReader(reader);
//        readRepository.save(book);
//        return "redirect:/readerlist/{reader}";
//    }

}
