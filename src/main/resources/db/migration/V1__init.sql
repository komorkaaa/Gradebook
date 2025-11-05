CREATE TABLE students (
                          id UUID PRIMARY KEY,
                          name TEXT NOT NULL,
                          email TEXT UNIQUE
);

CREATE TABLE courses (
                         id VARCHAR(50) PRIMARY KEY,
                         name TEXT NOT NULL
);

CREATE TABLE grades (
                        id BIGSERIAL PRIMARY KEY,
                        student_id UUID REFERENCES students(id) ON DELETE CASCADE,
                        course_id VARCHAR(50) REFERENCES courses(id) ON DELETE CASCADE,
                        value NUMERIC(5,2) NOT NULL,
                        weight NUMERIC(5,2) DEFAULT 1.0,
                        date TIMESTAMP DEFAULT now(),
                        note TEXT
);