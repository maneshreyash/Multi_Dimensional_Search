import java.util.*;

/**
 * Starter code for LP3
 *
 * @author
 */


// If you want to create additional classes, place them in this file as subclasses of MDS

public class MDS {
    // Add fields of MDS here

    HashMap<Long, TreeSet<Product>> table = new HashMap<>();
    TreeMap<Long, Product> tree = new TreeMap<>();

    // Constructors
    public MDS() {
        table = new HashMap<>();
        tree = new TreeMap<>();

    }

    public static void main(String[] args) {
        //mockProducts();
        //System.out.println(findPrice);
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
        List<Long> desc = new LinkedList<>();
        desc.clear();
        if (list != null) {
            for (long listElement : list) {
                desc.add(listElement);
            }
        }

        if (tree.containsKey(id)) {
            Product existingProduct = tree.get(id);
            delete(id);
            if (list == null || list.isEmpty()) {
                insert(id, price, existingProduct.desc);
            } else {
                insert(id, price, desc);
            }

            return 0;
        } else {
            Product newProduct = new Product(id, price, desc);
            tree.put(id, newProduct);
            for (long d : desc) {
                TreeSet<Product> set = table.get(d);
                if (set == null) {
                    set = new TreeSet<>();
                    set.add(newProduct);
                    table.put(d, set);
                } else {
                    set.add(newProduct);
                }
            }
            return 1;
        }

    }
    // b. Find(id): return price of item with given id (or 0, if not found).
    public Money find(long id) {

        return tree.get(id) == null ? new Money("0") : tree.get(id).price;
    }

    /**
     * A utility method which provides us with the bean of Product for a specific Id, useful in testing if the fields
     * of the product class are as expected
     *
     * @param id of the product
     * @return Product having that Id
     */
    Product getProductById(long id) {
        return tree.get(id);
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

        public Product(long id, Money price) {
            this.id = id;
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Product product = (Product) o;

            return id == product.id;
        }

        @Override
        public int hashCode() {
            return (int) (id ^ (id >>> 32));
        }

        @Override
        public int compareTo(Product o) {
            if (this.price.compareTo(o.price) < 0)
                return -1;
            else if (this.price.compareTo(o.price) > 0) {
                return 1;
            } else {
                if(this.id > o.id){
                    return 1;
                }else if(this.id < o.id){
                    return -1;
                }else{
                    return 0;
                }
            }
        }
    }

    /*
       c. Delete(id): delete item from storage.
       Returns the sum of the long ints that are in the description of the item deleted,
       or 0, if such an id did not exist.
    */
    public long delete(long id) {
        Product p = tree.remove(id);
        long sum = 0;
        if (p != null) {
            for (long d : p.desc) {
                sum += d;
                TreeSet<Product> set = table.get(d);
                if (set.size() > 1) {
                    set.remove(p);
                } else {
                    table.remove(d);
                }
            }
            return sum;
        } else {
            return 0;
        }

    }

    /*
       d. FindMinPrice(n): given a long int, find items whose description
       contains that number (exact match with one of the long ints in the
       item's description), and return lowest price of those items.
       Return 0 if there is no such item.
    */
    public Money findMinPrice(long n) {
        if(table.containsKey(n)){
            return table.get(n).first().price;
        }else{
            return new Money("0");
        }
    }

    /*
       e. FindMaxPrice(n): given a long int, find items whose description
       contains that number, and return highest price of those items.
       Return 0 if there is no such item.
    */
    public Money findMaxPrice(long n) {
        if (table.containsKey(n)) {
            return table.get(n).last().price;
        } else {
            return new Money("0");
        }


    }

    /*
       f. FindPriceRange(n,low,high): given a long int n, find the number
       of items whose description contains n, and in addition,
       their prices fall within the given range, [low, high].
    */
    public int findPriceRange(long n, Money low, Money high) {
        Product lowlim = new Product(Long.MIN_VALUE, low);
        Product highlim = new Product(Long.MAX_VALUE, high);

        if(table.containsKey(n)){
            TreeSet<Product> ranger = table.get(n);
            if(high.compareTo(ranger.first().price) < 0 || low.compareTo(ranger.last().price) > 0){
                return 0;
            }
            return ranger.subSet(ranger.ceiling(lowlim), true, ranger.floor(highlim), true).size();
        }
        else {
            return 0;
        }
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

            if (result1 < result2) {
                return -1;
            } else if (result1 > result2) {
                return 1;
            } else {
                return 0;
            }

        }

        public String toString() {

            return d + "." + c;
        }
    }

}