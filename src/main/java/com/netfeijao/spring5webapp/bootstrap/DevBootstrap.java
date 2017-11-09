package com.netfeijao.spring5webapp.bootstrap;

import com.netfeijao.spring5webapp.model.Author;
import com.netfeijao.spring5webapp.model.Book;
import com.netfeijao.spring5webapp.repositories.AuthorRepository;
import com.netfeijao.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        Author wagner =new Author("Wagner", "Santos");
        Book infinispan = new Book("Infinispan: The Definitive Guide", "1324", "Packt Publishing");
        wagner.getBooks().add(infinispan);
        infinispan.getAuthors().add(wagner);

        authorRepository.save(wagner);
        bookRepository.save(infinispan);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "32144", "Harper Collings");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);
    }
}
