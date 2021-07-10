import java.io.*;
import java.util.*;

public class Practice 
{
    
    public static void main(String args[]) throws IOException
    {
        //File f;
        try
        {
            File f=new File("booksFile.dat");
            if(!f.exists())
            {
                f.createNewFile();
            }
        }
        catch (IOException ioe) {
 
            System.out.println(ioe);
        }
        catch (NumberFormatException nfe) {
 
            System.out.println(nfe);
        }

        ArrayList<String> genre=new ArrayList<String>();
        genre.add("Suspense");
        genre.add("Thriller");
        genre.add("Romance");
        System.out.println(genre);
        Book obj1=new Book("Angels and Demons", "Dan Brown", genre, 400.00, "753938563829");
        Book obj2=new Book("The Da Vinci Code", "Dan Brown", genre, 500.00, "109248595039");
        System.out.println(obj1.getGenre());

        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try
        {
            fos=new FileOutputStream("booksFile.dat");
            oos=new ObjectOutputStream(fos);
            oos.writeObject(obj1);
            oos.writeObject(obj2);
            oos.writeObject(new EofIndicatorClass());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally 
        {
            if(fos!=null)
                fos.close();
            if(oos!=null)
                oos.close();
        }
        
        System.out.println(Book.getBooks("booksFile.dat"));
        System.out.println("DONE");
        System.out.println(Book.checkISBN("10924859039"));
    }
    
}
