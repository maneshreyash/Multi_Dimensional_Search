Project Number: LP 24
Team Members:
Sunny Bangale (shb170230)
Ketki Mahajan (krm150330)
Ameya Kasar (aak170230)
Shreyash Mane (ssm170730)

Project Description:
Multi-dimensional search: Consider the web site of a seller like Amazon.  
They carry tens of thousands of products, and each product has many
attributes (Name, Size, Description, Keywords, Manufacturer, Price, etc.).  
The search engine allows users to specify attributes of products that
they are seeking, and shows products that have most of those
attributes.  To make search efficient, the data is organized using
appropriate data structures, such as balanced trees.  But, if products
are organized by Name, how can search by price implemented efficiently?
The solution, called indexing in databases, is to create a new set of
references to the objects for each search field, and organize them to
implement search operations on that field efficiently.  As the objects
change, these access structures have to be kept consistent.

In this project, each object has 3 attributes: id (long int), description
(one or more long ints), and price (dollars and cents).

Steps to run the code:
1. Unzip the file
2. Open a java IDE like Eclipse/IntelliJ
3. Go to File  -> New  -> Project  and create a new java project 
4. Copy the java files along with the package folder  (shb170230) in the source folder
5. Run the program (Num.java)

Methods Implemented:

   a. Insert(id,price,list): 
      Inserts a new item whose description is given
      in the list.  If an entry with the same id already exists, then its
      description and price are replaced by the new values, unless list
      is null or empty, in which case, just the price is updated. 
      Returns 1 if the item is new, and 0 otherwise.

   b. Find(id): 
       Returns price of item with given id (or 0, if not found).

   c. Delete(id): 
      Deletes item from storage.  Returns the sum of the
      long ints that are in the description of the item deleted
      (or 0, if such an id did not exist).

   d. FindMinPrice(n): 
      Given a long int, it finds items whose description
      contains that number (exact match with one of the long ints in the
      item's description), and return lowest price of those items.
      Return 0 if there is no such item.

   e. FindMaxPrice(n): 
      Given a long int, it finds items whose description
      contains that number, and return highest price of those items.
      Return 0 if there is no such item.

   f. FindPriceRange(n,low,high): 
      Given a long int n, it finds the number
      of items whose description contains n, and in addition,
      their prices fall within the given range, [low, high].

   g. PriceHike(l,h,r): 
      It increases the price of every product, whose id is 
      in the range [l,h] by r%.  Discard any fractional pennies in the new 
      prices of items.  Note that you are truncating, not rounding.
      Returns the sum of the net increases of the prices.

   h. RemoveNames(id, list): 
      Removes elements of list from the description of id.
      It is possible that some of the items in the list are not in the
      id's description.  Return the sum of the numbers that are actually
      deleted from the description of id.  Return 0 if there is no such id.

If you find difficulties running the files please let us know.

Thank you