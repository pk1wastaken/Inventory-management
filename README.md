# Store Inventory Management System

Welcome to the **Store Inventory Management System**, a web-based application built with **Spring Boot** and **Thymeleaf** to manage inventory data efficiently. This application includes advanced features such as sorting, filtering, pagination, chart-based analytics, CSV export, and bulk operations, all with a clean and responsive user interface.

---

## Features

### 1. Dashboard with Pie Chart Analytics
- Displays aggregate statistics:
  - Total Inventory Items
  - Total Price
  - Average Margin Price
- Visualizes price vs. margin using a **pie chart** (powered by Chart.js).

### 2. Inventory List with Sorting and Pagination
- **Sorting**: Clickable column headers to sort inventory by name, price, margin, or type.
- **Pagination**: Navigate through large datasets with controls for pages.

### 3. Search/Filter by Name
- Quickly find inventory items using a case-insensitive search by product name.

### 4. Export Data as CSV
- Download the entire inventory list as a CSV file for backup or reporting.

### 5. Bulk Delete Operations
- Select multiple inventory items and delete them in one go.

### 6. Conditional Row Highlighting
- Automatically highlight rows where the margin price is below a defined threshold.

---

## Screenshots

### Dashboard
![Dashboard Screenshot](#)

### Inventory List
![Inventory List Screenshot](#)

---

## Technologies Used

### Backend:
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **Java 8+**

### Frontend:
- **Thymeleaf**
- **Bootstrap 4**
- **Chart.js**

### Database:
- **H2 Database** (for development, can be switched to MySQL or PostgreSQL)

---

## Getting Started

### Prerequisites
- **Java 8+**
- **Maven**
- **IDE** (IntelliJ, Eclipse, etc.)

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/store-inventory-management.git
    cd store-inventory-management
    ```

2. Build the application:
    ```bash
    mvn clean install
    ```

3. Run the application:
    ```bash
    mvn spring-boot:run
    ```

4. Access the application in your browser at:
    ```
    http://localhost:8080
    ```

---

## Usage

### Dashboard
- Visit `/dashboard` to view aggregate statistics and a pie chart comparing total price and margin.

### Inventory List
- Visit `/` (or `/page/1`) to view the inventory list.
- Use the **search bar**, **sorting links**, and **pagination controls** to navigate and filter items.

### Adding a New Inventory Item
- Click on "Add Inventory" in the navigation bar.
- Fill out the form and submit.

### Updating an Existing Inventory Item
- Click "Update" next to an item in the inventory list.
- Modify the form fields and submit.

### Deleting Items
- Use the "Delete" button for single deletion.
- Select multiple items with checkboxes and click "Delete Selected" for bulk deletion.

### Exporting as CSV
- Click "Export to CSV" on the inventory list page to download the dataset.

---

## File Structure

```
store-inventory-management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/Data/
│   │   │       ├── Controller/
│   │   │       │   └── InventoryController.java
│   │   │       ├── Models/
│   │   │       │   └── Inventory.java
│   │   │       ├── Service/
│   │   │       │   ├── InventoryService.java
│   │   │       │   └── InventoryServiceImp.java
│   │   │       └── repository/
│   │   │           └── InventoryRepository.java
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       │   ├── dashboard.html
│   │       │   ├── index.html
│   │       │   ├── new_Inventory.html
│   │       │   └── updateInventory.html
│   │       └── application.properties
├── pom.xml
└── README.md
```

---

## Configuration

### Database Configuration
By default, the application uses an in-memory **H2 database**. To switch to a different database (e.g., MySQL):

1. Update `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/inventory
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

2. Add the MySQL dependency to `pom.xml`:
    ```xml
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    ```

---

## License
This project is licensed under the [MIT License](LICENSE).

---

## Contributing
Contributions are welcome! Feel free to submit a pull request or open an issue.

---

## Acknowledgments
- **Spring Boot** for simplifying backend development.
- **Thymeleaf** for intuitive HTML templates.
- **Bootstrap** for responsive design.
- **Chart.js** for beautiful visualizations.

---

## Contact
For questions or feedback, reach out to:
- **Your Name**: [your.email@example.com](mailto:your.email@example.com)
- **GitHub**: [https://github.com/yourusername](https://github.com/yourusername)

