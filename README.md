# Coding Exercises
* Adrian Banachowicz
* BE-OP-LHC-2024-174-GRAP & TE-MPE-CB-2024-166-GRAP

## Table of Contents
- [First Exercise](#first-exercise)
    - [SQL Solution](#sql-solution)
        - [Approach and Limitations](#approach-and-limitations)
    - [Python - Main Solution](#python---main-solution)
        - [Time Complexity](#time-complexity)
        - [Space Complexity](#space-complexity)
        - [Tests](#tests)
        - [Improvements](#improvements)
        - [Advantages of the Proposed Solution](#advantages-of-the-proposed-solution)

## First Exercise
    Write a function that detects all duplicate elements...

I propose two solutions for this task.
My first solution is written in SQL; 
however, since a query is not a function, 
I have also prepared main solution with a class _Finder_ in Python for this task.
### SQL Solution

The query is designed for MS SQL.

Instructions to Run (Free SQL IDE https://sqliteonline.com/):

1. Create a table.
2. Insert data.
3. Add additional test cases (if needed).
4. Execute the select query.

#### Approach and Limitations

_This approach was slightly different and provided an interesting
way for me to solve the task. However, it is not perfect due to
the use of a self-join and the requirement for an additional
position column. Additionally, in this approach, 
the object could be a string of up to 255 characters._

### Python - main solution

The Finder class allows processing a list of objects of any type. 
These objects can be strings, integers, or even custom objects. 
The primary functionality of Finder includes:

1. Updating the list of objects
2. Counting objects (only when the list is updated):
   1. Key generation:
      * For hashable objects, the object itself is used as the key
      * For custom objects, a tuple of their attributes and values is used as the key
   2. Storing counts: The number of occurrences of each object is saved in a dictionary
3. Identifying duplicates: The method checks which objects occur more than once and returns an ordered list of these duplicates
   * Since Python 3.7, dictionaries are ordered

#### Time Complexity
[Complexity of dictionary](https://wiki.python.org/moin/TimeComplexity) is O(1) in average and O(n) in amortized worst case.

The variables used in the complexity analysis are:
* n - number of elements
* u - number of unique elements _(in worst case u = n)_
* k - number of attributes in a custom object

`_count_objects()`:
 * Cleaning the dictionary: O(1)
 * Generating keys: O(k log k) for custom objects or O(1) for hashable objects
 * Insertion/updating dictionary: O(1)

Total for n elements:
- O(n * k log k) for custom objects
- O(n) for hashable objects

`find_duplicated_objects_in_order()`:

This method iterates over the dictionary of unique keys. In the worst case, u=n

Time complexity: O(u) (in the worst case, O(n))

---

**Overall Time Complexity:**
* For custom objects: O(n * k log k) + O(u) ~~ O(n * k log k)
* For hashable objects: O(n) + O(u) ~~ O(n)

---

#### Space complexity
* n - number of elements
* u - number of unique elements
* k - number of attributes in a custom object

`_list_of_objects`:
Stores the list of n objects: O(n)

`_count_objects()`:
 * Generating keys: O(k) for custom objects or O(1) for hashable objects
 * In the worst case, every object is unique in dictionary: O(u) = O(n)

Total for n elements O(u * k) for custom objects or O(u) for hashable objects

---

**Overall Space Complexity:**
* For custom objects: O(n) + O(u * k) ~~ O(n * k)
* For hashable objects: O(n) + O(u) ~~ O(n)

---

#### Tests
The implementation includes 5 integration/unit test cases to validate the functionality. These tests check for duplicate detection in:

- A list of characters
- A list of numbers
- A list of strings
- A list of custom objects
- A mixed list containing characters, numbers, strings, and custom objects

And 5 unit tests for methods:
- `_generate_key`
- `_count_of_objects`
- `_update_list_of_objects`

To run the tests, execute the following command:

`python -m unittest .\Exercise_1\Python\src\tests\TestFinder.py`

![coverage](https://img.shields.io/badge/coverage-0.97-brightgreen)

To run the tests with coverage (Last generated is [HERE:](https://github.com/AdBanacho/adrian_banachowicz/blob/exercise-1-ab/Exercise_1/Python/src/tests/coverageRaport/htmlcov/index.html) `Exercise_1/Python/src/tests/coverageRaport/htmlcov/index.html`)

(if missing) `pip install coverage` 

`python -m coverage run -m unittest .\Exercise_1\Python\src\tests\TestFinder.py`

To generate a html report:
`python -m coverage html`

#### Improvements
Potential Improvement:
* Space Complexity for Custom Objects:
   * Currently, the worst-case space complexity is O(n)+O(u⋅k), which can be expensive for objects with many attributes
   * Proposed Solution: 
     * Instead of storing the entire object in the dictionary, store a reference to its position in _list_of_objects. This would reduce memory overhead
     
#### Advantages of the Proposed Solution
* Extensibility: It’s easy to add additional methods to find specific objects in the list
* Efficiency: The list of objects is processed and counted only once
* Flexibility: The method is not type-restricted and can handle lists containing various types, including custom objects