CREATE TABLE countries (
                           id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           name VARCHAR(255) NOT NULL
);

CREATE TABLE cities (
                        id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        country_id BIGINT NOT NULL,
                        CONSTRAINT fk_city_country FOREIGN KEY (country_id)
                            REFERENCES countries(id)
);

CREATE TABLE trips (
                       id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       title VARCHAR(255),
                       start_date DATE,
                       end_date DATE,
                       description TEXT
);

CREATE TABLE trip_cities (
                             trip_id BIGINT NOT NULL,
                             city_id BIGINT NOT NULL,
                             PRIMARY KEY (trip_id, city_id),
                             CONSTRAINT fk_trip_city_trip FOREIGN KEY (trip_id)
                                 REFERENCES trips(id) ON DELETE CASCADE,
                             CONSTRAINT fk_trip_city_city FOREIGN KEY (city_id)
                                 REFERENCES cities(id) ON DELETE CASCADE
);


CREATE TABLE hotels (
                        id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        name VARCHAR(255),
                        stars INT,
                        price_per_night DOUBLE PRECISION,
                        city_id BIGINT NOT NULL,
                        trip_id BIGINT NOT NULL,
                        CONSTRAINT fk_hotel_city FOREIGN KEY (city_id)
                            REFERENCES cities(id),
                        CONSTRAINT fk_hotel_trip FOREIGN KEY (trip_id)
                            REFERENCES trips(id)
);

CREATE TABLE activities (
                            id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            category VARCHAR(255),
                            price DOUBLE PRECISION,
                            trip_id BIGINT,
                            city_id BIGINT,
                            CONSTRAINT fk_activity_trip FOREIGN KEY (trip_id)
                                REFERENCES trips(id),
                            CONSTRAINT fk_activity_city FOREIGN KEY (city_id)
                                REFERENCES cities(id)
);