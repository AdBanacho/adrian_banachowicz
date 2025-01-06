-- Create a table
CREATE TABLE entities (
    object VARCHAR(255),
    position INT
);

-- Insert the list of objects with their positions
INSERT INTO entities (object, position)
VALUES
    ('b', 1),
    ('a', 2),
    ('c', 3),
    ('c', 4),
    ('e', 5),
    ('a', 6),
    ('c', 7),
    ('d', 8),
    ('c', 9),
    ('d', 10);

-- Additional tests cases
INSERT INTO entities (object, position)
VALUES
    ('e', 11);

-- Identify duplicates and preserve their first occurrence order
SELECT e.object
FROM entities e
JOIN (
    SELECT object
    FROM entities
    GROUP BY object
    HAVING COUNT(*) > 1
) dup ON e.object = dup.object
GROUP BY e.object
ORDER BY MIN(e.position)
