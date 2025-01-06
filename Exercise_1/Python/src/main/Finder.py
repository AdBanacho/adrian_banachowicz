class Finder:
    """
    A class to find specific objects in a list. It supports both hashable and custom objects
    by using their attributes or hashable properties as keys for comparison

    Attributes:
        _list_of_objects (list): The list of objects to analyze.
        _count_of_objects (dict): A dictionary to store the count and object data keyed by
                                  a unique representation of the objects
    """

    def __init__(self, list_of_objects=None):
        """
        Initializes the Finder instance with an optional list of objects

        Args:
            list_of_objects (list, optional): The list of objects to initialize with. Defaults to an empty list
        """
        self._list_of_objects = list_of_objects if list_of_objects is not None else []
        self._count_of_objects = {}
        self._count_objects()

    def update_list_of_objects(self, list_of_objects):
        """
        Updates the list of objects in the Finder instance and recalculates object counts

        Args:
            list_of_objects (list): The new list of objects to analyze for duplicates

        """
        if self._list_of_objects == list_of_objects:  # if list is the same do nothing
            return
        self._list_of_objects = list_of_objects
        self._count_objects()

    def _count_objects(self):
        """
        Populates the _count_of_objects dictionary with counts of each object
        in the current list, using a generated key for comparison
        """
        self._clean_count_of_objects()
        for obj in self._list_of_objects:
            key = self._generate_key(obj)
            if key in self._count_of_objects:
                self._count_of_objects[key]["count"] += 1
            else:
                self._count_of_objects[key] = {"count": 1, "object": obj}

    def _clean_count_of_objects(self):
        """
        Resets the _count_of_objects dictionary to an empty state
        """
        self._count_of_objects = {}

    def _generate_key(self, obj):
        """
        Generates a unique key for the given object to use in comparison

        For hashable objects, the object itself is used as the key. For custom objects
        with attributes, a tuple of sorted attribute-value pairs is used

        Args:
            obj: The object to generate a key for

        Returns:
            A hashable representation of the object

        Raises:
            ValueError: If the object type is not supported
        """
        if hasattr(obj, "__dict__"):  # Custom objects with attributes
            return tuple(sorted(obj.__dict__.items()))
        elif hasattr(obj, "__hash__"):  # Hashable objects
            return obj
        else:
            raise ValueError(f"Unsupported object type: {type(obj)}")

    def find_duplicated_objects_in_order(self):
        """
        Finds and returns a list of duplicate objects from the current list in the order they appear

        Returns:
            list: A list of objects that appear more than once in the list
        """
        duplicated_objects_in_order = []
        for key, value in self._count_of_objects.items():
            if value["count"] > 1:
                duplicated_objects_in_order.append(value["object"])
        return duplicated_objects_in_order
