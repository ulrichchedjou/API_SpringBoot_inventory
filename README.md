# API de Gestion d'Inventaire

## Prérequis
- Java 17+
- Maven

## Installation
1. Cloner le dépôt `https://github.com/ulrichchedjou/API_SpringBoot_inventory`
2. Lancer l'API `mvn spring-boot:run`

## Accès
- API : `http://localhost:12496/api/products`
- Swagger UI : `http://localhost:12496/swagger-ui.html`
- URL Postgresql : `jdbc:postgresql://localhost:5432/inventorydb`

## Endpoints
- POST `/api/products` - Créer un produit
- GET `/api/products/all` - Lister les produits
- PUT `/api/products/{id}` - Mettre à jour un produit
- DELETE `/api/products/{id}` - Supprimer un produit
