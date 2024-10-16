## API Documentation

### Base URL

All endpoints are accessible via the base URL:

http://localhost:8080

## Overview
This project is a simple Point-of-Sale (POS) backend API built using the Spring framework. It allows for the management of customers, items, and orders. The API enables users to perform CRUD operations (Create, Read, Update, Delete) on customers and items, as well as place and manage orders. It is designed to be used with a frontend interface to track customer orders, manage inventory, and process transactions.

---

## Authentication
This API does not require authentication. Ensure that appropriate security measures are implemented in a production environment.

---
### Endpoints

#### 1. Customer Management

##### *POST /customer*

*Description:* Create a new customer.

*Request:*
- *Content-Type:* application/json
- *Body:*
  json
  {
  "nic": "200071255814",
  "name": "A B C Perera",
  "phoneNo": "07635698521"
  }


*Response:*
- *Status Code:* 201 Created if successful
- *Status Code:* 400 Bad Request if the request is invalid

*Error Response:*
json
{
"error": "Failed to save customer"
}

##### *PUT /customer*

*Description:* Update an existing customer.

*Request:*
- *Content-Type:* application/json
- *Query Parameter:* id - ID of the customer to update
- *Body:*
  json
  {
  "nic": "200071255814",
  "name": "A B C Perera",
  "phoneNo": "0775278965"
  }

*Response:*
- *Status Code:* 200 OK if successful
- *Status Code:* 400 Bad Request if the request is invalid
- *Status Code:* 500 Internal Server Error if the update fails

*Error Response:*
json
{
"error": "Failed to update customer with ID: " + customerID"
}

##### *DELETE /customer*

*Description:* Delete a customer.

*Request:*
- *Query Parameter:* id - ID of the customer to delete

*Response:*
- *Status Code:* 204 No Content if successful
- *Status Code:* 400 Bad Request if the ID is missing
- *Status Code:* 500 Internal Server Error if the deletion fails

*Error Response:*
json
{
"error": "Failed to delete customer with ID: " + customerId"
}

##### *GET /customer*

*Description:* Retrieve all customers.

*Response:*
- *Status Code:* 200 OK with JSON array of customers if successful
- *Status Code:* 204 No Content if no customers are found

*Example Response:*
json
[
{
"id": CUSTOMER-1337a272-6deb-4fa1-a76c-b20a20be76f9,
"nic": "200071255814",
"name": "A B C Perera",
"phoneNo": "0775278965"
},
{
"id": CUSTOMER-6421a26d-b605-42d5-a56b-7ed0ac503d30,
"nic": "199925892589",
"name": "A B C Silva",
"phoneNo": "0789632584"
}
]

#### 2. Item Management

##### *POST /item*

*Description:* Create a new item.

*Request:*
- *Content-Type:* application/json
- *Body:*
  json
  {
  "name": "Cake",
  "price": 1500.00,
  "qty": 100
  }

*Response:*
- *Status Code:* 201 Created if successful
- *Status Code:* 400 Bad Request if the request is invalid
- *Status Code:* 500 Internal Server Error if the item creation fails

*Error Response:*
json
{
"error": "Save item failed"
}

##### *PUT /item*

*Description:* Update an existing item.

*Request:*
- *Content-Type:* application/json
- *Query Parameter:* id - ID of the item to update
- *Body:*
  json
  {
  "name": "Cake",
  "price": 2000.00,
  "qty": 100
  }

*Response:*
- *Status Code:* 200 OK if successful
- *Status Code:* 400 Bad Request if the request is invalid
- *Status Code:* 500 Internal Server Error if the update fails

*Error Response:*
json
{
"error": "Update failed"
}

##### *DELETE /item*

*Description:* Delete an item.

*Request:*
- *Query Parameter:* id - ID of the item to delete

*Response:*
- *Status Code:* 204 No Content if successful
- *Status Code:* 400 Bad Request if the ID is missing
- *Status Code:* 500 Internal Server Error if the deletion fails

*Error Response:*
json
{
"error": "Delete Failed"
}

##### *GET /item*

*Description:* Retrieve all items.

*Response:*
- *Status Code:* 200 OK with JSON array of items if successful
- *Status Code:* 204 No Content if no items are found

*Example Response:*
json
[
{
"id": ITEM-9bd3ac4a-626f-4e68-bae6-89814d351cdb,
"name": "cake",
"price": 2000.00,
"qty": 100
},
{
"id": ITEM-9d0e411d-7723-4034-904c-719646013d89,
"name": "Cup-Cake",
"price": 300.00,
"qty": 60
}
]


#### 3. Order Management

##### *POST /order*

*Description:* Create a new order.

*Request:*
- *Content-Type:* application/json
- *Body:*
  json
  {
  "orderDate": "2024-10-15", // Current date
  "total": 1900.0, // Total order price
  "customer": {
  "id": "CUSTOMER-92b537f5-c7fa-4eb7-880a-edb73c39dcb4",
  "nic": "200271202815",
  "name": "Saman",
  "phoneNo": "0783533750"
  },
  "items": [
  {
  "code": "ITEM-9d0e411d-7723-4034-904c-719646013d89", // Sample item code
  "name": "apple", // Item name
  "price": 300.0, // Price per item
  "qty": 3 // Ordered quantity
  },
  {
  "code": "ITEM-c9d82d44-9bba-42db-84e4-0b413d620aeb", // Sample item code
  "name": "orange", // Item name
  "price": 200.0, // Price per item
  "qty": 5 // Ordered quantity
  }
  ]
  }


*Response:*
- *Status Code:* 200 OK if successful
- *Status Code:* 400 Bad Request if the request is invalid
- *Status Code:* 500 Internal Server Error if the order creation fails

*Error Response:*
json
{
"error": "Order creation failed"
}

##### *GET /order*

*Description:* Retrieve an order by ID or list all orders.

*Request:*
- *Query Parameter:* orderId - (Optional) ID of the order to retrieve

*Response:*
- *Status Code:* 200 OK with JSON representation of the order if successful
- *Status Code:* 404 Not Found if the order is not found
- *Status Code:* 400 Bad Request if the ID is invalid

*Example Response (Single Order):*
OrderDTO(orderId=ORDER-d8ee066d-01cd-4a19-9a28-863f56bbe830, orderDate=2024-10-16, total=800.0, customer=CustomerDTO(id=CUSTOMER-1337a272-6deb-4fa1-a76c-b20a20be76f9, nic=200175369856, name=amal, phoneNo=0762541111), items=[ItemDTO(code=ITEM-9d0e411d-7723-4034-904c-719646013d89, name=cake, price=200.0, qty=4)])

### Error Codes

- *400 Bad Request:* The request could not be understood or was missing required parameters.
- *404 Not Found:* The requested resource could not be found.
- *500 Internal Server Error:* An error occurred on the server.

---

### Front End for this Back End


---
- *refer this link:* [https://github.com/senumiminodya/POS_JavaEE_Frontend.git](https://github.com/senumiminodya/POS_JavaEE_Frontend.git)
---

## License

This project is open-source and available under the [MIT License](LICENSE).
