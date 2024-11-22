import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Statement;

public class Library {
    private ArrayList<Book> books;
    private HashMap<Integer, Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new HashMap<>();
    }

    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMember(Member member) {
        String query = "INSERT INTO members (name) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, member.getName());
            stmt.executeUpdate();
            System.out.println("Member added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void issueBook(int bookId, int memberId) {
        String updateBookQuery = "UPDATE books SET is_available = FALSE WHERE id = ?";
        String insertBorrowQuery = "INSERT INTO borrowed_books (book_id, member_id) VALUES (?, ?)";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement updateStmt = conn.prepareStatement(updateBookQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertBorrowQuery)) {
            conn.setAutoCommit(false); // Start transaction
    
            // Update book availability
            updateStmt.setInt(1, bookId);
            updateStmt.executeUpdate();
    
            // Record borrowing
            insertStmt.setInt(1, bookId);
            insertStmt.setInt(2, memberId);
            insertStmt.executeUpdate();
    
            conn.commit();
            System.out.println("Book issued successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void returnBook(int bookId, int memberId) {
        String updateBookQuery = "UPDATE books SET is_available = TRUE WHERE id = ?";
        String deleteBorrowQuery = "DELETE FROM borrowed_books WHERE book_id = ? AND member_id = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement updateStmt = conn.prepareStatement(updateBookQuery);
             PreparedStatement deleteStmt = conn.prepareStatement(deleteBorrowQuery)) {
            conn.setAutoCommit(false); // Start transaction
    
            // Update book availability
            updateStmt.setInt(1, bookId);
            updateStmt.executeUpdate();
    
            // Remove borrowing record
            deleteStmt.setInt(1, bookId);
            deleteStmt.setInt(2, memberId);
            deleteStmt.executeUpdate();
    
            conn.commit();
            System.out.println("Book returned successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    private Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public void displayAllBooks() {
    String query = "SELECT * FROM books";

    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        System.out.println("Books in the library:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                               ", Title: " + rs.getString("title") +
                               ", Author: " + rs.getString("author") +
                               ", Available: " + rs.getBoolean("is_available"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
