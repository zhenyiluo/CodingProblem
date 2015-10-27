import java.util.*;

class Book{
    int id;
    String author;
    String title;
}

public class Library{
    HashMap<String, LinkedList<Integer>> bookByAuthorMap = new HashMap<String, LinkedList<Integer>>();

    HashMap<String, Integer> bookByTitleMap = new HashMap<String, Integer>();

    HashMap<Integer, PriorityQueue<String>> reserveInfo = new HashMap<Integer, PriorityQueue<String>>();

    HashMap<Integer, Book> books = new HashMap<Integer, Book>();

    HashMap<String, Integer> userPriority = new HashMap<String, Integer>();

    TreeMap<Integer, Integer> ratingsBook = new TreeMap<Integer, Integer>();

    public LinkedList<Integer> findBookIdsByAuthor(String author){
        return bookByAuthorMap.get(author);
    }

    public Integer findBookIdByTitle(String title){
        return bookByTitleMap.get(title);
    }

    public LinkedList<Book> findBookByAuthor(String author){
        LinkedList<Book> list = new LinkedList<Book>();
        for(Integer id : findBookIdsByAuthor(author)){
            list.add(books.get(id));
        }
        return list;
    }

    public Book findBookByTitle(String title){
        return books.get(findBookIdByTitle(title));
    }
    public LinkedList<Book> findBookByRatingRange(int lowRate, int highRate){
        LinkedList<Book> list = new LinkedList<Book>();
        for(int id : findBookIdsByRatingRange(lowRate, highRate)){
            list.add(books.get(id));
        }
        return list;
    }
    public Set<Integer> findBookIdsByRatingRange(int lowRate, int highRate){
        return ratingsBook.subMap(lowRate, true, highRate, true).keySet();
    }

    public void reserveBooks(String userName, Integer bookId){
        if(reserveInfo.containsKey(bookId)){
            PriorityQueue q = reserveInfo.get(bookId);
            q.add(userName);
        }else{
            PriorityQueue q = new PriorityQueue<String>(10, new Comparator<String>(){
                @Override
                public int compare(String s1, String s2){
                    return userPriority.get(s2) - userPriority.get(s1);
                }
            });
            q.add(userName);
            reserveInfo.put(bookId, q);
        }
    }

}
