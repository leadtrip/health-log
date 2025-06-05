CREATE TABLE health_log (
        id SERIAL PRIMARY KEY,
        feeling VARCHAR(20),
        note TEXT,
        timestamp TIMESTAMP NOT NULL DEFAULT NOW(),
        type VARCHAR(20)
);

