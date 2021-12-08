CREATE TABLE books (
  id SERIAL primary key,
  author text,
  launch_date date NOT NULL,
  price decimal NOT NULL,
  title text
);
