DELETE FROM pet;
INSERT INTO pet (id, name, characteristics) VALUES (1, 'Paul', '{"species": "Dog", "colours": ["blue", "yellow"]}'::jsonb);
INSERT INTO pet (id, name, characteristics) VALUES (2, 'Alice', '{"species": "Cat", "colours": ["green", "black"]}'::jsonb);