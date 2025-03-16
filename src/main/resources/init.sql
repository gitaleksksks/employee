CREATE TABLE IF NOT EXISTS employees (
                                         id SERIAL PRIMARY KEY,
                                         full_name VARCHAR(255) NOT NULL,
                                         birth_date DATE NOT NULL,
                                         gender VARCHAR(10) NOT NULL
);
