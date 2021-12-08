CREATE TABLE IF NOT EXISTS users (
  id SERIAL primary key not null,
  user_name text DEFAULT NULL unique,
  full_name text DEFAULT NULL,
  password text DEFAULT NULL,
  account_non_expired bit(1) DEFAULT NULL,
  account_non_locked bit(1) DEFAULT NULL,
  credentials_non_expired bit(1) DEFAULT NULL,
  enabled bit(1) DEFAULT NULL
);