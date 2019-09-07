package com.springframeworkguru.spring5webapp.bootstrap;

import com.springframeworkguru.spring5webapp.model.Author;
import com.springframeworkguru.spring5webapp.model.Book;
import com.springframeworkguru.spring5webapp.model.Publisher;
import com.springframeworkguru.spring5webapp.repositories.AuthorRepository;
import com.springframeworkguru.spring5webapp.repositories.BookRepository;
import com.springframeworkguru.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private AuthorRepository authorRepository;
  private BookRepository bookRepository;
  private PublisherRepository publisherRepository;

  public DevBootstrap(
      AuthorRepository authorRepository,
      BookRepository bookRepository,
      PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    initData();
  }

  private void initData() {
    Publisher ericPublisher = new Publisher("Harper Collins", "LA");
    Publisher rodPublisher = new Publisher("Worx", "NY");

    publisherRepository.save(ericPublisher);
    publisherRepository.save(rodPublisher);

    Author eric = new Author("Eric", "Evans");
    Book ddd = new Book("Domain Driven Design", "1234", ericPublisher);
    eric.getBooks().add(ddd);

    authorRepository.save(eric);
    bookRepository.save(ddd);

    Author rod = new Author("Rod", "Johnson");
    Book noEJB = new Book("J2EE Development without EJB", "23444", rodPublisher);
    rod.getBooks().add(noEJB);

    authorRepository.save(rod);
    bookRepository.save(noEJB);
  }
}
