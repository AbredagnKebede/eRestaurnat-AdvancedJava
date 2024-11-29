CREATE DATABASE e_restaurant;
USE e_restaurant;

CREATE TABLE Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    food_type VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    verification_code INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE Menu (
    food_type VARCHAR(100) PRIMARY KEY,
    price DECIMAL(10, 2) NOT NULL
);

INSERT INTO Menu (food_type, price) VALUES
('Margherita_pizza', 100),
('Pepperoni_pizza', 250),
('Hawaiian_pizza', 300),
('Regular_Burger', 150),
('Extra_special_Burger', 200),
('Extra_double_Burger', 160),
('Mushabak_with_honey_only', 40),
('Mushabak_honey_and_sugar', 50),
('Mushabak_with_less_honey', 60),
('Mexican_tacos_with_beef', 170),
('Mexican_tacos_for_vegetarian', 140),
('Mexican_tacos_with_chicken_and_fruits', 120),
('chip_butty_with_egg', 70),
('chip_butty_with_soya', 80),
('chip_butty_with_Avocado', 90);
