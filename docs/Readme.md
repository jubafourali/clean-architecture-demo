### Clean architecture Demo

As part of this workshop we will be following clean architecture and will be implementing the following features using ATDD

1. Should be able to create an item

   Description:
    1. fields in an Item are
        1. name, code, description
        2. code has to be unique and mandatory

   | Given  | When | Then |
   | ------------- | ------------- | -------- |
   | Item with code item-1234 does not exist  | an item is created with missing name  | returns error stating "item name cannot be empty" |
   | Item with code item-1234 does not exist  | an item is created with code item-1234  | item is successfully created |
   | Item with code item-1234 exists  | and another item is created with code item-1234  | returns error stating "item with code already exists"  |

2. Should be able to list all saved items

   | Given  | When | Then |
   | ------------- | ------------- | -------- |
   | Items exist  | fetch all items  | returns all items |
   | Items do not exist  | fetch all items  | should return empty list |
