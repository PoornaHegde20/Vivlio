import java.io.*;
import java.util.*;

public class Book implements Serializable
{
    //instance variables
    private String m_title, m_ISBN, m_author;
    private double m_price;
    private int m_numCopies;
    private ArrayList<Customer> m_buyers, m_borrowers;
    private ArrayList<String> m_genre;

    //static variable containing a list of all the books
    private static ArrayList<Book> booksList;

    //default constructor with null/default values
    public Book()
    {}

    //constructor taking in 5 characteristic inputs; others are updated with time
    public Book(String title, String author, ArrayList<String> genre, double price, String ISBN)
    {
        m_title=title;
        m_author=author;
        m_genre=new ArrayList<>(genre);
        m_price=price;
        m_ISBN=ISBN;
        //10 copies of the book 
        m_numCopies=10;
        m_buyers=new ArrayList<Customer>();
        m_borrowers=new ArrayList<Customer>();
    }

    public String toString()
    {
        //String representation of the book object with all of its characteristic details
        String s="Title  : "+m_title+"\nAuthor : "+m_author+"\nGenre  : "+showGenre()+"\nPrice  : "+m_price+"\nISBN   : "+m_ISBN;
        return s;
    }

    //getter functions
    public String getTitle()
    {
        return m_title;
    }

    public String getISBN()
    {
        return m_ISBN;
    }

    public String getAuthor()
    {
        return m_author;
    }

    public double getPrice()
    {
        return m_price;
    }

    public int getNumCopies()
    {
        return m_numCopies;
    }

    public boolean isAvailable()
    {
        return (m_numCopies!=0);
    }

    public ArrayList<String> getGenre()
    {
        return m_genre;
    }

    public ArrayList<Customer> getBorrowers()
    {
        return m_borrowers;
    }

    public ArrayList<Customer> getBuyers()
    {
        return m_buyers;
    }

    //private function to print genre in a good manner
    private String showGenre()
    {
        String s=m_genre.get(0);
        for(int i=1;i<m_genre.size();i++)
            s+=", "+m_genre.get(i);
        return s;
    }

    //setter functions
    public void setTitle(String title)
    {
        m_title=title;
    }

    public void setAuthor(String author)
    {
        m_author=author;
    }

    public void setISBN(String ISBN)
    {
        m_ISBN=ISBN;
    }

    public void setPrice(double price)
    {
        m_price=price;
    }

    public void setGenre(ArrayList<String> genre)
    {
        m_genre=new ArrayList<String>(genre);
    }

    public void setNumCopies(int numCopies)
    {
        m_numCopies=numCopies;
    }

    public void setBorrowers(ArrayList<Customer> borrowers)
    {
        m_borrowers=new ArrayList<Customer>(borrowers);
    }

    public void setBuyers(ArrayList<Customer> buyers)
    {
        m_buyers=new ArrayList<Customer>(buyers);
    }

    public static boolean checkISBN(String ISBN) throws IOException
    {
        ArrayList<Book> books=new ArrayList<Book>(getBooks("booksFile.dat"));
        for (int i=0;i<books.size();i++) 
            if(books.get(i).getISBN().equals(ISBN))
                return true;
        return false;
    }

    public static ArrayList<Book> getBooks(String filename) throws IOException
    {
        ArrayList<Book> books=new ArrayList<Book>();

        File booksFile=new File(filename);

        FileInputStream fis=null;
        ObjectInputStream ois=null;

        try
        {
            fis=new FileInputStream(booksFile);
            ois=new ObjectInputStream(fis);
            /*Object object = null;
            while (!((object =  ois.readObject()) instanceof EofIndicatorClass))
            { 
                if(object instanceof Book) 
                {
                    Book book = (Book) object;
                    books.add(book);
                }
            }*/
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            if(fis!=null)
                fis.close();
            if(ois!=null)
                ois.close();
        }

        return books;
    }

}
