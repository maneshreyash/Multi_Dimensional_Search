import java.util.*;

/**
 * Starter code for LP3
 *
 * @author
 */


// If you want to create additional classes, place them in this file as subclasses of MDS

public class MDS {
    // Add fields of MDS here

    HashMap<Long, HashSet<Long>> table;
    TreeMap<Long, Product> tree;


    // Constructors
    public MDS() {
        table = new HashMap<>();
        tree = new TreeMap<>();
    }

    //Inner Class
    static class Product{
        long id;
        Money price;
        List<Long> desc;

        public Product(Money price, java.util.List<Long> list) {
            this.price = price;
            this.desc = list;
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
    }




   /* public static void main(String[] args) {
        mockProducts();
        System.out.println(findPrice);
    }*/

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
            desc.addAll(list);
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
            Product newProduct = new Product(price, desc);
            tree.put(id, newProduct);
            for (long d : desc) {
                HashSet<Long> set = table.get(d);
                if (set == null) {
                    set = new HashSet<>();
                    set.add(id);
                    table.put(d, set);
                } else {
                    set.add(id);
                }
            }

            return 1;
        }

    }
    // b. Find(id): return price of item with given id (or 0, if not found).
    public Money find(long id) {
        if(tree.containsKey(id)){
            return tree.get(id).price;
        }else{
            return new Money();
        }
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


    /*
       c. Delete(id): delete item from storage.
       Returns the sum of the long ints that are in the description of the item deleted,
       or 0, if such an id did not exist.
    */
    public long delete(long id) {

        Product p = tree.remove(id);
        long sum = 0;
        if (p != null) {
            for (long d : p.desc)
            {
                sum += d;
                HashSet<Long> set = table.get(d);
                if (set != null) {
                    if (set.size() > 1) {
                        set.remove(id);
                    } else {
                        set.remove(id);
                        table.remove(d);
                    }
                }
            }
            return sum;
        }
        else {
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
            HashSet<Long> min = table.get(n);
            Money mini = new Money(Long.MAX_VALUE, 0);
            Money temp;
            for(Long id: min){
                temp = find(id);
                if(find(id).compareTo(mini) < 0){
                    mini = temp;
                }
            }
            return mini;
        }else{
            return new Money();
        }
    }


       /*e. FindMaxPrice(n): given a long int, find items whose description
       contains that number, and return highest price of those items.
       Return 0 if there is no such item.*/

    public Money findMaxPrice(long n) {
        if (table.containsKey(n)) {
            HashSet<Long> max = table.get(n);
            Money maxi = new Money();
            Money temp;
            for(Long id: max){
                temp = find(id);
                if(find(id).compareTo(maxi) > 0){
                    maxi = temp;
                }
            }
            return maxi;
        } else {
            return new Money();
        }


    }

    /*
       f. FindPriceRange(n,low,high): given a long int n, find the number
       of items whose description contains n, and in addition,
       their prices fall within the given range, [low, high].
    */
    public int findPriceRange(long n, Money low, Money high) {

        if(low.compareTo(high) > 0)
        {
            return 0;
        }

        if(table.containsKey(n)){
            HashSet<Long> range = table.get(n);
            int count = 0;
            Money temp;
            for(Long id: range){
                temp = find(id);
                if(temp.compareTo(low) >= 0 && temp.compareTo(high) <= 0){
                    count++;
                }
            }
            return count;
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
        //handled null pointers in case high is less than minimum or low is greater than maximum keys.
        if(tree.lastKey() < l || tree.firstKey() > h){
            return new Money();
        }
        long low = tree.ceilingKey(l);
        long high = tree.floorKey(h);

        Money sum = new Money();
        for(long i: tree.keySet()){
            if(i <= high && i >= low) {
                if (tree.containsKey(i)) {
                    Product p = tree.get(i);
                    Money temp = p.price;
                    Money increase = MoneyIntoFloat(p.price, rate);
                    Money update = MoneyAdder(temp, increase);
                    sum = MoneyAdder(sum, increase);
                    tree.put(i, new Product(update, p.desc));
                }
            }
        }
        return sum;
    }

    /*
     * Used internally to add 2 Money prices.
     *
     * */
    private Money MoneyAdder(Money p1, Money p2){
        long a = (p1.dollars() * 100) + p1.cents();
        long b = (p2.dollars() * 100) + p2.cents();
        long res = a + b;
        return new Money(res/100, (int) (res%100));
    }


    /*
     * Used internally to multiply the value of Money and
     * rate to find what needs to be added to make updates in priceHike
     * */
    private Money MoneyIntoFloat(Money p1, double r){
        long a = (p1.dollars() * 100) + p1.cents();
        double res = (a * (r / 100));
        long result = (long) res;
        return new Money(result/100, (int) (result%100));
    }

    /*
      h. RemoveNames(id, list): Remove elements of list from the description of id.
      It is possible that some of the items in the list are not in the id's description.
      Return the sum of the numbers that are actually deleted from the description of id.
      Return 0 if there is no such id.
    */
    public long removeNames(long id, java.util.List<Long> list) {

         long sum = 0;
         Product p = tree.get(id);

         if (p != null) {
             for (long i : list)
             {
                if (p.desc.contains(i))
                {
                    sum += i;
                    p.desc.remove(i);
                    HashSet<Long> set = table.get(i);
                    if (set != null)
                    {
                        if (set.size() > 1) {
                            set.remove(id);
                        } else {
                            set.remove(id);
                            table.remove(i);
                        }
                    }
                }
             }
        }

        return sum;
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

            if (this.d < other.d) {
                return -1;
            } else if (this.d > other.d) {
                return 1;
            } else {
                if (this.c < other.c) {
                    return -1;
                } else if (this.c > other.c) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

        public String toString() {

            return d + "." + c;
        }
    }

}