# Catálogo de Productos Flexible con MongoDB

Una API RESTful construida con Spring Boot y MongoDB para gestionar un catálogo de productos con especificaciones técnicas variables, aprovechando la flexibilidad de los esquemas de MongoDB.

## 🎯 Objetivo del Proyecto

Construir una API que demuestre el poder de MongoDB para manejar datos con esquema flexible, donde cada producto puede tener diferentes especificaciones técnicas según su tipo (libros, laptops, ropa, etc.).

## 📋 Descripción del Escenario

Imagina un sistema de catálogo de productos donde:
- Un **libro** necesita: autor, ISBN, editorial
- Un **laptop** necesita: RAM, CPU, tamaño de pantalla
- Una **camiseta** necesita: talla, color, material

MongoDB es ideal para este caso debido a su esquema flexible que permite almacenar diferentes estructuras de datos en la misma colección.

## 🏗️ Estructura del Proyecto

```
src/main/java/com/devsenior/andresquintero/catalogo/
├── controller/          # Controladores REST
│   └── ProductoController.java
├── service/            # Lógica de negocio
│   ├── ProductService.java
│   └── ProductServiceImpl.java
├── model/              # Modelos de datos
│   ├── document/       # Entidades de MongoDB
│   │   └── Product.java
│   ├── dto/           # Data Transfer Objects
│   │   ├── ProductRequest.java
│   │   ├── ProductResponse.java
│   │   └── ProductListResponse.java
│   └── shared/        # Enums y clases compartidas
│       └── Tag.java
├── repository/         # Repositorios de MongoDB
│   └── ProductRepository.java
├── mapper/            # Mapeo entre DTOs y entidades
│   └── ProductMapper.java
├── exception/         # Manejo de excepciones personalizadas
│   ├── ProductNotFoundException.java
│   └── ProductoIdNotFoundException.java
├── config/            # Configuraciones globales
│   ├── ApiErrorResponse.java
│   └── GlobalExceptionHandler.java
└── CatalogoDeProductosLlApplication.java
```

## 🗄️ Modelo de Datos

### Estructura de un Producto

```json
{
  "_id": "64a7b8c9d1e2f3g4h5i6j7k8",
  "name": "MacBook Pro 14\"",
  "price": 1999.99,
  "description": "Laptop de alto rendimiento para profesionales",
  "especification": {
    "procesador": "Apple M2 Pro",
    "ram": "16GB",
    "almacenamiento": "512GB SSD",
    "pantalla": "14.2\" Liquid Retina XDR"
  },
  "tags": ["ELECTRONICA", "LAPTOP", "APPLE"]
}
```

### Ejemplos de Productos con Diferentes Especificaciones

#### Libro
```json
{
  "name": "Clean Code",
  "price": 45.50,
  "description": "Guía de mejores prácticas para código limpio",
  "especification": {
    "autor": "Robert C. Martin",
    "isbn": "978-0132350884",
    "editorial": "Prentice Hall",
    "paginas": "464",
    "idioma": "Inglés"
  },
  "tags": ["LIBRO", "PROGRAMACION", "TECNICO"]
}
```

#### Camiseta
```json
{
  "name": "Camiseta Premium Algodón",
  "price": 29.99,
  "description": "Camiseta de algodón orgánico de alta calidad",
  "especification": {
    "material": "Algodón Orgánico 100%",
    "talla": "L",
    "color": "Blanco",
    "tipo_cuello": "Redondo",
    "origen": "Fair Trade"
  },
  "tags": ["ROPA", "ECOLOGICO", "CASUAL"]
}
```

## 🔧 Configuración del Proyecto

### Dependencias Requeridas

Agrega estas dependencias a tu `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Boot Starter Data MongoDB -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
    
    <!-- Spring Boot Starter Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    
    <!-- SpringDoc OpenAPI UI -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.2.0</version>
    </dependency>
    
    <!-- Lombok (Opcional) -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

### Configuración de MongoDB

#### Para MongoDB Local
En `application.properties`:

```properties
# Configuración MongoDB Local
spring.data.mongodb.uri=mongodb://localhost:27017/catalogo_productos
spring.data.mongodb.database=catalogo_productos

# Configuración del servidor
server.port=8080
```

#### Para MongoDB Atlas
En `application.properties`:

```properties
# Configuración MongoDB Atlas
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster0.xxxxx.mongodb.net/catalogo_productos?retryWrites=true&w=majority
spring.data.mongodb.database=catalogo_productos

# Configuración del servidor
server.port=8080
```

#### Configuración con YAML (Opcional)
En `application.yml`:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/catalogo_productos
      database: catalogo_productos

server:
  port: 8080

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
```

## 🚀 Endpoints de la API

### Operaciones CRUD Básicas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/productos` | Crear un nuevo producto |
| GET | `/api/productos` | Obtener todos los productos |
| GET | `/api/productos/{id}` | Obtener producto por ID |
| PUT | `/api/productos/{id}` | Actualizar producto existente |
| DELETE | `/api/productos/{id}` | Eliminar producto |

### Endpoints de Consulta Avanzada

| Método | Endpoint | Parámetros | Descripción |
|--------|----------|------------|-------------|
| GET | `/api/productos/find` | `b={texto}` | Búsqueda por nombre o descripción (Regex) |
| GET | `/api/productos/tag` | `b={tag}` | Filtrar por etiqueta específica |
| GET | `/api/productos/especificacion` | `t={clave}&g={valor}` | Buscar por especificación anidada |

## 📝 Ejemplos de Uso

### Crear un Producto
```bash
POST /api/productos
Content-Type: application/json

{
  "name": "iPhone 15 Pro Max",
  "price": 1199.99,
  "description": "Smartphone de última generación",
  "especification": {
    "almacenamiento": "256GB",
    "color": "Titanio Azul",
    "pantalla": "6.7\" OLED",
    "procesador": "A17 Pro"
  },
  "tags": ["ELECTRONICA", "CELULAR", "APPLE"]
}
```

### Buscar Productos por Texto
```bash
GET /api/productos/find?b=iphone
```

### Filtrar por Etiqueta
```bash
GET /api/productos/tag?b=ELECTRONICA
```

### Buscar por Especificación
```bash
GET /api/productos/especificacion?t=almacenamiento&g=256GB
```

## 🛠️ Tecnologías Utilizadas

- **Java 17+**: Lenguaje de programación principal
- **Spring Boot 3.x**: Framework principal
- **Spring Data MongoDB**: Integración con MongoDB
- **MongoDB**: Base de datos NoSQL
- **Spring Validation**: Validación de datos
- **SpringDoc OpenAPI**: Documentación de API
- **Maven**: Gestión de dependencias

## 🧪 Pruebas con HTTP Requests

El proyecto incluye un archivo `Product.http` en la carpeta `htto/` con ejemplos de todas las solicitudes HTTP para probar la API directamente desde tu IDE.

## 📚 Características Destacadas

### ✅ Esquema Flexible
- Cada producto puede tener diferentes especificaciones técnicas
- El campo `especification` acepta cualquier estructura JSON
- No hay necesidad de migraciones de esquema

### ✅ Consultas Potentes
- Búsqueda de texto con expresiones regulares
- Consultas en campos anidados
- Filtrado por etiquetas en arrays
- Búsqueda por pares clave-valor en especificaciones

### ✅ Validación Robusta
- Validación de campos obligatorios
- Validación de formatos de datos
- Manejo centralizado de excepciones

### ✅ Documentación Automática
- Interfaz Swagger UI disponible en `/swagger-ui.html`
- Documentación OpenAPI en `/api-docs`

## 🚀 Cómo Ejecutar el Proyecto

1. **Clonar el repositorio**
   ```bash
   git clone <repository-url>
   cd catalogo_de_productos_ll
   ```

2. **Configurar MongoDB**
   - Asegúrate de tener MongoDB corriendo localmente
   - O configura tu conexión a MongoDB Atlas en `application.properties`

3. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

4. **Acceder a la API**
   - API Base URL: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - API Docs: `http://localhost:8080/api-docs`




## 📄 Licencia

Este proyecto es educativo y puede ser utilizado como referencia para aprender sobre Spring Boot y MongoDB.

---


