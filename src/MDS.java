import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Starter code for LP3
 *
 * @author
 */


// If you want to create additional classes, place them in this file as subclasses of MDS

public class MDS {
    // Add fields of MDS here

    static HashMap<Long, TreeSet<Product>> table = new HashMap<>();
    TreeMap<Long, Product> tree = new TreeMap<>();

    // Constructors
    public MDS() {

    }

    public static void main(String[] args) {
        //mockProducts();
    }

    /* Public methods of MDS. Do not change their signatures.
       __________________________________________________________________
       a. Insert(id,price,list): insert a new item whose description is given
       in the list.  If an entry with the same id already exists, then its
       description and price are replaced by the new values, unless list
       is null or empty, in which case, just the price is updated.
       Returns 1 if the item is new, and 0 otherwise.
    */
    public int insert(long id, Money price, java.util.List<Long> list) {

        return 0;
    }
   /* static void  mockProducts(){
         TreeSet<Product> ts = new TreeSet<>();
         TreeSet<Product> ts2 = new TreeSet<>();
         TreeSet<Product> ts3 = new TreeSet<>();
        Product p1 = new Product(1L,450);
        Product p2 = new Product(2L,150);
        Product p3 = new Product(3L,350);
        Product p4 = new Product(4L,250);
        Product p5 = new Product(5L,550);
        Product p6 = new Product(6L,650);
        Product p7 = new Product(7L,11550);
        Product p8 = new Product(8L,1550);
        Product p9 = new Product(9L,2550);

        ts.add(p1);
        ts.add(p2);
        ts.add(p3);
        ts2.add(p4);
        ts2.add(p5);
        ts2.add(p6);
        ts3.add(p7);
        ts3.add(p8);
        ts3.add(p9);
        table.put(1L,ts);
        table.put(186L,ts2);
        table.put(1921L,ts3);


        System.out.println(ts);
    }*/

    // b. Find(id): return price of item with given id (or 0, if not found).
    public Money find(long id) {

        return new Money();
    }

    static class Product implements Comparable<Product> {
        long id;
        Money price;
        List<Long> desc;

        public Product(long id, Money price, java.util.List<Long> list) {
            this.id = id;
            this.price = price;
            this.desc = list;
        }

        @Override
        public int compareTo(Product o) {
            if (this.price.compareTo(o.price) < 0)
                return -1;
            else if (this.price.compareTo(o.price) > 0) {
                return 1;
            } else
                return 0;
        }
    }

    /*
       c. Delete(id): delete item from storage.  Returns the sum of the
       long ints that are in the description of the item deleted,
       or 0, if such an id did not exist.
    */
    public long delete(long id) {
        return 0;
    }

    /*
       d. FindMinPrice(n): given a long int, find items whose description
       contains that number (exact match with one of the long ints in the
       item's description), and return lowest price of those items.
       Return 0 if there is no such item.
    */
    public Money findMinPrice(long n) {
        return new Money();
    }

    /*
       e. FindMaxPrice(n): given a long int, find items whose description
       contains that number, and return highest price of those items.
       Return 0 if there is no such item.
    */
    public Money findMaxPrice(long n) {
        return new Money();
    }

    /*
       f. FindPriceRange(n,low,high): given a long int n, find the number
       of items whose description contains n, and in addition,
       their prices fall within the given range, [low, high].
    */
    public int findPriceRange(long n, Money low, Money high) {
        return 0;
    }

    /*
       g. PriceHike(l,h,r): increase the price of every product, whose id is
       in the range [l,h] by r%.  Discard any fractional pennies in the new
       prices of items.  Returns the sum of the net increases of the prices.
    */
    public Money priceHike(long l, long h, double rate) {
        return new Money();
    }

    /*
      h. RemoveNames(id, list): Remove elements of list from the description of id.
      It is possible that some of the items in the list are not in the
      id's description.  Return the sum of the numbers that are actually
      deleted from the description of id.  Return 0 if there is no such id.
    */
    public long removeNames(long id, java.util.List<Long> list) {
        return 0;
    }

    // Do not modify the Money class in a way that breaks LP3Driver.java
    public static class Money implements Comparable<Money> {
        long d;
        int c;

        public Money() {
            d = 0;
            c = 0;
        }

        public Money(long d, int c) {
            this.d = d;
            this.c = c;
        }

        public Money(String s) {
            String[] part = s.split("\\.");
            int len = part.length;
            if (len < 1) {
                d = 0;
                c = 0;
            } else if (part.length == 1) {
                d = Long.parseLong(s);
                c = 0;
            } else {
                d = Long.parseLong(part[0]);
                c = Integer.parseInt(part[1]);
            }
        }

        public long dollars() {

            return d;
        }

        public int cents() {
            return c;
        }

        public int compareTo(Money other) { // Complete this, if needed
            long result1 = (this.dollars() * 100) + this.cents();
            long result2 = (other.dollars() * 100) + other.cents();

            return (int) (result1 - result2);
        }

        public String toString() {

            return d + "." + c;
        }
    }

}