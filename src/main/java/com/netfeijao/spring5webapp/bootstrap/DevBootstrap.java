package com.netfeijao.spring5webapp.bootstrap;

import com.netfeijao.spring5webapp.model.Author;
import com.netfeijao.spring5webapp.model.Book;
import com.netfeijao.spring5webapp.model.Publisher;
import com.netfeijao.spring5webapp.repositories.AuthorRepository;
import com.netfeijao.spring5webapp.repositories.BookRepository;
import com.netfeijao.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        Publisher packt = new Publisher("Packt Publishing", "England");
        Publisher harper = new Publisher("Harper Collings", "United States");

        publisherRepository.save(packt);
        publisherRepository.save(harper);

        Author wagner =new Author("Wagner", "Santos");
        Book infinispan = new Book("Infinispan: The Definitive Guide", "1324", packt);
        wagner.getBooks().add(infinispan);
        infinispan.getAuthors().add(wagner);

        authorRepository.save(wagner);
        bookRepository.save(infinispan);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "32144", harper);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);
    }
}
