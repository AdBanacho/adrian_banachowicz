import unittest
from Exercise_1.Python.src.main.Finder import Finder


class SimpleEntity:
    """Simple class to test custom object handling in Finder"""
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __repr__(self):
        return f"SimpleEntity(name={self.name}, age={self.age})"


class SimpleEntityWithoutAttributes:
    """Simple class to test custom object without attributes handling in Finder"""
    def __repr__(self):
        return f"SimpleEntity"


class TestFinder(unittest.TestCase):
    def setUp(self):
        """Set up a Finder instance for testing"""
        self.finder = Finder()

    def test_letters(self):
        """Test with a list of letters"""
        letters = ["b", "a", "c", "c", "e", "a", "c", "d", "c", "d"]
        self.finder.update_list_of_objects(letters)
        expected = ["a", "c", "d"]
        self.assertEqual(self.finder.find_duplicated_objects_in_order(), expected)

    def test_numbers(self):
        """Test with a list of numbers"""
        numbers = [1, 5, 6, 4, 8, 7, 1, 3, 4, 3, 6, 7, 1]
        self.finder.update_list_of_objects(numbers)
        expected = [1, 6, 4, 7, 3]
        self.assertEqual(self.finder.find_duplicated_objects_in_order(), expected)

    def test_texts(self):
        """Test with a list of strings"""
        texts = ["CERN", "cern", "Adrian", "Interview", "CERN", "Adrian"]
        self.finder.update_list_of_objects(texts)
        expected = ["CERN", "Adrian"]
        self.assertEqual(self.finder.find_duplicated_objects_in_order(), expected)

    def test_custom_objects(self):
        """Test with a list of custom objects"""
        o1 = SimpleEntity(name="John", age=30)
        o2 = SimpleEntity(name="Adrian", age=27)
        o3 = SimpleEntity(name="Alex", age=30)
        o4 = SimpleEntity(name="John", age=30)
        o5 = SimpleEntityWithoutAttributes()
        o6 = SimpleEntity(name="Adrian", age=27)
        o7 = SimpleEntityWithoutAttributes()
        objects = [o1, o2, o3, o4, o5, o6, o7]
        self.finder.update_list_of_objects(objects)
        expected = [o1, o2, o5]
        self.assertEqual(self.finder.find_duplicated_objects_in_order(), expected)

    def test_mixed_types(self):
        """Test with a list of mixed types"""
        o1 = SimpleEntity(name="John", age=30)
        o2 = SimpleEntity(name="Adrian", age=27)
        o4 = SimpleEntity(name="John", age=30)
        mixed_types = ["CERN", 1.0, o1, "cern", 'b', -3, o2, "Adrian", -3, "CERN", 'c', o4, 'b']
        self.finder.update_list_of_objects(mixed_types)
        expected = ["CERN", o1, 'b', -3]
        self.assertEqual(self.finder.find_duplicated_objects_in_order(), expected)

    def test_count_objects_with_strings(self):
        """Test _count_objects() method"""
        letters = ["a", "b", "c", "a", "b", "c", "d"]
        finder = Finder(letters)

        self.assertEqual(len(finder._count_of_objects), 4)
        self.assertEqual(finder._count_of_objects["a"]["count"], 2)
        self.assertEqual(finder._count_of_objects["b"]["count"], 2)
        self.assertEqual(finder._count_of_objects["c"]["count"], 2)
        self.assertEqual(finder._count_of_objects["d"]["count"], 1)

    def test_count_objects_with_numbers(self):
        """Test _count_objects() method"""
        numbers = [1, 2, 3, 1, 4, 5, 3, 2]
        finder = Finder(numbers)

        self.assertEqual(len(finder._count_of_objects), 5)
        self.assertEqual(finder._count_of_objects[1]["count"], 2)
        self.assertEqual(finder._count_of_objects[2]["count"], 2)
        self.assertEqual(finder._count_of_objects[3]["count"], 2)
        self.assertEqual(finder._count_of_objects[4]["count"], 1)
        self.assertEqual(finder._count_of_objects[5]["count"], 1)

    def test_generate_key_for_hashable_objects(self):
        """Test _generate_key() method for hashable objects"""
        numbers = [1, 2, 3]
        finder = Finder(numbers)

        # Test _generate_key for hashable objects (integers)
        key_1 = finder._generate_key(1)
        key_2 = finder._generate_key(2)
        key_3 = finder._generate_key(3)

        # Since the object itself is the key for hashable types, the key should be the object value
        self.assertEqual(key_1, 1)
        self.assertEqual(key_2, 2)
        self.assertEqual(key_3, 3)

    def test_generate_key_for_custom_objects(self):
        """Test _generate_key() method for custom objects"""
        o1 = SimpleEntity(name="John", age=30)
        o2 = SimpleEntity(name="Adrian", age=27)
        finder = Finder([o1, o2])

        # Test _generate_key for custom objects
        key_1 = finder._generate_key(o1)
        key_2 = finder._generate_key(o2)

        # Since the key is a tuple of sorted attributes for custom objects
        self.assertEqual(key_1, (("age", 30), ("name", "John")))
        self.assertEqual(key_2, (("age", 27), ("name", "Adrian")))


if __name__ == "__main__":
    unittest.main()
