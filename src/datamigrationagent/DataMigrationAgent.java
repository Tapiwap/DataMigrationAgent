/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamigrationagent;

import com.agent.dao.BookDAO;
import com.agent.model.Book;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * This is the main class that runs the application Run this class and the
 * migration will be executed
 *
 * @author Tapiwa Pitso
 */
public class DataMigrationAgent {

    private static final Logger LOG = Logger.getLogger(DataMigrationAgent.class.getName());

    public DataMigrationAgent() {
        try {
            LOG.addHandler(new FileHandler("log.txt", true));
            LOG.setLevel(Level.INFO);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(DataMigrationAgent.class.getName()).log(Level.SEVERE, "Cannot access Log file", ex);
            LOG.log(Level.INFO, "Cannot access Log file{0}", ex.toString());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LOG.log(Level.INFO, "DataMigration Started.....");
        List<Book> bookList = getBookList();

        com.agent.entity.Book bookEntity = new com.agent.entity.Book();
        BookDAO bookDao = new BookDAO();

        bookList.forEach((book) -> {
            try {
                marshalBookEntity(bookEntity, book);
                bookDao.addBook(bookEntity);

            } catch (ParseException ex) {
                Logger.getLogger(DataMigrationAgent.class.getName()).log(Level.SEVERE, "Could not save book to database", ex);
                LOG.log(Level.SEVERE, "Could not save book to database{0}", ex);
            }
        });
        LOG.log(Level.INFO, "DataMigration Complete.....");
    }

    private static List<Book> getBookList() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8084/DataMigrationServer/api/books");
        List<Book> bookList = target.request(MediaType.APPLICATION_XML).get(new GenericType<List<Book>>() {
        });
        return bookList;
    }

    private static void marshalBookEntity(com.agent.entity.Book bookEntity, com.agent.model.Book book) throws ParseException {
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setDate(parseStringToDate(book));
        bookEntity.setDescription(book.getDescription());
        bookEntity.setGenre(book.getGenre());
        bookEntity.setPrice(BigDecimal.valueOf(book.getPrice()));
        bookEntity.setTitle(book.getTitle());
    }

    private static Date parseStringToDate(Book book) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(book.getPublishDate());
        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
        return sqlDate;
    }

}
