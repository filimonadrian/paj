

## 2nd Assignment

---
### Exercise 1

 - Open the BankApplication project and get familiar
with its structure and functionality. Tests will help
you to understand it.
-  Write an AccountFactory class to serve as a static
factory for creating saving/checking accounts. Write a
test to verify its functionality.
---

### Exercise 2

- Implement the Cloneable interface for the
AbstractAccount class and write a clone method.
- Write an AccountCache class that will maintain a map of
String and AbstractAccount (one saving and one
checking account, both set with all values on 0). Provide
methods to load the cache and for cloning an account.
When a client will ask for creating an account, it will be
cloned from the cache.
- Write a test to verify this functionality.

---
### Exercise 3

- Investigate the BankApplication project and identify the
classes where the accessibility of the members can be
minimized and use accessor methods instead.
- Eliminate the tagged classes and refactor them so
everything will work based only on hierarchies.
- Replace the listeners from the Bank class with
anonymous classes.
- Make sure that tests work fine after introducing these
changes.
---