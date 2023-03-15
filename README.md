# Perscholas-Capstone-Working
Proof of Concept for a ecommerce site for the purposes of selling collectable cards.

Run Sql commands to create an admin user before registering.

INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_GUEST'); 
 
INSERT INTO `users` VALUES (1,'admin@gmail.com','$2a$10$k9644mshajjDvMhU8p76.u4sgOFuINZDkZ/csNgzFY99W1diZjBuC','admin'); 
 
INSERT INTO `users_roles` VALUES (1,1);
