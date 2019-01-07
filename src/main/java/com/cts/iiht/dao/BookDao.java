package com.cts.iiht.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.iiht.entity.Book;
import com.cts.iiht.entity.Subject;
import com.cts.iiht.util.JPAUtil;

@Repository
public class BookDao {

	@PersistenceContext
	private EntityManager em;
	
	public void addBook(Book book ) {
		/*EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction txn = em.getTransaction();
        txn.begin();*/
        em.persist(book);
        //txn.commit();
		System.out.println("Book added succesfully");
	}

	public void deleteBook(long id) {
			/*EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			EntityTransaction txn = em.getTransaction();
	        txn.begin();*/
	        Book book = searchBook(id);
	        if(book != null) {
	        	book = em.merge(book);
	        	em.remove(book);
		        //txn.commit();
				System.out.println("Book with id: "+ id + " has been deleted successfully");
	        }
			else {
				System.out.println("No Book found with id: "+ id );
			}		
	}

	public Book searchBook(long id) {
		//EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		return em.find(Book.class, id);
	}
	
	public List<Book> getAllBooks() {
		//EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		return em.createQuery("FROM Book b").getResultList();
	}
	
	public List<Book> getAllBooksSortByTitle(){
		//EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		return em.createQuery("FROM Book b order by b.title").getResultList();
	}
	
	public List<Book> getAllBooksSortByPublishDate(){
		//EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		return em.createQuery("FROM Book b order by b.publishDate").getResultList();
	}
}
